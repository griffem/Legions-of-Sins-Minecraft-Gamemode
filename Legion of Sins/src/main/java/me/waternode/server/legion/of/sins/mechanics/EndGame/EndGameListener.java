package me.waternode.server.legion.of.sins.mechanics.EndGame;

import me.waternode.server.legion.of.sins.generation.endgame.EndGameWorldGen;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EndGameListener implements Listener {

    @EventHandler
    public void OnChestClick(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getPlayer().getItemInHand() != null && e.getPlayer().getWorld().getName().equalsIgnoreCase("endgame")) {
                if(e.getPlayer().getItemInHand().getType().equals(Material.LONG_GRASS)) {
                    e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1f, 0f);
                    e.getPlayer().sendMessage(ChatColor.GREEN + "The ecobomb has been deployed.");
                    ItemStack longgrass = new ItemStack(Material.LONG_GRASS, getAmountInInventory(e.getPlayer(), Material.LONG_GRASS)-1, (byte) 1);
                    e.getPlayer().getInventory().remove(Material.LONG_GRASS);
                    e.getPlayer().getInventory().addItem(longgrass);
                    ChunkAnti(e.getPlayer().getLocation().getChunk());
                }
            }
        }
    }

    public int getAmountInInventory(Player player, Material type)
    {
        int total = 0;
        for(ItemStack is : player.getInventory().getContents())
        {
            if(!(is == null)) {
                if(is.getType() == type)
                {
                    total += is.getAmount();
                }
            }

        }
        return total;
    }

    public void ChunkAnti(Chunk c) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 128; y++) {
                    Block b = c.getBlock(x, y, z);
                    Material type = b.getType();

                    switch (type) {
                        case SMOOTH_BRICK: case STONE:
                            b.setType(Material.DIRT);
                            break;
                        case MYCEL: case SOUL_SAND: case OBSIDIAN: case NETHER_BRICK: case QUARTZ_ORE:
                            b.setType(Material.GRASS);
                            break;
                        case GRAVEL: case COBBLESTONE:
                            b.setType(Material.SAND);
                            break;
                        case QUARTZ_BLOCK: case COAL_BLOCK:
                            b.setType(Material.LOG);
                            break;
                        case WEB:
                            b.setType(Material.LEAVES);
                            break;
                    }
                }
            }
        }
    }
}
