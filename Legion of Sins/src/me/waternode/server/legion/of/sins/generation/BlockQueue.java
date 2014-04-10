package me.waternode.server.legion.of.sins.generation;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockQueue extends BukkitRunnable {

	WorldGeneration gen;

	public BlockQueue(WorldGeneration gener) {
		gen = gener;
	}

	@Override
	public void run() {
		for (int i = gen.loop; i >= 0; i--) {
			if (gen.ToChange.size() > 0) {
				Block b = gen.ToChange.get(0).getLocation().getBlock();
				Material m = gen.ChangeTo.get(gen.ToChange.get(0));
				b.setType(m);
				gen.ChangeTo.remove(gen.ToChange.get(0));
				gen.ToChange.remove(0);
			}
		}
	}

}
