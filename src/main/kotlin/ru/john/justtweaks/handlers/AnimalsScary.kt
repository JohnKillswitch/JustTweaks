package ru.john.justtweaks.handlers

import org.bukkit.entity.Animals
import org.bukkit.entity.Mob
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf
import java.util.*


class AnimalsScary (
    private val mainConf: Configuration<MainConf>,
) : Listener {

    @EventHandler
    fun checkEvent (event: EntityDamageByEntityEvent) {
        val damager = event.damager
        if (damager !is Player) return
        if (event.entity !is Animals) return

        val blacklist = mainConf.data().tweaks().animalsScary().blacklist()
        if (blacklist.any { it.equals(damager.world.name, ignoreCase = true) }) return

        val radius = mainConf.data().tweaks().animalsScary().scaryRadius().toDouble()

        val entities = damager.getNearbyEntities(radius, radius, radius)
        entities.forEach { e ->
            if (e is Animals) {
                val pathf = (e as Mob).pathfinder
                val test = e.location.add(Random().nextDouble(-10.0, 10.0), 0.0, Random().nextDouble(-10.0, 10.0)).toBlockLocation()
                pathf.moveTo(test, 1.5)
            }
        }
    }


}