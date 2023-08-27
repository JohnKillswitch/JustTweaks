package ru.john.justtweaks.handlers

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf
import java.util.*

class StoneCutterDamagePlayers (
    private val mainConf: Configuration<MainConf>,
    private val plugin: JavaPlugin,
    private val cooldownMap: WeakHashMap<UUID, Long>
) : Listener {

    @EventHandler
    fun checkEvent (event: PlayerMoveEvent) {
        if (event.to.block.type != Material.STONECUTTER) return

        val blacklist = mainConf.data().tweaks().animalsScary().blacklist()
        if (blacklist.any { it.equals(event.to.block.world.name, ignoreCase = true) }) return

        if ((cooldownMap[event.player.uniqueId]?.minus(System.currentTimeMillis()) ?: -1000) > -499) return

        cooldownMap[event.player.uniqueId] = System.currentTimeMillis()

        event.player.damage(1.0)

    }
}