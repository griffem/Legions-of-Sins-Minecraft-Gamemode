package me.waternode.server.legion.of.sins.generation;

import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class MainWorldListener
		implements Listener {
	public WorldGeneration gen;
	LOSMain main;

	public MainWorldListener(LOSMain plugin) {
		this.main = plugin;
		this.gen = new WorldGeneration(plugin);
	}

	@EventHandler
	public void onChunkPopulate(ChunkPopulateEvent e) {
		if (!e.getWorld().getName().equalsIgnoreCase("main")) {
			return;
		}
		Random r = new Random();
		Chunk c = e.getChunk();
		World world = e.getWorld();
		for (int x = 0; x < 16 + 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 128; y++) {
					Block b = c.getBlock(x, y, z);
					Material type = b.getType();

					if (type == Material.DIRT) {
						this.gen.DirtReplace(b, r);
					} else if (type == Material.GRASS) {
						this.gen.GrassReplace(b, r);
					} else if ((type == Material.SAND) || (type == Material.STAINED_CLAY) || (type == Material.HARD_CLAY) || (type == Material.CLAY)) {
						this.gen.SandLikeReplace(b, r);
					} else if ((type == Material.LOG) || (type == Material.LOG_2)) {
						this.gen.LogsReplace(b, r);
					} else if ((type == Material.LEAVES) || (type == Material.LEAVES_2)) {
						this.gen.LeavesReplace(b, r);
					} else if ((type == Material.LONG_GRASS) ||
							(type == Material.RED_ROSE) ||
							(type == Material.YELLOW_FLOWER) ||
							(type == Material.DOUBLE_PLANT) ||
							(type == Material.COCOA) ||
							(type == Material.SUGAR_CANE_BLOCK) ||
							(type == Material.CACTUS)) {
						b.setType(Material.AIR);
					}
				}

				Block b = world.getBlockAt(x, 100, z);

				if ((b.getBiome() == Biome.OCEAN) || (b.getBiome() == Biome.DEEP_OCEAN)) {
					WorldLib.CreateAsh(b, r, this);
				}
			}
		}

		this.gen.CylinderGeneration(r, c);

		this.gen.RuinsGeneration(r, c);

		WorldLib.CreateChests(c, r);
	}

	@EventHandler
	public void onPortal(PlayerPortalEvent e) {
		Random r = new Random();
		if (e.getFrom().getWorld().getName().equalsIgnoreCase("spawn")) {
			e.getPlayer().teleport(new Location(this.main.getServer().getWorld("main"), r.nextInt(1000), 260.0D, r.nextInt(1000)));
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 150, 5));
		} else if (e.getFrom().getWorld().getName().equalsIgnoreCase("main")) {
			e.getPlayer().teleport(new Location(this.main.getServer().getWorld("main2"), r.nextInt(1000), 260.0D, r.nextInt(1000)));
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 150, 5));
		}

		e.setCancelled(true);
	}
}
