package com.griffem.legionofsins.mechanics.EndGame;

import com.griffem.legionofsins.generation.endgame.EndGameWorldGen;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EndGameListener implements Listener {

    /**
     * Handles the player deploying an "eco-bomb" in the EndGame world.
     *
     * <p>Right-clicking with a block of grass consumes it and converts nearby
     * blocks to more natural variants.</p>
     */
    @EventHandler
    public void onChestClick(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        Player player = e.getPlayer();
        if (!player.getWorld().getName().equalsIgnoreCase("endgame")) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != Material.GRASS_BLOCK) return;

        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 0f);
        player.sendMessage(ChatColor.GREEN + "The ecobomb has been deployed.");
        player.getInventory().removeItem(new ItemStack(Material.GRASS_BLOCK, 1));
        purifyChunk(player.getLocation().getChunk());
        player.updateInventory();
    }

    /**
     * Applies ecological transformations to a given chunk.
     *
     * @param chunk chunk to transform
     */
    public void purifyChunk(Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                transformColumn(chunk, x, z);
            }
        }
    }

    /**
     * Transforms all blocks in a vertical column of a chunk.
     */
    private void transformColumn(Chunk chunk, int x, int z) {
        for (int y = 0; y < 128; y++) {
            Block block = chunk.getBlock(x, y, z);
            transformBlock(block);
        }
    }

    /**
     * Replaces harsh blocks with their more "eco" friendly counterparts.
     */
    private void transformBlock(Block block) {
        switch (block.getType()) {
            case STONE_BRICKS, STONE -> block.setType(Material.DIRT);
            case MYCELIUM, SOUL_SAND, OBSIDIAN, NETHER_BRICKS, NETHER_QUARTZ_ORE -> block.setType(Material.GRASS_BLOCK);
            case GRAVEL, COBBLESTONE -> block.setType(Material.SAND);
            case QUARTZ_BLOCK, COAL_BLOCK -> block.setType(Material.OAK_LOG);
            case WEB -> block.setType(Material.OAK_LEAVES);
            default -> {
            }
        }
    }
}
