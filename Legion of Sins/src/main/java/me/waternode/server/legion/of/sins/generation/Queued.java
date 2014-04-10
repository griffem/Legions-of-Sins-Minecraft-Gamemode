package me.waternode.server.legion.of.sins.generation;

import org.bukkit.Location;

class Queued {
	private final Location loc;

	public Queued(Location l) {
		loc = l;
	}

	public Location getLocation() {
		return loc;
	}
}
