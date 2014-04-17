package me.waternode.server.legion.of.sins.generation;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import java.util.ArrayList;
import java.util.Random;

public class WorldGeneration {

	public static void DirtReplace(Block b, Random r) {
        switch (b.getBiome()) {
            case BIRCH_FOREST:
            case BIRCH_FOREST_HILLS:
            case BIRCH_FOREST_HILLS_MOUNTAINS:
            case BIRCH_FOREST_MOUNTAINS:
                int i = r.nextInt(3);

                if (i == 0) {
                    b.setType(Material.STONE);
                } else if (i == 1) {
                    b.setType(Material.COBBLESTONE);
                } else if (i == 2) {
                    b.setType(Material.SMOOTH_STAIRS);
                }
                break;

            case MEGA_SPRUCE_TAIGA:
            case MEGA_SPRUCE_TAIGA_HILLS:
            case MEGA_TAIGA:
            case MEGA_TAIGA_HILLS:
                if (r.nextBoolean()) {
                    b.setType(Material.MYCEL);
                } else if (r.nextInt(3) == 0) {
                    b.setType(Material.GRAVEL);
                } else {
                    b.setType(Material.COBBLESTONE);
                }
                break;

            default:
                b.setType(Material.STONE);
                break;
        }
	}

	public static void GrassReplace(Block b, Random r) {
        switch(b.getBiome()) {
            case SAVANNA:
            case SAVANNA_MOUNTAINS:
            case SAVANNA_PLATEAU:
            case SAVANNA_PLATEAU_MOUNTAINS:
            case JUNGLE:
            case JUNGLE_EDGE:
            case JUNGLE_EDGE_MOUNTAINS:
            case JUNGLE_HILLS:
            case TAIGA:
            case TAIGA_HILLS:
            case TAIGA_MOUNTAINS:
            case MEGA_TAIGA_HILLS:
            case MEGA_TAIGA:
            case MEGA_SPRUCE_TAIGA_HILLS:
            case MEGA_SPRUCE_TAIGA:
            case MESA:
            case MESA_BRYCE:
            case MESA_PLATEAU:
            case MESA_PLATEAU_FOREST:
            case MESA_PLATEAU_FOREST_MOUNTAINS:
            case MESA_PLATEAU_MOUNTAINS:
                b.setType(Material.MYCEL);
                break;

            case BIRCH_FOREST:
            case BIRCH_FOREST_HILLS:
            case BIRCH_FOREST_HILLS_MOUNTAINS:
            case BIRCH_FOREST_MOUNTAINS:
			    int i = r.nextInt(3);

		    	if (i == 0) {
                    b.setType(Material.STONE);
		    	} else if (i == 1) {
                    b.setType(Material.COBBLESTONE);
		    	} else if (i == 2) {
                    b.setType(Material.SMOOTH_BRICK);
		    	}
                break;

            case FOREST:
            case FOREST_HILLS:
            case FLOWER_FOREST:
            case ROOFED_FOREST:
            case ROOFED_FOREST_MOUNTAINS:
			    int k = r.nextInt(6);

		       	if (k == 0) {
                   b.setType(Material.MYCEL);
		    	} else if (k <= 1) {
                    b.setType(Material.SOUL_SAND);
		    	} else if (k <= 2) {
                    b.setType(Material.OBSIDIAN);
		    	} else if (k <= 5) {
                     b.setType(Material.NETHER_BRICK);
			    }
                break;

            case PLAINS:
            case SUNFLOWER_PLAINS:
            case ICE_PLAINS:
            case ICE_PLAINS_SPIKES:
                int j = r.nextInt(9);

                if (j == 0) {
                    b.setType(Material.QUARTZ_ORE);
                } else if (j <= 1) {
                    b.setType(Material.SOUL_SAND);
                } else if (j <= 3) {
                    b.setType(Material.OBSIDIAN);
                } else if (j <= 8) {
                    b.setType(Material.NETHER_BRICK);
                }
                break;

            default:
                b.setType(Material.OBSIDIAN);
        }
	}

	public static void SandLikeReplace(Block b, Random r) {
		if (r.nextInt(3) == 0)
            b.setType(Material.GRAVEL);
		else
            b.setType(Material.COBBLESTONE);
	}

	public static void LogsReplace(Block b) {
        switch (b.getBiome()) {
            case BIRCH_FOREST:
            case BIRCH_FOREST_HILLS:
            case BIRCH_FOREST_HILLS_MOUNTAINS:
            case BIRCH_FOREST_MOUNTAINS:
                b.setType(Material.QUARTZ_BLOCK);
                break;

            case SAVANNA:
            case SAVANNA_MOUNTAINS:
            case SAVANNA_PLATEAU:
            case SAVANNA_PLATEAU_MOUNTAINS:
                break;

            default:
                b.setType(Material.COAL_BLOCK);
                break;
        }
	}

	public static void LeavesReplace(Block b, Random r) {
        switch (b.getBiome()) {
            case BIRCH_FOREST:
            case BIRCH_FOREST_HILLS:
            case BIRCH_FOREST_HILLS_MOUNTAINS:
            case BIRCH_FOREST_MOUNTAINS:
                b.setType(Material.COAL_BLOCK);
                break;

            case SAVANNA:
            case SAVANNA_MOUNTAINS:
            case SAVANNA_PLATEAU:
            case SAVANNA_PLATEAU_MOUNTAINS:
                break;

            default:
                int i = r.nextInt(12);
                if (i == 0) {
                    b.setType(Material.EMERALD_ORE);
                } else if (i <= 1) {
                    b.setType(Material.AIR);
                } else if (i <= 4) {
                    b.setType(Material.WEB);
                } else if (i <= 11) {
                    b.setType(Material.COAL_BLOCK);
                }
                break;
        }
	}

	public static void CylinderGeneration(Random r, Chunk c) {
		int a = r.nextInt(10);
		if (a == 9) {
			Block b = c.getBlock(r.nextInt(16), r.nextInt(55)+10, r.nextInt(16));
			if ((b.getBiome() != Biome.RIVER) && (b.getBiome() != Biome.OCEAN) && (b.getBiome() != Biome.DEEP_OCEAN))
				WorldLib.createCyl(b.getLocation(), r.nextInt(10) + 1, r);
		} else if (a <= 4) {
			Block b = c.getBlock(r.nextInt(16), r.nextInt(55), r.nextInt(16));
			if ((b.getBiome() != Biome.RIVER) && (b.getBiome() != Biome.OCEAN) && (b.getBiome() != Biome.DEEP_OCEAN) && (
					(b.getBiome() == Biome.PLAINS) ||
							(b.getBiome() == Biome.SUNFLOWER_PLAINS)))
				WorldLib.createCyl(b.getLocation(), r.nextInt(10) + 1, r);
		}
	}

	public static void RuinsGeneration(Random r, Chunk c) {
		int a = r.nextInt(800);
		if (a < 2) {
			Block b = c.getBlock(r.nextInt(16), 1, r.nextInt(16));
			b = c.getWorld().getHighestBlockAt(b.getLocation());
			Location l = b.getLocation();
			l.add(0.0D, -1 * r.nextInt(3), 0.0D).getBlock();
			b = l.getBlock();
			a = r.nextInt(30);
			ArrayList<Material> walls = new ArrayList<Material>();
			ArrayList<Material> floors = new ArrayList<Material>();
			ArrayList<Material> afterfloors = new ArrayList<Material>();

			if (a < 10) {
				for (int i = 0; i < 3; i++) {
					walls.add(Material.COBBLESTONE);
				}
				for (int i = 0; i < 3; i++) {
					walls.add(Material.STONE);
				}
				for (int i = 0; i < 3; i++) {
					walls.add(Material.SMOOTH_BRICK);
				}
				walls.add(Material.AIR);
				walls.add(Material.COAL_BLOCK);
				walls.add(Material.QUARTZ_BLOCK);

				for (int i = 0; i < 7; i++) {
					floors.add(Material.NETHER_BRICK);
				}
				floors.add(Material.AIR);


				for (int i = 0; i < 144; i++) {
					afterfloors.add(Material.AIR);
				}
                for (int i = 0; i < 15; i++) {
                    afterfloors.add(Material.WEB);
                }
                afterfloors.add(Material.CHEST);
			} else if (a < 20) {
				for (int i = 0; i < 5; i++) {
					walls.add(Material.QUARTZ_BLOCK);
				}
				walls.add(Material.GLOWSTONE);
				walls.add(Material.COBBLESTONE);

				floors.add(Material.STAINED_GLASS);

				for (int i = 0; i < 144; i++) {
					afterfloors.add(Material.AIR);
				}
                for (int i = 0; i < 15; i++) {
                    afterfloors.add(Material.WEB);
                }
                afterfloors.add(Material.CHEST);
			} else {
				for (int i = 0; i < 4; i++) {
					walls.add(Material.LEAVES);
				}
				for (int i = 0; i < 2; i++) {
					walls.add(Material.LOG);
				}
				walls.add(Material.WEB);

				for (int i = 0; i < 4; i++) {
					floors.add(Material.LEAVES);
				}
				for (int i = 0; i < 2; i++) {
					floors.add(Material.LOG);
				}
				floors.add(Material.WEB);

				for (int i = 0; i < 64; i++) {
					afterfloors.add(Material.LEAVES);
				}
				for (int i = 0; i < 32; i++) {
					afterfloors.add(Material.LOG);
				}
                for (int i = 0; i < 15; i++) {
                    afterfloors.add(Material.WEB);
                }
                afterfloors.add(Material.CHEST);
			}

			int h = r.nextInt(4) * 10 + 10;
			ArrayList<Integer> levels = new ArrayList<Integer>();
			levels.add(0);
			for (int i = 1; i <= h / 5; i++) {
				levels.add(i * 5);
			}
			h += 1 + r.nextInt(3);
			WorldLib.createTower(b.getLocation(), r.nextInt(11) + 5, h, r, walls, floors, afterfloors, levels);
		} else if(a == 2) {

        }
	}
}
