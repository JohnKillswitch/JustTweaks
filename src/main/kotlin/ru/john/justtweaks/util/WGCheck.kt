package ru.john.justtweaks.util

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.protection.regions.RegionContainer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin


class WGCheck {

    fun isPlayerNotInAnyRegion(player: Player): Boolean {
        val pluginManager = Bukkit.getPluginManager()

        pluginManager.getPlugin("WorldGuard") ?: return true

        val container: RegionContainer = WorldGuard.getInstance().platform.regionContainer
        val regions = container[BukkitAdapter.adapt(player.world)]
        val location = player.location


        val applicableRegions = regions?.getApplicableRegions(BlockVector3.at(location.x, location.y, location.z))

        return (applicableRegions?.size() ?: 0) == 0
    }
}