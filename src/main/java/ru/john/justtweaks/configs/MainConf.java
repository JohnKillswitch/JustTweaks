package ru.john.justtweaks.configs;

import space.arim.dazzleconf.annote.*;
import space.arim.dazzleconf.sorter.AnnotationBasedSorter;
@ConfHeader({"Main configuration file."})
public interface MainConf {

    interface Tweaks {
        interface ArrowFired {
            @AnnotationBasedSorter.Order(1)
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("enabled")
            Boolean enabled();

            @ConfComments("Does NOT player`s arrows can invoke fire?")
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("otherSourceTrigger")
            Boolean otherSourceTrigger();

            @ConfComments("Must be a percent value from 1 to 100")
            @ConfDefault.DefaultInteger(20)
            @ConfKey("igniteChance")
            Integer igniteChance();
        }
        @ConfComments("After entity shoot on burnable block that block can be igniting")
        @ConfKey("ArrowFire")
        @SubSection
        ArrowFired arrowFired();

        interface StoneCutterDamage {
            @AnnotationBasedSorter.Order(1)
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("enabled")
            Boolean enabled();

            @ConfComments("Stonecutter damage affect mobs?")
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("mobsTrigger")
            Boolean mobsTrigger();

            @ConfComments("damage of stonecutter")
            @ConfDefault.DefaultInteger(1)
            @ConfKey("damage")
            Integer damage();
        }
        @ConfComments("Stonecutter now can damage if you step on it")
        @ConfKey("StoneCutterDamage")
        @SubSection
        StoneCutterDamage stoneCutterDamage();

        interface CreatePath {
            @AnnotationBasedSorter.Order(1)
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("enabled")
            Boolean enabled();

            @ConfComments("Must be a percent value from 1 to 100")
            @ConfDefault.DefaultInteger(35)
            @ConfKey("changeChance")
            Integer changeChance();
        }
        @ConfComments("Change block under player to create a 'Path'")
        @ConfKey("CreatePath")
        @SubSection
        CreatePath createPath();

        interface AnvilRepair {
            @AnnotationBasedSorter.Order(1)
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("enabled")
            Boolean enabled();

            @ConfComments(
                    {"What item player need to have inhand to repair anvil",
                    "Material list can be found here https://dev.bukkit.org/projects/supplies/pages/material-list"})
            @ConfDefault.DefaultString("IRON_BLOCK")
            @ConfKey("itemMaterial")
            String itemMaterial();

            @ConfComments("how much items player need to repair anvil")
            @ConfDefault.DefaultInteger(1)
            @ConfKey("itemCount")
            Integer itemCount();
        }
        @ConfComments("Repair anvil with certain item by right click")
        @ConfKey("AnvilRepair")
        @SubSection
        AnvilRepair anvilRepair();

        interface AnimalsScary {
            @AnnotationBasedSorter.Order(1)
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("enabled")
            Boolean enabled();

            @ConfComments("in which radius animals will be scared")
            @ConfDefault.DefaultInteger(15)
            @ConfKey("scaryRadius")
            Integer scaryRadius();
        }
        @ConfComments("Animals will scary if you punch animal")
        @ConfKey("AnimalsScary")
        @SubSection
        AnimalsScary animalsScary();

        interface EndCrystalBlockDamage {
            @AnnotationBasedSorter.Order(1)
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("enabled")
            Boolean enabled();
        }
        @ConfComments("Disable or enable ender crystal block damage")
        @ConfKey("EndCrystalBlockDamage")
        @SubSection
        EndCrystalBlockDamage endCrystalBlockDamage();

        interface PiercingForShield {
            @AnnotationBasedSorter.Order(1)
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("enabled")
            Boolean enabled();

            @ConfComments("Does shield of player being blocked or player just taking damage")
            @ConfDefault.DefaultBoolean(true)
            @ConfKey("shieldBlock")
            Boolean shieldBlock();
        }
        @ConfComments("when player get hit from crossbow with piercing enchant shield got blocked")
        @ConfKey("PiercingForShield")
        @SubSection
        PiercingForShield piercingForShield();


//    interface OtherSettings {
//        interface WGSettings {
//            @ConfComments("WorldGuard MUST BE installed for this")
//            @ConfDefault.DefaultBoolean(false)
//            @ConfKey("enabled")
//            Boolean enabled();
//        }
//        @SubSection
//        @ConfKey("WorldGuardSupportSettings")
//        WGSettings wgSettings();
//    }
//    @ConfKey("Other-Settings")
//    @SubSection
    }
    @ConfKey("Tweaks")
    @SubSection
    Tweaks tweaks();
}
