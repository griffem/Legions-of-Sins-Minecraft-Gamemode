package com.griffem.legionofsins.mechanics;

import com.griffem.legionofsins.InfinitePotionEffect;
import com.griffem.legionofsins.LOSMain;
import com.griffem.legionofsins.chance.Fraction;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Handles mob spawning and transformation mechanics.
 */
public class MobHandler implements Listener {

    /**
     * Applies custom spawn logic for hostile mobs in the main world.
     */
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (e.getSpawnReason() == SpawnReason.CUSTOM ||
            !e.getLocation().getWorld().getName().equalsIgnoreCase("main")) {
            return;
        }

        ThreadLocalRandom r = ThreadLocalRandom.current();
        switch (e.getEntityType()) {
            case CREEPER -> handleCreeperSpawn(e, r);
            case ZOMBIE, SPIDER, SKELETON -> buffHostileMob(e.getEntity(), r, e.getLocation());
            case ENDERMAN -> e.setCancelled(true);
            default -> {
            }
        }
    }

    /**
     * Handles animal mutations when struck by a player.
     */
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!e.getDamager().getLocation().getWorld().getName().equalsIgnoreCase("main") ||
            !(e.getDamager() instanceof Player) ||
            !(e.getEntity() instanceof Animals hitEntity)) {
            return;
        }

        if (hitEntity instanceof Pig pig) {
            if (Fraction.HALF.roll()) {
                pig2Zombie(pig);
            }
            return;
        }

        if (hitEntity instanceof Sheep sheep) {
            if (Fraction.HALF.roll()) {
                spawnBuffedCaveSpider(sheep);
                playSoundAt(sheep, Sound.ENTITY_SPIDER_DEATH);
                sheep.setHealth(0);
            }
            return;
        }

        if (hitEntity instanceof Chicken chicken) {
            if (Fraction.QUARTER.roll()) {
                spawnPrimedTnt(chicken.getLocation(), 6);
                playSoundAt(chicken, Sound.ENTITY_PLAYER_BURP);
                chicken.setHealth(0);
            }
            return;
        }

        if (hitEntity instanceof Cow cow) {
            if (Fraction.HALF.roll()) {
                transformCow(cow);
                cow.setHealth(0);
            }
        }
    }

    /**
     * Creepers never spawn naturally; instead we replace them with other threats.
     * Each cancelled creeper spawn attempts two replacement rolls:
     * <ul>
     *   <li>1⁄32 chance for a ghast above the spawn point</li>
     *   <li>~1⁄32 chance for a blaze above the spawn point</li>
     *   <li>Otherwise, a small group of silverfish at ground level</li>
     * </ul>
     */
    private void handleCreeperSpawn(CreatureSpawnEvent e, ThreadLocalRandom r) {
        e.setCancelled(true);
        Location loc = e.getLocation();
        for (int roll = 0; roll < 2; roll++) {
            spawnReplacementMob(loc, r);
        }
    }

    /**
     * Decides which mob to spawn instead of a creeper.
     */
    private void spawnReplacementMob(Location loc, ThreadLocalRandom r) {
        int chance = r.nextInt(32);
        if (chance == 0) {
            // Very rare: ghast floating above
            spawnAbove(loc, EntityType.GHAST);
        } else if (chance <= 10 && r.nextInt(10) == 0) {
            // Uncommon: blaze positioned above
            spawnAbove(loc, EntityType.BLAZE);
        } else {
            // Default: silverfish swarm on the ground
            spawnSilverfishGroup(loc, r);
        }
    }

    private void buffHostileMob(LivingEntity entity, ThreadLocalRandom r, Location spawnLoc) {
        entity.addPotionEffect(new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1));
        entity.addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(2)));
        entity.addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(2)));

        if (r.nextBoolean()) {
            LivingEntity partner = (LivingEntity) spawnLoc.getWorld().spawnEntity(spawnLoc, entity.getType());
            partner.addPotionEffect(new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1));
            partner.addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(2)));
            partner.addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(2)));
            partner.addPotionEffect(new InfinitePotionEffect(PotionEffectType.WEAKNESS, r.nextInt(1)));
        }
    }

    /**
     * Spawns the given entity 10 blocks above the highest ground at the base location.
     */
    private void spawnAbove(Location base, EntityType type) {
        Location highest = base.getWorld().getHighestBlockAt(base).getLocation();
        highest.add(0, 10, 0);
        base.getWorld().spawnEntity(highest, type);
    }

    /**
     * Spawns one or more silverfish at the given location.
     * There is a small chance to spawn an extra pair on top of the first.
     */
    private void spawnSilverfishGroup(Location loc, ThreadLocalRandom r) {
        World world = loc.getWorld();
        world.spawnEntity(loc, EntityType.SILVERFISH);
        if (r.nextBoolean()) {
            // 50% chance for at least one extra
            world.spawnEntity(loc, EntityType.SILVERFISH);
            if (r.nextInt(5) == 0) {
                // Occasionally spawn a third
                world.spawnEntity(loc, EntityType.SILVERFISH);
            }
        }
    }

    private void spawnBuffedCaveSpider(Animals origin) {
        CaveSpider caveSpider = (CaveSpider) origin.getWorld().spawnEntity(origin.getLocation(), EntityType.CAVE_SPIDER);
        Map<PotionEffectType, Integer> effects = Map.of(
            PotionEffectType.SPEED, 3,
            PotionEffectType.INCREASE_DAMAGE, 2,
            PotionEffectType.DAMAGE_RESISTANCE, 2,
            PotionEffectType.REGENERATION, 3
        );
        List<PotionEffect> ps = effects.entrySet().stream()
            .map(pe -> new InfinitePotionEffect(pe.getKey(), LOSMain.getRandom().nextInt(pe.getValue())))
            .toList();
        caveSpider.addPotionEffects(ps);
    }

    private void spawnPrimedTnt(Location loc, int count) {
        World world = loc.getWorld();
        for (int i = 0; i < count; i++) {
            world.spawnEntity(loc, EntityType.PRIMED_TNT);
        }
    }

    private void transformCow(Cow cow) {
        ThreadLocalRandom r = LOSMain.getRandom();
        if (Fraction.HALF.roll()) {
            CaveSpider spider = (CaveSpider) cow.getWorld().spawnEntity(cow.getLocation(), EntityType.CAVE_SPIDER);
            List<PotionEffect> ps = List.of(
                new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(3)),
                new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(2)),
                new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(2)),
                new InfinitePotionEffect(PotionEffectType.REGENERATION, r.nextInt(3))
            );
            spider.addPotionEffects(ps);
            playSoundAt(cow, Sound.ENTITY_SPIDER_DEATH);
        } else {
            Zombie zombie = (Zombie) cow.getWorld().spawnEntity(cow.getLocation(), EntityType.ZOMBIE);
            List<PotionEffect> ps = List.of(
                new InfinitePotionEffect(PotionEffectType.SPEED, r.nextInt(2)),
                new InfinitePotionEffect(PotionEffectType.INCREASE_DAMAGE, r.nextInt(3)),
                new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, r.nextInt(3)),
                new InfinitePotionEffect(PotionEffectType.REGENERATION, r.nextInt(1)),
                new InfinitePotionEffect(PotionEffectType.FIRE_RESISTANCE, 1)
            );
            zombie.addPotionEffects(ps);
            playSoundAt(cow, Sound.ENTITY_ZOMBIE_DEATH);
        }
    }

    /**
     * Convenience method for playing a sound at an entity's location.
     */
    private static void playSoundAt(Entity entity, Sound sound) {
        entity.getWorld().playSound(entity.getLocation(), sound, 2, 1);
    }

    /**
     * Turns a pig into a zombie with random potion effects.
     */
    private static void pig2Zombie(Pig entity) {
        Zombie zombie = (Zombie) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ZOMBIE);

        Map<PotionEffectType, Integer> effects = Map.of(
            PotionEffectType.SPEED, 2,
            PotionEffectType.INCREASE_DAMAGE, 3,
            PotionEffectType.DAMAGE_RESISTANCE, 3,
            PotionEffectType.REGENERATION, 1,
            PotionEffectType.FIRE_RESISTANCE, 1
        );

        List<PotionEffect> ps = effects.entrySet().stream()
            .map(pe -> new InfinitePotionEffect(pe.getKey(), LOSMain.getRandom().nextInt(pe.getValue())))
            .toList();

        zombie.addPotionEffects(ps);

        playSoundAt(entity, Sound.ENTITY_ZOMBIE_DEATH);
        entity.setHealth(0);
    }
}

