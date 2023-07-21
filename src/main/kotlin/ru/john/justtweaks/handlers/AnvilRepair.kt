package ru.john.justtweaks.handlers

import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import org.bukkit.Material
import org.bukkit.block.data.BlockData
import org.bukkit.block.data.Directional
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf


class AnvilRepair (
    private val mainConf: Configuration<MainConf>,
) : Listener {

    companion object {
        val TYPE_MAP = mapOf(
            Material.CHIPPED_ANVIL to Material.ANVIL,
            Material.DAMAGED_ANVIL to Material.CHIPPED_ANVIL
        )
    }

    @EventHandler
    fun checkEvent(event: PlayerInteractEvent) {

        if (event.action != Action.RIGHT_CLICK_BLOCK) return
        val block = event.clickedBlock ?: return
        val type = block.type
        val itemData = this.mainConf.data().tweaks().anvilRepair()
        val player = event.player
        val mainHand = player.inventory.itemInMainHand


        if (type != Material.CHIPPED_ANVIL &&
            type != Material.DAMAGED_ANVIL) return
        if (Material.getMaterial(itemData .itemMaterial()) != mainHand.type &&
            mainHand.amount != itemData .itemCount()) return

        val face = (block.blockData as Directional).facing

        block.type = TYPE_MAP[type] ?: return

        val data = block.blockData
        (data as Directional).facing = face
        block.blockData = data

        mainHand.amount -= 1
        player.playSound(Sound.sound(Key.key("block.anvil.use"), Sound.Source.BLOCK, 0.5f, 1f))
        event.isCancelled = true

    }
}