package me.waternode.server.legion.of.sins.mechanics.bosses;

import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.mechanics.bosses.abilities.AbilityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Emery
 * Date: 4/12/14
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbilityClone extends BukkitRunnable {
    AbilityType ability;
    LivingEntity boss;
    int cooldownAmt;
    int cooldown = 0;
    boolean active = true;

    public AbilityClone(AbilityType a, int cd, LivingEntity b) {
        ability = a;
        cooldownAmt = cd;
        boss = b;
    }

    @Override
    public void run() {
        if(cooldown > 0) {
            cooldown--;
        } else {
            active = true;
        }
    }

    public void Cast() {
        if(active) {
            boss.getWorld().spawnEntity(boss.getLocation(), boss.getType());
        }
    }

    public boolean GetState() {
        return active;
    }
}
