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

        e.setCancelled(true);
    }
}
