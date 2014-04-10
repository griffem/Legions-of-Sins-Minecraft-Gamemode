package me.waternode.server.legion.of.sins.generation;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Emery
 * Date: 4/10/14
 * Time: 8:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random r, Chunk c) {
        for (int x = 0; x < 16 + 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 128; y++) {
                    Block b = c.getBlock(x, y, z);
                    Material type = b.getType();

                    switch (type) {
                        case DIRT:
                            WorldGeneration.DirtReplace(b, r);
                            break;
                        case GRASS:
                            WorldGeneration.GrassReplace(b, r);
                            break;
                        case SAND: case STAINED_CLAY: case HARD_CLAY: case CLAY:
                            WorldGeneration.SandLikeReplace(b, r);
                            break;
                        case LOG: case LOG_2:
                            WorldGeneration.LogsReplace(b);
                            break;
                        case LEAVES: case LEAVES_2:
                            WorldGeneration.LogsReplace(b);
                            break;
                        case LONG_GRASS: case RED_ROSE: case YELLOW_FLOWER: case DOUBLE_PLANT: case COCOA: case SUGAR_CANE_BLOCK: case CACTUS:
                            b.setType(Material.AIR);
                            break;
                    }
                }

                Block b = world.getBlockAt(x, 100, z);

                if ((b.getBiome() == Biome.OCEAN) || (b.getBiome() == Biome.DEEP_OCEAN)) {
                    WorldLib.CreateAsh(b, r);
                }
            }
        }

        WorldGeneration.CylinderGeneration(r, c);

        WorldGeneration.RuinsGeneration(r, c);

        WorldLib.CreateChests(c, r);
    }
}
