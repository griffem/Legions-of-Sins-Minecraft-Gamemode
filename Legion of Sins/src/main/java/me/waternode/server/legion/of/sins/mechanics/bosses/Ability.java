package me.waternode.server.legion.of.sins.mechanics.bosses;

import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.mechanics.bosses.abilities.AbilityType;
import org.bukkit.World;
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
public class Ability extends BukkitRunnable {
    AbilityType ability;
    LOSMain main;
    LivingEntity boss;
    int cooldownAmt;
    int cooldown = 0;
    World world;
    int range;
    boolean active = true;

    public Ability(int cd, LivingEntity b) {
        cooldownAmt = cd;
        boss = b;
    }

    public Ability(AbilityType a, int cd, LOSMain p, LivingEntity b, World w, int r) {
        ability = a;
        cooldownAmt = cd;
        main = p;
        boss = b;
        world = w;
        range = r;
    }

    @Override
    public void run() {
        if(cooldown > 0) {
            cooldown--;
        } else {
            active = true;
        }
    }

    public boolean Cast() {
        if(active) {
            ArrayList<Player> ps = new ArrayList<Player>();
            for (Player p : main.getServer().getOnlinePlayers())
                if (p.getWorld().getName().equalsIgnoreCase(world.getName())) ps.add(p);
            ps = ability.getPlayers(boss.getLocation(), range, ps);
            if(ps.size() > 0) {
                ability.getValue(ps).runTaskTimer(this.main, 0L, 20L);
                active = false;
                cooldown += cooldownAmt;
                return true;
            }
        }
        return false;
    }

    public boolean GetState() {
        return active;
    }
}
