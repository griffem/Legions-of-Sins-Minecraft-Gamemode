package me.waternode.server.legion.of.sins.mechanics.bosses;

import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.mechanics.bosses.abilities.AbilityType;
import me.waternode.server.legion.of.sins.mechanics.events.*;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Emery
 * Date: 4/13/14
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class BossManager extends BukkitRunnable {
    private final LOSMain main;

    public BossManager(LOSMain p) {
        this.main = p;
    }

    public void run() {
        // Use lowest interface possible
        ArrayList<Player> ps = new ArrayList<Player>();
        for (Player p : main.getServer().getOnlinePlayers())
            if (p.getWorld().getName().toLowerCase().contains("deathworld")) ps.add(p);

        if (ps.size() <= 0) return;

        ArrayList<Player> center = new ArrayList<Player>();
        ArrayList<Player> first = new ArrayList<Player>();
        ArrayList<Player> second = new ArrayList<Player>();
        ArrayList<Player> third = new ArrayList<Player>();

        for (Player p : ps) {
            if((p.getLocation().getX() < 10 || p.getLocation().getX() > -10)
                    && (p.getLocation().getZ() < 10 || p.getLocation().getZ() > -10)) {
                center.add(p);
            } else if((p.getLocation().getX() < 200 || p.getLocation().getX() > -200)
                    && (p.getLocation().getZ() < 200 || p.getLocation().getZ() > -200)) {
                first.add(p);
            } else if((p.getLocation().getX() < 500 || p.getLocation().getX() > -500)
                    && (p.getLocation().getZ() < 500 || p.getLocation().getZ() > -500)) {
                second.add(p);
            } else if((p.getLocation().getX() < 750 || p.getLocation().getX() > -750)
                    && (p.getLocation().getZ() < 750 || p.getLocation().getZ() > -750)) {
                third.add(p);
            }
        }
        if (center.size() > 0) FinalBoss(center.get(LOSMain.getRandom().nextInt(center.size())));
        if (first.size() > 0) FinalBoss(first.get(LOSMain.getRandom().nextInt(first.size())));
        if (second.size() > 0) FinalBoss(second.get(LOSMain.getRandom().nextInt(second.size())));
        if (third.size() > 0) FinalBoss(third.get(LOSMain.getRandom().nextInt(third.size())));
    }

    public void FinalBoss(Player p) {
        Bat b = (Bat) p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
        LivingEntity boss = (LivingEntity) b.getWorld().spawnEntity(b.getLocation(), EntityType.CHICKEN);
        ArrayList<Ability> abs = new ArrayList<Ability>();
        abs.add(new Ability(AbilityType.CULTOFTHEFEATHER, 20, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.SEWERSWARM, 20, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.SMITE, 10, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.WITHER, 10, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.ZOMBIESIEGE, 20, main, b, b.getWorld(), 30));
        new Chicken(b, boss, abs, main).runTaskTimer(main, 0L, 1L);
    }

    public void GeneralBoss(Player p) {
        Bat b = (Bat) p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
        LivingEntity boss = (LivingEntity) b.getWorld().spawnEntity(b.getLocation(), EntityType.SKELETON);
        ArrayList<Ability> abs = new ArrayList<Ability>();
        abs.add(new Ability(AbilityType.SEWERSWARM, 30, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.SMITE, 10, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.WITHER, 15, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.ZOMBIESIEGE, 30, main, b, b.getWorld(), 30));
        abs.add(new AbilityClone(20, boss));
        new Skeleton(b, boss, abs, main).runTaskTimer(main, 0L, 1L);
    }

    public void CommanderBoss(Player p) {
        Bat b = (Bat) p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
        LivingEntity boss = (LivingEntity) b.getWorld().spawnEntity(b.getLocation(), EntityType.MUSHROOM_COW);
        ArrayList<Ability> abs = new ArrayList<Ability>();
        abs.add(new Ability(AbilityType.CULTOFTHEFEATHER, 20, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.SEWERSWARM, 30, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.SMITE, 10, main, b, b.getWorld(), 30));
        abs.add(new Ability(AbilityType.WITHER, 15, main, b, b.getWorld(), 30));
        new MooshroomCow(b, boss, abs, main).runTaskTimer(main, 0L, 1L);
    }

    public void RegularBoss(Player p) {
        int i = LOSMain.getRandom().nextInt(3);
        switch(i) {
            case 0:
                Bat b = (Bat) p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
                LivingEntity boss = (LivingEntity) b.getWorld().spawnEntity(b.getLocation(), EntityType.PIG);
                ArrayList<Ability> abs = new ArrayList<Ability>();
                abs.add(new Ability(AbilityType.CULTOFTHEFEATHER, 20, main, b, b.getWorld(), 30));
                abs.add(new Ability(AbilityType.SMITE, 5, main, b, b.getWorld(), 30));
                abs.add(new Ability(AbilityType.WITHER, 10, main, b, b.getWorld(), 30));
                new Pig(b, boss, abs, main).runTaskTimer(main, 0L, 1L);
                break;
            case 1:
                Bat b1 = (Bat) p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
                LivingEntity boss1 = (LivingEntity) b1.getWorld().spawnEntity(b1.getLocation(), EntityType.PIG_ZOMBIE);
                ArrayList<Ability> abs1 = new ArrayList<Ability>();
                abs1.add(new Ability(AbilityType.SMITE, 10, main, b1, b1.getWorld(), 30));
                abs1.add(new Ability(AbilityType.WITHER, 10, main, b1, b1.getWorld(), 30));
                abs1.add(new AbilityClone(15, boss1));
                new PigZombie(b1, boss1, abs1, main).runTaskTimer(main, 0L, 1L);
                break;
            case 2:
                Bat b2 = (Bat) p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
                LivingEntity boss2 = (LivingEntity) b2.getWorld().spawnEntity(b2.getLocation(), EntityType.WITCH);
                ArrayList<Ability> abs2 = new ArrayList<Ability>();
                abs2.add(new Ability(AbilityType.SMITE, 5, main, b2, b2.getWorld(), 30));
                abs2.add(new Ability(AbilityType.WITHER, 10, main, b2, b2.getWorld(), 30));
                abs2.add(new AbilityClone(10, boss2));
                new Witch(b2, boss2, abs2, main).runTaskTimer(main, 0L, 1L);
                break;
        }
    }
}
