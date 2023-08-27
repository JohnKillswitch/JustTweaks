package ru.john.justtweaks.handlers

import org.bukkit.EntityEffect
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.util.Vector
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf
import java.util.*


class PiercingForShield(
    private val mainConf: Configuration<MainConf>,
    private val shooterMap: WeakHashMap<UUID, SavedArrow>,
) : Listener {

    companion object {
        val POWER_MAP = mapOf(
            1 to 0.0,
            2 to 0.2,
            3 to 0.4,
            4 to 0.6
        )
    }

    @EventHandler
    fun checkEvent (event: EntityShootBowEvent) {
        if(event.projectile !is Arrow) return
        if ((event.bow?.getEnchantmentLevel(Enchantment.PIERCING) ?: 0) == 0) return

        val arrow = event.projectile as Arrow

        val blacklist = mainConf.data().tweaks().piercingForShield().blacklist()
        if (blacklist.any { it.equals(arrow.world.name, ignoreCase = true) }) return

        this.shooterMap[event.projectile.uniqueId] = SavedArrow(arrow.damage, arrow.pierceLevel, arrow.velocity)

    }

    @EventHandler
    fun checkEvent2 (event: ProjectileHitEvent) {
        if (event.hitEntity !is Player) return
        if (event.entity !is Arrow) return

        val player = event.hitEntity as Player
        val arrow = this.shooterMap[event.entity.uniqueId] ?: return

        when {
            player.inventory.itemInMainHand.type == Material.SHIELD || player.inventory.itemInOffHand.type == Material.SHIELD -> {
                player.playEffect(EntityEffect.SHIELD_BREAK)
                if (mainConf.data().tweaks().piercingForShield().shieldBlock()) player.setCooldown(Material.SHIELD, (arrow.enchantLevel + 1) * 20)
                player.clearActiveItem()
            }
            else -> return
        }
        player.damage(arrow.arrowDamage)
        player.velocity = arrow.vector.subtract(player.velocity.normalize()).setY(0.2).multiply((POWER_MAP[arrow.enchantLevel] ?: 0.0))
    }


    class SavedArrow(
        val arrowDamage: Double,
        val enchantLevel: Int,
        val vector: Vector
    )
}