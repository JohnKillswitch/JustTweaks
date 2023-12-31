package ru.john.justtweaks.handlers

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf

class EndCrystalBlockDamage (
    private val mainConf: Configuration<MainConf>,
) : Listener {

    @EventHandler
    fun onEntityExplode(event: EntityExplodeEvent) {
        if (event.entityType != EntityType.ENDER_CRYSTAL) return

        val blacklist = mainConf.data().tweaks().endCrystalBlockDamage().blacklist()
        if (blacklist.any { it.equals(event.entity.world.name, ignoreCase = true) }) return

        event.blockList().clear()

    }
}