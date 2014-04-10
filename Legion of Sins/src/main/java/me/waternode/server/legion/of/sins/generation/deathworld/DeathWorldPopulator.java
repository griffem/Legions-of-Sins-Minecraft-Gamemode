package me.waternode.server.legion.of.sins.generation.deathworld;

import me.waternode.server.legion.of.sins.generation.WorldLib;
import org.bukkit.Chunk;
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
    public void populator(World world, Random r, Chunk c) {
        for (int x = 0; x < 16 + 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 128; y++) {
                    Block b = c.getBlock(x, y, z);
                    Material type = b.getType();

                    switch (type) {
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
                        case LONG_GRASS: case RED_ROSE: case YELLOW_FLOWER: case DOUBLE_PLANT: case COCOA: case SUGAR_CANE_BLOCK: case CACTUS:
                            b.setType(Material.AIR);
                            break;
                    }
                }

                Block b = c.getBlock(x, 100, z);

                if ((b.getBiome() == Biome.OCEAN) || (b.getBiome() == Biome.DEEP_OCEAN)) {
                    WorldLib.CreateAsh(b, r);
                }
            }
        }
    }
}
