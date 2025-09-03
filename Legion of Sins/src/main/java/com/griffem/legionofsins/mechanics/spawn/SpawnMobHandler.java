package com.griffem.legionofsins.mechanics.spawn;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by Ervin
 * on 4/11/2014
 */
public class SpawnMobHandler implements Listener {

    @EventHandler
    public void CSE(CreatureSpawnEvent e) {
        if(e.getLocation().getWorld().getName().contains("spawn")) {
            if(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) {
            } else {
                e.setCancelled(true);
            }
        }
    }

    public void PDE(EntityDamageEvent e) {
        if(e.getEntity().getWorld().getName().contains("spawn")) {
            Entity ee = e.getEntity();
            if(ee instanceof Player) {
                e.setCancelled(true);
                ((Player) e.getEntity()).setHealth(20);
            }
        }
    }

    public void PLH(FoodLevelChangeEvent e) {
        if(e.getEntity().getWorld().getName().contains("spawn")) {
            if(e.getEntity().getType() == EntityType.PLAYER) {
                if(e.getFoodLevel() > 20) {
                    e.setFoodLevel(20);
                    e.getEntity().setHealth(20);
                }
            }
        }
    }
}
