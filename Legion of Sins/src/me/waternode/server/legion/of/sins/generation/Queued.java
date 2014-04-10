package me.waternode.server.legion.of.sins.generation;

import org.bukkit.Location;

public class Queued {
	Location loc;
	int in;
	
	public Queued(Location l, int i) {
		loc = l;
		i = in;
	}

	public Location getLocation() {
		return loc;
	}
}
