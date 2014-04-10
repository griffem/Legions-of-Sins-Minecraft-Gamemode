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
                        case SAND
                    }

                    if (type == Material.DIRT) {
                        WorldGeneration.DirtReplace(b, r);
                    } else if (type == Material.GRASS) {
                        WorldGeneration.GrassReplace(b, r);
                    } else if ((type == Material.SAND) || (type == Material.STAINED_CLAY) || (type == Material.HARD_CLAY) || (type == Material.CLAY)) {
                        WorldGeneration.SandLikeReplace(b, r);
                    } else if ((type == Material.LOG) || (type == Material.LOG_2)) {
                        WorldGeneration.LogsReplace(b);
                    } else if ((type == Material.LEAVES) || (type == Material.LEAVES_2)) {
                        WorldGeneration.LeavesReplace(b, r);
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
                    WorldLib.CreateAsh(b, r);
                }
            }
        }

        this.gen.CylinderGeneration(r, c);

        this.gen.RuinsGeneration(r, c);

        WorldLib.CreateChests(c, r);
    }
}
