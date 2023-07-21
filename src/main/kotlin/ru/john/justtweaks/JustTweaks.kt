package ru.john.justtweaks

import org.bukkit.plugin.java.JavaPlugin
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf
import space.arim.dazzleconf.ConfigurationOptions

class JustTweaks : JavaPlugin() {
    override fun onEnable() {

        val conf: Configuration<MainConf> = Configuration.create(
            this,
            "tweaks.yml",
            MainConf::class.java,
            ConfigurationOptions.defaults()
        );


        val tweaksRegistrator: TweaksRegistrator = TweaksRegistrator(this, conf)
        tweaksRegistrator.checkHandlers()
    }

    override fun onDisable() {
        // Plugin shutdown logic


        // disable end_crystal block damage
    }
}
