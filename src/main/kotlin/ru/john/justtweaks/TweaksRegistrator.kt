package ru.john.justtweaks

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf
import ru.john.justtweaks.handlers.*
import java.util.*

class TweaksRegistrator(
    val plugin: JavaPlugin,
    private val mainConf: Configuration<MainConf>
    ) {
    fun checkHandlers() {
        val directory = mainConf.data().tweaks()
        if (directory.arrowFired().enabled()) registerHandler(ArrowFired(this.mainConf))
        if (directory.stoneCutterDamage().enabled())
            registerHandler(StoneCutterDamagePlayers(
                this.mainConf,
                this.plugin,
                WeakHashMap<UUID, Long>()))

        if (directory.stoneCutterDamage().enabled() &&
            directory.stoneCutterDamage().mobsTrigger())
            registerHandler(StoneCutterDamageMobs(
                this.mainConf,
                this.plugin,
                WeakHashMap<UUID, Long>()))
        if (directory.createPath().enabled()) registerHandler(CreatePath(this.mainConf))
        if (directory.anvilRepair().enabled()) registerHandler(AnvilRepair(this.mainConf))
        if (directory.anvilRepair().enabled()) registerHandler(AnimalsScary(this.mainConf))
        if (directory.endCrystalBlockDamage().enabled()) registerHandler(EndCrystalBlockDamage(this.mainConf))
        if (directory.piercingForShield().enabled())
            registerHandler(PiercingForShield(
                this.mainConf, WeakHashMap<UUID, PiercingForShield.SavedArrow>()))


    }

    private fun registerHandler(handler: Listener) {
        this.plugin.server.pluginManager.registerEvents(handler, this.plugin)
    }

}