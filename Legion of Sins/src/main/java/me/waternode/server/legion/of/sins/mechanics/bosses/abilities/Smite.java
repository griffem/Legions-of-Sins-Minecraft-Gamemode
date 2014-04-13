package me.waternode.server.legion.of.sins.mechanics.bosses.abilities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ervin
 * on 4/12/2014
 */
public class Smite extends Abilities {

    public Smite(ArrayList<Player> ps) {super(ps, false);}

    public void OnPlayerNear(Player p, Random random) {
        World w = p.getWorld();
        Location pLoc = p.getLocation();
        int pX = pLoc.getBlockX();
        int pZ = pLoc.getBlockZ();
        if (w.getHighestBlockYAt(pX, pZ) - 5 <= pLoc.getY()) {
                Location loc = p.getLocation();

                loc.setX(p.getLocation().getX() + (random.nextInt(11) - 5));
                loc.setY(p.getLocation().getY());
                loc.setZ(p.getLocation().getZ() + (random.nextInt(11) - 5));

                p.getWorld().strikeLightning(loc);
        }
    }

    @Override
    public boolean Cancelled() {return false;}
}
