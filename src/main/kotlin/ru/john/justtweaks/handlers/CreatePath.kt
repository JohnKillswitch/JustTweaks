package ru.john.justtweaks.handlers

import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf
import ru.john.justtweaks.util.CPCheck
import ru.john.justtweaks.util.WGCheck

class CreatePath (
    private val mainConf: Configuration<MainConf>,
) : Listener {

    @EventHandler
    fun checkEvent(event: PlayerMoveEvent) {

        val fromBlock = event.from.block
        val downBlock = fromBlock.getRelative(BlockFace.DOWN)

        if (downBlock.type != Material.GRASS_BLOCK &&
            downBlock.type != Material.DIRT
        ) return
        if (event.to.block == fromBlock) return
        if (!WGCheck().isPlayerNotInAnyRegion(event.player)) return


        if ((1..100 / mainConf.data().tweaks().createPath().changeChance()).random() != 1) return

        if (fromBlock.isSolid) return

        when (downBlock.type) {
            Material.GRASS_BLOCK -> downBlock.type = Material.DIRT
            Material.DIRT -> downBlock.type = Material.GRASS_PATH
            else -> return
        }
        CPCheck().getCP()?.logPlacement(event.player.name, downBlock.location, downBlock.type, null)
    }

}