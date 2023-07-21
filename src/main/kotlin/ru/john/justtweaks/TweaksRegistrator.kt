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
        if (mainConf.data().tweaks().arrowFired().enabled()) registerHandler(ArrowFired(this.mainConf))
        if (mainConf.data().tweaks().stoneCutterDamage().enabled())
            registerHandler(StoneCutterDamagePlayers(
                this.mainConf,
                this.plugin,
                WeakHashMap<UUID, Long>()))

        if (mainConf.data().tweaks().stoneCutterDamage().enabled() &&
            mainConf.data().tweaks().stoneCutterDamage().mobsTrigger())
            registerHandler(StoneCutterDamageMobs(
                this.mainConf,
                this.plugin,
                WeakHashMap<UUID, Long>()))
        if (mainConf.data().tweaks().createPath().enabled()) registerHandler(CreatePath(this.mainConf))
        if (mainConf.data().tweaks().anvilRepair().enabled()) registerHandler(AnvilRepair(this.mainConf))
        if (mainConf.data().tweaks().anvilRepair().enabled()) registerHandler(AnimalsScary(this.mainConf))
        if (mainConf.data().tweaks().endCrystalBlockDamage().enabled()) registerHandler(EndCrystalBlockDamage(this.mainConf))

    }

    private fun registerHandler(handler: Listener) {
        this.plugin.server.pluginManager.registerEvents(handler, this.plugin)
    }

}