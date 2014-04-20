package me.waternode.server.legion.of.sins.mechanics.deathworld;

import net.ess3.api.IEssentials;
import com.earth2me.essentials.spawn.SpawnStorage;
import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class DeathWorldMobHandler implements Listener {

    final ArrayList<Player> queue = new ArrayList<Player>();
    final IEssentials ess = (IEssentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

    @EventHandler
    public void CSE1(CreatureSpawnEvent e) {
        if (e.getSpawnReason() == SpawnReason.CUSTOM || !e.getLocation().getWorld().getName().equalsIgnoreCase("deathworld"))	return;

        e.setCancelled(true);
    }

    @EventHandler
    public void BossKill(EntityDeathEvent e) {
        if(e.getEntityType().equals(EntityType.MAGMA_CUBE)) {
            if(e.getEntity().getMaxHealth() == 500) {
                if(e.getEntity().getKiller() != null) {
                    queue.add(e.getEntity().getKiller());
                }
            }
        }
    }

    @EventHandler
    public void OnChestClick(PlayerInteractEvent e) {
        if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.CHEST) {
                if(e.getClickedBlock().getLocation().getX() == 0 && e.getClickedBlock().getLocation().getZ() == 0 && e.getClickedBlock().getLocation().getY() == 200) {
                    if(!queue.contains(e.getPlayer())) {
                        e.getPlayer().sendMessage(ChatColor.RED + "Kill the boss you cutter!");
                    } else {
                        e.getPlayer().getInventory().addItem(new ItemStack(Material.NETHER_STAR));
                        e.getPlayer().getInventory().addItem(new ItemStack(Material.GOLD_INGOT, LOSMain.getRandom().nextInt(161)+32));
                        e.getPlayer().sendMessage(ChatColor.DARK_BLUE + "Hold the nether star with your hand when entering spawn to...go back to the real realm.");
                        queue.remove(e.getPlayer());
                        e.getPlayer().teleport(new Location(Bukkit.getWorld("spawn"), 453, 140, -353));
                    }
                    e.setCancelled(true);
                }
            }
        }
    }
}
