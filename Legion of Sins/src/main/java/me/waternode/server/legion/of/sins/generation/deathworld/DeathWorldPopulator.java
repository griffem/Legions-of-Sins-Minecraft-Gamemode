package me.waternode.server.legion.of.sins.generation.deathworld;

import me.waternode.server.legion.of.sins.generation.WorldLib;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

/**
 * Created by Ervin
 * on 4/10/2014
 */
public class DeathWorldPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random r, Chunk c) {
        for (int x = 0; x < 16 + 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 128; y++) {
                    Block b = c.getBlock(x, y, z);
                    Material type = b.getType();

                    switch (type) {
                        case WATER: case STATIONARY_WATER:
                            DeathWorldGeneration.WaterReplace(b, r);
                            break;
                        case DIRT:
                            DeathWorldGeneration.DirtReplace(b, r);
                            break;
                        case GRASS:
                            DeathWorldGeneration.GrassReplace(b, r);
                            break;
                        case SAND: case STAINED_CLAY: case HARD_CLAY: case CLAY:
                            DeathWorldGeneration.SandLikeReplace(b, r);
                            break;
                        case LOG: case LOG_2:
                            DeathWorldGeneration.LogsReplace(b);
                            break;
                        case LEAVES: case LEAVES_2:
                            DeathWorldGeneration.LeavesReplace(b, r);
                            break;
                        case LONG_GRASS: case RED_ROSE:
                        case YELLOW_FLOWER: case DOUBLE_PLANT:
                        case COCOA: case SUGAR_CANE_BLOCK:
                        case CACTUS: case MELON_BLOCK: case VINE:
                        case WATER_LILY: case SUGAR_CANE:
                            b.setType(Material.AIR);
                            break;
                    }
                }
                Block b = c.getBlock(x, 200, z);
                Location l = b.getLocation();
                if((l.getX() > -10 && l.getX() < 10)
                        ||(l.getZ() > -10 && l.getZ() < 10)) {
                    b.setType(Material.SAND);
                }
                if((l.getX() == -10 || l.getX() == 10) && (l.getZ() == -10 || l.getZ() == 10)) {
                    for(int i = 0; i < 10; i++) {
                        l.add(0, 1, 0);
                        b.setType(Material.GRAVEL);
                    }
                }
                if(l.getX() == 0 && l.getZ() == 0) {
                    l.setY(10);
                    for(int i = 0; i < 199; i++) {
                        l.add(0, 1, 0);
                        b.setType(Material.GLOWSTONE);
                    }
                }
            }
        }
    }
}
