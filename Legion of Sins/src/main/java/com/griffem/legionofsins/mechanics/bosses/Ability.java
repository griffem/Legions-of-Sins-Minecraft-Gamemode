package com.griffem.legionofsins.mechanics.bosses;

import com.griffem.legionofsins.LOSMain;
import com.griffem.legionofsins.mechanics.bosses.abilities.AbilityType;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
public class Ability extends BukkitRunnable {

    private final AbilityType ability;
    private final LOSMain main;
    private final LivingEntity boss;
    private final int cooldownAmt;
    private int cooldown = 0;
    private final World world;
    private final int range;
    private boolean active = true;

    public Ability(int cd, LivingEntity b) {
        this(null, cd, null, b, null, 0);
    }

    public Ability(AbilityType a, int cd, LOSMain p, LivingEntity b, World w, int r) {
        this.ability = a;
        this.cooldownAmt = cd;
        this.main = p;
        this.boss = b;
        this.world = w;
        this.range = r;
    }

    @Override
    public void run() {
        if (cooldown > 0) {
            cooldown--;
        } else {
            active = true;
        }
    }

    public boolean cast() {
        if (!active) {
            return false;
        }

        var nearby = world.getPlayers().stream()
            .filter(p -> boss.getLocation().distanceSquared(p.getLocation()) <= range * range)
            .toList();

        if (!nearby.isEmpty()) {
            ability.getValue(nearby, main).runTaskTimer(main, 0L, 20L);
            active = false;
            cooldown += cooldownAmt;
            return true;
        }

        return false;
    }

    public boolean isActive() {
        return active;
    }
}
