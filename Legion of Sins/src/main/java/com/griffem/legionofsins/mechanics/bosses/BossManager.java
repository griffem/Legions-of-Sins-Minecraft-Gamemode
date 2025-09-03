package com.griffem.legionofsins.mechanics.bosses;

import com.griffem.legionofsins.LOSMain;
import com.griffem.legionofsins.mechanics.bosses.abilities.AbilityType;
import com.griffem.legionofsins.mechanics.bosses.abilities.AbilitySpec;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class BossManager extends BukkitRunnable implements Listener {
    private static final String DEATHWORLD = "deathworld";
    private static final int ARENA_MIN = -10;
    private static final int ARENA_MAX = 10;
    private static final int ARENA_Y = 200;

    private final LOSMain main;

    private int mainCD = 0;
    private int skeleCD = 0;
    private int cowCD = 0;

    public BossManager(LOSMain p) {
        this.main = p;
        buildArena();
    }

    @Override
    public void run() {
        List<Player> ps = main.getServer().getOnlinePlayers().stream()
                .filter(p -> p.getWorld().getName().toLowerCase().contains(DEATHWORLD))
                .toList();

        if(mainCD > 0) mainCD--;
        if(skeleCD > 0) skeleCD--;
        if(cowCD > 0) cowCD--;
        if (ps.size() <= 0) return;

        PlayerBuckets buckets = categorizePlayers(ps);
        if (!buckets.center().isEmpty() && mainCD == 0) finalBoss(buckets.center().get(LOSMain.getRandom().nextInt(buckets.center().size())));
        if (!buckets.first().isEmpty() && skeleCD == 0) generalBoss(buckets.first().get(LOSMain.getRandom().nextInt(buckets.first().size())));
        if (!buckets.second().isEmpty() && cowCD == 0) commanderBoss(buckets.second().get(LOSMain.getRandom().nextInt(buckets.second().size())));
        if (!buckets.third().isEmpty()) regularBoss(buckets.third().get(LOSMain.getRandom().nextInt(buckets.third().size())));
    }
    public void finalBoss(Player p) {
        LivingEntity b = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
        MagmaCube boss = (MagmaCube) b.getWorld().spawnEntity(b.getLocation(), EntityType.MAGMA_CUBE);
        boss.setSize(2);
        List<Ability> abs = createAbilities(b,
                new AbilitySpec(AbilityType.CULTOFTHEFEATHER, 20),
                new AbilitySpec(AbilityType.SEWERSWARM, 20),
                new AbilitySpec(AbilityType.SMITE, 10),
                new AbilitySpec(AbilityType.WITHER, 10),
                new AbilitySpec(AbilityType.ZOMBIESIEGE, 20));
        new FinalBoss(b, boss, abs, main).runTaskTimer(main, 0L, 1L);
        mainCD += 8;
    }

    public void generalBoss(Player p) {
        LivingEntity b = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
        LivingEntity boss = (LivingEntity) b.getWorld().spawnEntity(b.getLocation(), EntityType.SKELETON);
        List<Ability> abs = createAbilities(b,
                new AbilitySpec(AbilityType.SEWERSWARM, 30),
                new AbilitySpec(AbilityType.SMITE, 10),
                new AbilitySpec(AbilityType.WITHER, 15),
                new AbilitySpec(AbilityType.ZOMBIESIEGE, 30));
        new General(b, boss, abs, main).runTaskTimer(main, 0L, 1L);
        skeleCD += 4;
    }

    public void commanderBoss(Player p) {
        LivingEntity b = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
        LivingEntity boss = (LivingEntity) b.getWorld().spawnEntity(b.getLocation(), EntityType.MOOSHROOM);
        List<Ability> abs = createAbilities(b,
                new AbilitySpec(AbilityType.CULTOFTHEFEATHER, 20),
                new AbilitySpec(AbilityType.SEWERSWARM, 30),
                new AbilitySpec(AbilityType.SMITE, 10),
                new AbilitySpec(AbilityType.WITHER, 15));
        new Commander(b, boss, abs, main).runTaskTimer(main, 0L, 1L);
        cowCD += 2;
    }

    public void regularBoss(Player p) {
        int i = LOSMain.getRandom().nextInt(3);
        switch (i) {
            case 0 -> {
                LivingEntity b = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                LivingEntity boss = (LivingEntity) b.getWorld().spawnEntity(b.getLocation(), EntityType.PIG);
                List<Ability> abs = createAbilities(b,
                        new AbilitySpec(AbilityType.CULTOFTHEFEATHER, 20),
                        new AbilitySpec(AbilityType.SMITE, 5),
                        new AbilitySpec(AbilityType.WITHER, 10));
                new Captain(b, boss, abs, main).runTaskTimer(main, 0L, 1L);
            }
            case 1 -> {
                LivingEntity b1 = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                LivingEntity boss1 = (LivingEntity) b1.getWorld().spawnEntity(b1.getLocation(), EntityType.ZOMBIFIED_PIGLIN);
                List<Ability> abs1 = createAbilities(b1,
                        new AbilitySpec(AbilityType.SMITE, 10),
                        new AbilitySpec(AbilityType.WITHER, 10));
                new Lieutenant(b1, boss1, abs1, main).runTaskTimer(main, 0L, 1L);
            }
            case 2 -> {
                LivingEntity b2 = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
                LivingEntity boss2 = (LivingEntity) b2.getWorld().spawnEntity(b2.getLocation(), EntityType.WITCH);
                List<Ability> abs2 = createAbilities(b2,
                        new AbilitySpec(AbilityType.SMITE, 5),
                        new AbilitySpec(AbilityType.WITHER, 10));
                new Officer(b2, boss2, abs2, main).runTaskTimer(main, 0L, 1L);
            }
        }
    }

    private List<Ability> createAbilities(LivingEntity boss, AbilitySpec... specs) {
        List<Ability> abilities = new ArrayList<>();
        for (AbilitySpec spec : specs) {
            abilities.add(new Ability(spec.type(), spec.cooldown(), main, boss, boss.getWorld(), 30));
        }
        return abilities;
    }

    private PlayerBuckets categorizePlayers(List<Player> players) {
        List<Player> center = new ArrayList<>();
        List<Player> first = new ArrayList<>();
        List<Player> second = new ArrayList<>();
        List<Player> third = new ArrayList<>();

        for (Player p : players) {
            double x = p.getLocation().getX();
            double z = p.getLocation().getZ();
            if (x < 10 && x > -10 && z < 10 && z > -10) {
                center.add(p);
            } else if (x < 200 && x > -200 && z < 200 && z > -200) {
                first.add(p);
            } else if (x < 500 && x > -500 && z < 500 && z > -500) {
                second.add(p);
            } else if (x < 750 && x > -750 && z < 750 && z > -750) {
                third.add(p);
            }
        }

        return new PlayerBuckets(center, first, second, third);
    }

    private record PlayerBuckets(List<Player> center, List<Player> first, List<Player> second, List<Player> third) {
    }

    @EventHandler
    public void onCombust(EntityCombustEvent e) {
        if(e.getEntity().getWorld().getName().equalsIgnoreCase(DEATHWORLD)) {
            if(e.getEntityType() == EntityType.ZOMBIE) {
                e.setCancelled(true);
            }
        }
    }

    private void buildArena() {
        var world = Bukkit.getWorld(DEATHWORLD);
        if (world == null) {
            return;
        }
        Location l = world.getBlockAt(ARENA_MIN, ARENA_Y, ARENA_MIN).getLocation();
        for (int x = ARENA_MIN; x <= ARENA_MAX; x++) {
            l.setX(x);
            for (int z = ARENA_MIN; z <= ARENA_MAX; z++) {
                l.setZ(z);
                boolean onBorder = ((x > ARENA_MIN && x < ARENA_MAX) && (z == ARENA_MIN || z == ARENA_MAX)) ||
                                   ((x == ARENA_MIN || x == ARENA_MAX) && (z > ARENA_MIN && z < ARENA_MAX));
                if (onBorder) {
                    l.getBlock().setType(Material.GRAVEL);
                }
            }
        }
    }
}
