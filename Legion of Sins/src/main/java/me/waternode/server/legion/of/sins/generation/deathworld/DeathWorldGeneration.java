package me.waternode.server.legion.of.sins.generation.deathworld;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Random;

/**
 * Created by Ervin
 * on 4/10/2014
 */
public class DeathWorldGeneration {

    public static void WaterReplace(Block b, Random r) {
        if(b.getType() == Material.STATIONARY_WATER) {
            b.setType(Material.AIR);
        } else if(b.getType() == Material.WATER) {
            b.setType(Material.AIR);
        }
    }
    public static void DirtReplace(Block b, Random r) {
        // netherrack, soul sand, web, coal ore, obsidian, nether brick, cobble, gravel, or smooth brick
        switch (b.getBiome()) {
            case BIRCH_FOREST:
            case BIRCH_FOREST_HILLS:
            case BIRCH_FOREST_HILLS_MOUNTAINS:
            case BIRCH_FOREST_MOUNTAINS:
                int i = r.nextInt(3);
                
                if (i == 0) {
                    b.setType(Material.NETHERRACK);
                } else if (i == 1) {
                    b.setType(Material.SOUL_SAND);
                } else if (i == 2) {
                    b.setType(Material.OBSIDIAN);
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
                b.setType(Material.NETHERRACK);
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
                    b.setType(Material.NETHERRACK);
                } else if (i == 1) {
                    b.setType(Material.SOUL_SAND);
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
                    b.setType(Material.COAL_ORE);
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
                b.setType(Material.COAL_ORE);
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
}
