package ru.john.justtweaks.handlers

import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.block.data.Waterlogged
import org.bukkit.block.data.type.Fire
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import ru.john.justtweaks.configs.Configuration
import ru.john.justtweaks.configs.MainConf
import ru.john.justtweaks.util.CPCheck
import ru.john.justtweaks.util.WGCheck
import java.util.*

class ArrowFired (private val mainConf: Configuration<MainConf> ) : Listener {

    @EventHandler
    fun checkEvent (event: ProjectileHitEvent) {

        val tweakData = this.mainConf.data().tweaks().arrowFired()
        val entity = event.entity
        val blockFace = event.hitBlockFace
        val hitBlock = event.hitBlock ?: return

        val blacklist = mainConf.data().tweaks().arrowFired().blacklist()
        if (blacklist.any { it.equals(entity.world.name, ignoreCase = true) }) return


        if (entity.shooter !is LivingEntity && !tweakData.otherSourceTrigger()) return
        if (entity.type != EntityType.ARROW || entity.fireTicks <0) return

        if (entity.shooter is Player && !WGCheck().isPlayerNotInAnyRegion(entity.shooter as Player)) return

        if (!event.hitBlock!!.type.isBurnable) return

        if (hitBlock is Waterlogged && hitBlock.isWaterlogged) return

        val firedBlock = event.hitBlock!!.getRelative(event.hitBlockFace!!)
        if (firedBlock.type != Material.AIR) return

        if (Random().nextInt(100 / tweakData.igniteChance() + 1) != 1) return

        firedBlock.type = Material.FIRE

        if (firedBlock.type != Material.FIRE) return

        val data = firedBlock.blockData as Fire
        if (blockFace!!.oppositeFace == BlockFace.DOWN) return
        data.setFace(blockFace.oppositeFace, true)
        firedBlock.blockData = data
        val entityName = when (entity.shooter) {
            is Player -> (entity.shooter as Player).name
            is LivingEntity -> "#${(entity.shooter as LivingEntity).name}"
            else -> null
        }

        CPCheck().getCP()?.logPlacement(entityName, firedBlock.location, firedBlock.type, null)
    }

}