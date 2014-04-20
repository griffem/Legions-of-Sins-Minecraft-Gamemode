package me.waternode.server.legion.of.sins.generation.endgame;

import me.waternode.server.legion.of.sins.generation.WorldGeneration;
import me.waternode.server.legion.of.sins.generation.WorldLib;
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
public class EndGamePopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random r, Chunk c) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 128; y++) {
                    Block b = c.getBlock(x, y, z);
                    Material type = b.getType();

                    switch (type) {
                        case DIRT:
                            EndGameWorldGen.DirtReplace(b, r);
                            break;
                        case GRASS:
                            EndGameWorldGen.GrassReplace(b, r);
                            break;
                        case SAND: case STAINED_CLAY: case HARD_CLAY: case CLAY:
                            EndGameWorldGen.SandLikeReplace(b, r);
                            break;
                        case LOG: case LOG_2:
                            EndGameWorldGen.LogsReplace(b);
                            break;
                        case LEAVES: case LEAVES_2:
                            EndGameWorldGen.LeavesReplace(b, r);
                            break;
                    }
                }
            }
        }
        WorldLib.CreateChests(c, r);
    }
}
