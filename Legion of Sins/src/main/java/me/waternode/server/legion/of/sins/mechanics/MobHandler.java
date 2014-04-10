package me.waternode.server.legion.of.sins.mechanics;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
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

import java.util.ArrayList;
import java.util.Random;

public class MobHandler implements Listener {

	@EventHandler
	public void CSE1(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.CUSTOM || !e.getLocation().getWorld().getName().equalsIgnoreCase("main")) {
			return;
		}

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
			e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(2)));
			e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(2)));
			e.getEntity().addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(2)));
			if (r.nextBoolean()) {
				LivingEntity atk = (LivingEntity) e.getLocation().getWorld().spawnEntity(e.getLocation(), e.getEntityType());
				atk.addPotionEffect(new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1));
				atk.addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(2)));
				atk.addPotionEffect(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(2)));
				atk.addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(2)));
			}
		} else if (e.getEntityType() == EntityType.ENDERMAN) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		Random r = new Random();
		if (!e.getDamager().getLocation().getWorld().getName().equalsIgnoreCase("main")) {
			return;
		}

		if (e.getEntity() instanceof Pig && e.getDamager() instanceof Player) {
			Pig mob = (Pig) e.getEntity();
			if (r.nextBoolean()) {
				Zombie i = (Zombie) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.ZOMBIE);
				ArrayList<PotionEffect> ps = new ArrayList<PotionEffect>();
				ps.add(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(2)));
				ps.add(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(3)));
				ps.add(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(3)));
				ps.add(new InfinitePotionEffect(PotionEffectType.REGENERATION, r.nextInt(1)));
				ps.add(new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1));
				i.addPotionEffects(ps);
				mob.getWorld().playSound(mob.getLocation(), Sound.ZOMBIE_DEATH, 2, 1);
				mob.setHealth(0);
			}
		} else if (e.getEntity() instanceof Sheep && e.getDamager() instanceof Player) {
			Sheep mob = (Sheep) e.getEntity();
			if (r.nextBoolean()) {
				CaveSpider i = (CaveSpider) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.CAVE_SPIDER);
				ArrayList<PotionEffect> ps = new ArrayList<PotionEffect>();
				ps.add(new PotionEffect(PotionEffectType.SPEED, 36000, r.nextInt(3)));
				ps.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 36000, r.nextInt(2)));
				ps.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 36000, r.nextInt(2)));
				ps.add(new PotionEffect(PotionEffectType.REGENERATION, 36000, r.nextInt(3)));
				i.addPotionEffects(ps);
				mob.getWorld().playSound(mob.getLocation(), Sound.SPIDER_DEATH, 2, 1);
				mob.setHealth(0);
			}
		} else if (e.getEntity() instanceof Chicken && e.getDamager() instanceof Player) {
			Chicken mob = (Chicken) e.getEntity();
			if (r.nextInt(3) == 0) {
				e.getDamager().getWorld().spawnEntity(e.getDamager().getLocation(), EntityType.PRIMED_TNT);
				e.getDamager().getWorld().spawnEntity(e.getDamager().getLocation(), EntityType.PRIMED_TNT);
				e.getDamager().getWorld().spawnEntity(e.getDamager().getLocation(), EntityType.PRIMED_TNT);
				e.getDamager().getWorld().spawnEntity(e.getDamager().getLocation(), EntityType.PRIMED_TNT);
				e.getDamager().getWorld().spawnEntity(e.getDamager().getLocation(), EntityType.PRIMED_TNT);
				e.getDamager().getWorld().spawnEntity(e.getDamager().getLocation(), EntityType.PRIMED_TNT);
				mob.getWorld().playSound(mob.getLocation(), Sound.BURP, 2, 1);
				mob.setHealth(0);
			}
		} else if (e.getEntity() instanceof Cow && e.getDamager() instanceof Player) {
			Cow mob = (Cow) e.getEntity();
			if (r.nextBoolean()) {
				if (r.nextBoolean()) {
					CaveSpider i = (CaveSpider) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.CAVE_SPIDER);
					ArrayList<PotionEffect> ps = new ArrayList<PotionEffect>();
					ps.add(new PotionEffect(PotionEffectType.SPEED, 36000, r.nextInt(3)));
					ps.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 36000, r.nextInt(2)));
					ps.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 36000, r.nextInt(2)));
					ps.add(new PotionEffect(PotionEffectType.REGENERATION, 36000, r.nextInt(3)));
					i.addPotionEffects(ps);
					mob.getWorld().playSound(mob.getLocation(), Sound.SPIDER_DEATH, 2, 1);
				} else {
					Zombie i = (Zombie) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.ZOMBIE);
					ArrayList<PotionEffect> ps = new ArrayList<PotionEffect>();
					ps.add(new PotionEffect(PotionEffectType.SPEED, 36000, r.nextInt(2)));
					ps.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 36000, r.nextInt(3)));
					ps.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 36000, r.nextInt(3)));
					ps.add(new PotionEffect(PotionEffectType.REGENERATION, 36000, r.nextInt(1)));
					ps.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 36000, 1));
					i.addPotionEffects(ps);
					mob.getWorld().playSound(mob.getLocation(), Sound.ZOMBIE_DEATH, 2, 1);
				}
				mob.setHealth(0);
			}
		}
	}
}