package me.waternode.server.legion.of.sins.mechanics.events;

import me.waternode.server.legion.of.sins.LOSMain;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ElectricalStorms extends CatastrophicEvent {
	
	public ElectricalStorms(Location c, double r, double s, int l, LOSMain m,	boolean D3, World w, Vector d) {
		super(c, r, s, l, m, D3, w, d);
	}
	
	@Override
	public void OnPlayerNear(Player p) {
		// 4 times a second / 2 = ~2 times per second
		World w = p.getWorld();
		Location pLoc = p.getLocation();
		int pX = (int) pLoc.getBlockX();
		int pZ = (int) pLoc.getBlockZ();
		if(w.getHighestBlockYAt(pX, pZ) - 5 <= pLoc.getY()){
			if(rand.nextInt(4) == 0) {
				Location loc = new Location(world, range, range, range);
				
				loc.setX(p.getLocation().getX() + (rand.nextInt(11) - 5));
				loc.setY(p.getLocation().getY());
				loc.setZ(p.getLocation().getZ() + (rand.nextInt(11) - 5));
				
				p.getWorld().strikeLightning(loc);
			}
		}
		
	}
}
