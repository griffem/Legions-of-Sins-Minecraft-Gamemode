package me.waternode.server.legion.of.sins.mechanics.bosses.abilities;

import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.mechanics.bosses.abilities.Abilities;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Ervin
 * on 4/12/2014
 */
public class Smite extends Abilities {

    public Smite(ArrayList<Player> ps) {super(ps, false);}

    @Override
    public void PlayerCast(Player p) {
        World w = p.getWorld();
        Location pLoc = p.getLocation();
        int pX = pLoc.getBlockX();
        int pZ = pLoc.getBlockZ();
        if (w.getHighestBlockYAt(pX, pZ) - 5 <= pLoc.getY()) {
                Location loc = p.getLocation();

                loc.setX(p.getLocation().getX() + (LOSMain.getRandom().nextInt(11) - 5));
                loc.setY(p.getLocation().getY());
                loc.setZ(p.getLocation().getZ() + (LOSMain.getRandom().nextInt(11) - 5));

                p.getWorld().strikeLightning(loc);
        }
    }

    @Override
    public boolean Cancelled() {return false;}
}
