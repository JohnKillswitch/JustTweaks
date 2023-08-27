package ru.john.justtweaks.handlers

import io.papermc.paper.event.entity.EntityMoveEvent
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf
import java.util.*

class StoneCutterDamageMobs (
    private val mainConf: Configuration<MainConf>,
    private val plugin: JavaPlugin,
    private val cooldownMap: WeakHashMap<UUID, Long>
) : Listener {

    @EventHandler
    fun checkEvent (event: EntityMoveEvent) {
        if (event.to.block.type != Material.STONECUTTER) return

        val blacklist = mainConf.data().tweaks().stoneCutterDamage().blacklist()
        if (blacklist.any { it.equals(event.to.block.world.name, ignoreCase = true) }) return

        if ((cooldownMap[event.entity.uniqueId]?.minus(System.currentTimeMillis()) ?: -1000) > -499) return

        cooldownMap[event.entity.uniqueId] = System.currentTimeMillis()

        event.entity.damage(1.0)

    }
}