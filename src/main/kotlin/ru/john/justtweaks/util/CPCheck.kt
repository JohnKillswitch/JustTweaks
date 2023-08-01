package ru.john.justtweaks.util

import net.coreprotect.CoreProtect
import net.coreprotect.CoreProtectAPI
import org.bukkit.Bukkit.getServer
import org.bukkit.plugin.Plugin


class CPCheck {
    fun getCP(): CoreProtectAPI? {
        val plugin = getServer().pluginManager.getPlugin("CoreProtect") as? CoreProtect ?: return null
        return if (plugin.isEnabled) plugin.api else null
    }

}