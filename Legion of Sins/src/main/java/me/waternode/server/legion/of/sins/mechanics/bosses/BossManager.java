package me.waternode.server.legion.of.sins.mechanics.bosses;

import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.mechanics.bosses.abilities.AbilityType;
import me.waternode.server.legion.of.sins.mechanics.events.*;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
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
        List<Player> ps = new ArrayList<Player>();
        for (Player p : main.getServer().getOnlinePlayers())
            if (p.getWorld().getName().toLowerCase().contains("deathworld")) ps.add(p);

        if (ps.size() <= 0) return;

        Player p = ps.get(LOSMain.getRandom().nextInt(ps.size()));

        if((p.getLocation().getX() < 10 || p.getLocation().getX() > -10)
                && (p.getLocation().getZ() < 10 || p.getLocation().getZ() > -10)) {
            Bat b = (Bat) p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
            ArrayList<Ability> abs = new ArrayList<Ability>();
            abs.add(new Ability(AbilityType.CULTOFTHEFEATHER, 30, main, b, b.getWorld(), 30));
            abs.add(new Ability(AbilityType.SEWERSWARM, 30, main, b, b.getWorld(), 30));
            abs.add(new Ability(AbilityType.SMITE, 30, main, b, b.getWorld(), 30));
            abs.add(new Ability(AbilityType.WITHER, 30, main, b, b.getWorld(), 30));
            abs.add(new Ability(AbilityType.ZOMBIESIEGE, 30, main, b, b.getWorld(), 30));
            abs.add(new AbilityClone(30, b));
        } else if((p.getLocation().getX() < 200 || p.getLocation().getX() > -200)
                && (p.getLocation().getZ() < 200 || p.getLocation().getZ() > -200)) {

        } else if((p.getLocation().getX() < 500 || p.getLocation().getX() > -500)
                && (p.getLocation().getZ() < 500 || p.getLocation().getZ() > -500)) {

        } else if((p.getLocation().getX() < 750 || p.getLocation().getX() > -750)
                && (p.getLocation().getZ() < 750 || p.getLocation().getZ() > -750)) {

        }
    }
}
