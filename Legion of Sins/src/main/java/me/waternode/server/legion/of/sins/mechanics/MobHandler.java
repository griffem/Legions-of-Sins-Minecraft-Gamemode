package me.waternode.server.legion.of.sins.mechanics;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.chance.Fraction;
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

public class MobHandler implements Listener {

	@EventHandler
	public void CSE1(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.CUSTOM || !e.getLocation().getWorld().getName().equalsIgnoreCase("main"))	return;

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
		if (!e.getDamager().getLocation().getWorld().getName().equalsIgnoreCase("main") ||
				!(e.getDamager() instanceof Player) ||
				!(e.getEntity() instanceof Animals)) return;

		Animals hitEntity = (Animals) e.getEntity();
		if (e.getEntity() instanceof Pig) {
			if (Fraction.getChance(new Fraction(1, 2))) {
				pig2Zombie((Pig)hitEntity);
			}
		}
		if (e.getEntity() instanceof Sheep) {
			if (Fraction.getChance(new Fraction(1, 2))) {
				CaveSpider caveSpider = (CaveSpider) hitEntity.getWorld().spawnEntity(hitEntity.getLocation(), EntityType.CAVE_SPIDER);

                // Potion effect type, 0 - (Integer - 1) levels of that potion you can get
				Map<PotionEffectType, Integer> potionEffectTypeIntegerMap = new HashMap<PotionEffectType, Integer>() {
					{
						// Put all potions and the range for the random integer in hashmap
						put(PotionEffectType.SPEED, 3);
						put(PotionEffectType.INCREASE_DAMAGE, 2);
						put(PotionEffectType.DAMAGE_RESISTANCE, 2);
						put(PotionEffectType.REGENERATION, 3);

					}
				};

				// Turns into potion effect
				List<PotionEffect> ps = new ArrayList<PotionEffect>();
				for(PotionEffectType potionEffectType : potionEffectTypeIntegerMap.keySet()) ps.add(new InfinitePotionEffect(potionEffectType, LOSMain.getRandom().nextInt(potionEffectTypeIntegerMap.get(potionEffectType))));

				// Add Potion Effect
				caveSpider.addPotionEffects(ps);

				playSoundAt(hitEntity, Sound.SPIDER_DEATH);
				hitEntity.setHealth(0);
			}
			// Return as if the Entity is an instance of a sheep it's not also going to be an instance of a chicken :P
			return;
		}
		if (e.getEntity() instanceof Chicken) {
			if (Fraction.getChance(new Fraction(1, 4))) {
				// l = Amounts of times to repeat primed tnt
				for(Integer i = 0, l = 6; i < l; i++) {
					e.getDamager().getWorld().spawnEntity(e.getDamager().getLocation(), EntityType.PRIMED_TNT);
				}

				playSoundAt(hitEntity, Sound.BURP);
				hitEntity.setHealth(0);
			}
			// Return as if the Entity is an instance of a Chicken it's not also going to be an instance of a Cow :P
			return;
		}
		if (e.getEntity() instanceof Cow) {
			if (Fraction.getChance(new Fraction(1, 2))) {
				Random r = LOSMain.getRandom();
				if (Fraction.getChance(new Fraction(1, 2))) {
					CaveSpider i = (CaveSpider) hitEntity.getWorld().spawnEntity(hitEntity.getLocation(), EntityType.CAVE_SPIDER);
					List<PotionEffect> ps = new ArrayList<PotionEffect>();
					ps.add(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(3)));
					ps.add(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(2)));
					ps.add(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(2)));
					ps.add(new InfinitePotionEffect(PotionEffectType.REGENERATION, r.nextInt(3)));
					i.addPotionEffects(ps);
					playSoundAt(hitEntity, Sound.SPIDER_DEATH);
				} else {
					Zombie i = (Zombie) hitEntity.getWorld().spawnEntity(hitEntity.getLocation(), EntityType.ZOMBIE);
					List<PotionEffect> ps = new ArrayList<PotionEffect>();
					ps.add(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(2)));
					ps.add(new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(3)));
					ps.add(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(3)));
					ps.add(new InfinitePotionEffect(PotionEffectType.REGENERATION, r.nextInt(1)));
					ps.add(new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1));
					i.addPotionEffects(ps);
					playSoundAt(hitEntity, Sound.ZOMBIE_DEATH);
				}
				hitEntity.setHealth(0);
			}
			// No Return this time as it is last statement in the method ;)
		}
	}

	/**
	 * Convenience Method for the hitEntity in the events used in this class
	 * @param entity ~ The entity to play the sound at the location of
	 * @param sound ~ The sound to play
	 */
	private static void playSoundAt(Entity entity, Sound sound) {
		entity.getWorld().playSound(entity.getLocation(), sound, 2, 1);
	}

	/**
	 * Convenience Method for turning the pig into a zombie and playing other, "cool", effects
	 * @param entity The pig to turn into a zombie
	 */
	private static void pig2Zombie(Pig entity) {
		Zombie zombie = (Zombie) entity.getWorld().spawnEntity( entity.getLocation(), EntityType.ZOMBIE);

		// Potion effect type, 0 - (Integer - 1) levels of that potion you can get
		Map<PotionEffectType, Integer> potionEffectTypeIntegerMap = new HashMap<PotionEffectType, Integer>() {
			{
				// Put all potions and the range for the random integer in hashmap
				put(PotionEffectType.SPEED, 2);
				put(PotionEffectType.INCREASE_DAMAGE, 3);
				put(PotionEffectType.DAMAGE_RESISTANCE, 3);
				put(PotionEffectType.REGENERATION, 1);
				put(PotionEffectType.FIRE_RESISTANCE, 1);
			}
		};

		// Turns into potion effect
		List<PotionEffect> ps = new ArrayList<PotionEffect>();
		for(PotionEffectType potionEffectType : potionEffectTypeIntegerMap.keySet()) ps.add(new InfinitePotionEffect(potionEffectType, LOSMain.getRandom().nextInt(potionEffectTypeIntegerMap.get(potionEffectType))));

		// Add Potion effects to Zombie
		zombie.addPotionEffects(ps);

		playSoundAt( entity, Sound.ZOMBIE_DEATH);
	    entity.setHealth(0);
	}
}