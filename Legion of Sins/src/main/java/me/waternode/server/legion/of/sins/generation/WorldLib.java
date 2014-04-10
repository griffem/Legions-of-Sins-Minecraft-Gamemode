package me.waternode.server.legion.of.sins.generation;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

abstract class WorldLib {
	public static void CreateChests(Chunk c, Random r) {
		for (int i = 0; i < r.nextInt(4) + 1; i++) {
			Block b = c.getBlock(r.nextInt(16), r.nextInt(55), r.nextInt(16));
			b.setType(Material.CHEST);
			Chest ch = (Chest) b.getState();
			ch.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, r.nextInt(256) + 16));
			ch.update();
		}
	}

	public static void CreateAsh(Block center, Random r) {
		World world = center.getWorld();

		for (int y = center.getY(); y < center.getY() + 6; y++) {
			Block b = world.getBlockAt(center.getX(), y, center.getZ());
			if ((b.getBiome() == Biome.OCEAN) || (b.getBiome() == Biome.DEEP_OCEAN))
				if ((y == center.getY()) || (y == center.getY() + 5)) {
					if (r.nextInt(3) == 0)
						b.setType(Material.COAL_BLOCK);
				} else if ((y == center.getY() + 1) || (y == center.getY() + 4)) {
					if (r.nextBoolean())
                        b.setType(Material.COAL_BLOCK);
				} else
                    b.setType(Material.COAL_BLOCK);
		}
	}

	public static void createTower(Location centerBot, double radius, double height, Random r, ArrayList<Material> walls, ArrayList<Material> floors, ArrayList<Material> afterfloors, ArrayList<Integer> levels) {
		Location main = centerBot.clone();
		for (int y = centerBot.getBlockY(); y < centerBot.getBlockY() + height; y++) {
			main.setY(y);
			for (Location l : getCylHorizontal(main, radius))
				if (Math.pow(l.getX() - main.getX(), 2.0D) + Math.pow(l.getZ() - main.getZ(), 2.0D) >= Math.pow(radius, 2.0D) - radius) {
                    l.getBlock().setType(walls.get(r.nextInt(walls.size())));
				} else if (isFloor(l, centerBot, levels)) {
                    l.getBlock().setType(floors.get(r.nextInt(floors.size())));
				} else if (isAfterFloor(l, centerBot, levels))
                    l.getBlock().setType(afterfloors.get(r.nextInt(afterfloors.size())));
		}
	}

	private static boolean isFloor(Location a, Location b, ArrayList<Integer> s) {
		for (Integer value : s) {
			if (a.getBlockY() == b.getBlockY() + value) {
				return true;
			}
		}
		return false;
	}

	private static boolean isAfterFloor(Location a, Location b, ArrayList<Integer> s) {
		for (Integer value : s) {
			if (a.getBlockY() == b.getBlockY() + value + 1) {
				return true;
			}
		}
		return false;
	}

	public static void createCyl(Location centerBot, double radius, Random r) {
		Location main = centerBot.clone();

		for (int y = centerBot.getBlockY(); y < centerBot.getBlockY() + 150.0D; y++) {
			main.setY(y);
			for (Location l : getCylHorizontal(main, radius)) {
				Block b = l.getBlock();
				if (b.getType() != Material.AIR)
					if (Math.pow(l.getX() - main.getX(), 2.0D) + Math.pow(l.getZ() - main.getZ(), 2.0D) == Math.pow(radius, 2.0D)) {
						if (r.nextBoolean())
                            l.getBlock().setType(Material.LAVA);
					} else
                        l.getBlock().setType(Material.AIR);
			}
		}
	}

	private static ArrayList<Location> getCylHorizontal(Location center, double radius) {
		Location point1 = center.clone();
		Location point2 = center.clone();
		Location official = center.clone();

		point1.setX(center.getBlockX() + radius);
		point1.setZ(center.getBlockZ() + radius);

		point2.setX(center.getX() - radius);
		point2.setZ(center.getZ() - radius);

		ArrayList<Location> locs = new ArrayList<Location>();

		for (int x = point2.getBlockX(); x < point1.getBlockX(); x++) {
			for (int z = point2.getBlockZ(); z < point1.getBlockZ(); z++) {
				official.setX(x);
				official.setZ(z);
				if (Math.pow(official.getX() - center.getX(), 2.0D) + Math.pow(official.getZ() - center.getZ(), 2.0D) <= Math.pow(radius, 2.0D)) {
					locs.add(official.clone());
				}
			}
		}

		return locs;
	}
}
