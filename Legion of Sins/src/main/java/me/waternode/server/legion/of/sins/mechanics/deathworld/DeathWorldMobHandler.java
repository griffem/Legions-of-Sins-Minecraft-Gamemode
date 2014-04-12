package me.waternode.server.legion.of.sins.mechanics.deathworld;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.chance.Fraction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class DeathWorldMobHandler implements Listener {

    @EventHandler
    public void CSE1(CreatureSpawnEvent e) {
        if (e.getSpawnReason() == SpawnReason.CUSTOM || !e.getLocation().getWorld().getName().equalsIgnoreCase("deathworld"))	return;

        Random r = new Random();
        if (e.getEntityType() == EntityType.CREEPER) {
            e.setCancelled(true);
            for (int a = 0; a < 2; a++) {
                int i = r.nextInt(32);
                if (i == 0) {
                    Location l = e.getLocation().getWorld().getHighestBlockAt(e.getLocation()).getLocation();
                    l.add(0, 10, 0);
                    e.getLocation().getWorld().spawnEntity(l, EntityType.GHAST);
                } else if (i <= 10) {
                    Location l = e.getLocation().getWorld().getHighestBlockAt(e.getLocation()).getLocation();
                    l.add(0, 10, 0);
                    if (r.nextInt(10) == 0) {
                        e.getLocation().getWorld().spawnEntity(l, EntityType.BLAZE);
                    }
                } else {
                    if (r.nextBoolean()) {
                        e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH);
                        if (r.nextInt(5) == 0) {
                            e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH);
                            e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH);
                        }
                    } else {
                        e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH);
                        e.getLocation().getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH);
                    }
                }
            }
        } else if (e.getEntityType() == EntityType.ZOMBIE
                || e.getEntityType() == EntityType.SPIDER
                || e.getEntityType() == EntityType.SKELETON) {
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(3)));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(3)));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(3)));
            if (r.nextBoolean()) {
                LivingEntity atk = (LivingEntity) e.getLocation().getWorld().spawnEntity(e.getLocation(), e.getEntityType());
                atk.addPotionEffect(new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1));
                atk.addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(3)));
                atk.addPotionEffect(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(3)));
                atk.addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(3)));
            }
        } else if (e.getEntityType() == EntityType.ENDERMAN) {
            e.setCancelled(true);
        }
    }

    //Don't know where to add this, this gives the skeletons that spawn an item in there hand.
    public void equipSkeleton(Skeleton skeleton) {
        skeleton.getEquipment().setItemInHand(new ItemStack(Material.BOW));
        skeleton.setSkeletonType(Skeleton.SkeletonType.NORMAL);
    }
}
