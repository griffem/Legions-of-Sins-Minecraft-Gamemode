package com.griffem.legionofsins.mechanics.EndGame;

import com.griffem.legionofsins.InfinitePotionEffect;
import com.griffem.legionofsins.LOSMain;
import com.griffem.legionofsins.chance.Fraction;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class EndGameMobHandler implements Listener {

	@EventHandler
	public void CSE1(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.CUSTOM || !e.getLocation().getWorld().getName().equalsIgnoreCase("endgame"))	return;

		Random r = new Random();
		if (e.getEntityType() == EntityType.CREEPER) {
			e.setCancelled(true);
		} else if (e.getEntityType() == EntityType.ZOMBIE) {
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(3)));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(1)));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(1)));
        } else if (e.getEntityType() == EntityType.SPIDER) {
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(1)));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(2)));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(2)));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.REGENERATION, r.nextInt(2)));

        } else if(e.getEntityType() == EntityType.SKELETON) {
			e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1));
			e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(2)));
			e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(1)));
            e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(1)));
		} else if (e.getEntityType() == EntityType.ENDERMAN) {
			e.setCancelled(true);
		}
	}
}