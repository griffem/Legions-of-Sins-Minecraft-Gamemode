package com.griffem.legionofsins.generation;

import com.griffem.legionofsins.InfinitePotionEffect;
import com.griffem.legionofsins.LOSMain;
import com.griffem.legionofsins.generation.deathworld.DeathWorldPopulator;
import com.griffem.legionofsins.generation.endgame.EndGamePopulator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class WorldListener implements Listener {

	@EventHandler
	public void onInit(WorldInitEvent e) {
        if(e.getWorld().getName().equalsIgnoreCase("main")) {
            e.getWorld().getPopulators().add(new MainPopulator());
        } else if(e.getWorld().getName().equalsIgnoreCase("deathworld")) {
            e.getWorld().getPopulators().add(new DeathWorldPopulator());
        } else if(e.getWorld().getName().equalsIgnoreCase("endgame")) {
            e.getWorld().getPopulators().add(new EndGamePopulator());
        }
	}

	@EventHandler
    public void onPortal(EntityPortalEnterEvent e) {
        if(e.getEntityType() == EntityType.PLAYER) {
            Player p = (Player) e.getEntity();
            if (e.getLocation().getWorld().getName().equalsIgnoreCase("spawn")) {
                if(p.getItemInHand() != null) {
                    if(p.getItemInHand().getType().equals(Material.NETHER_STAR)) {
                        Location l = new Location(Bukkit.getWorld("endgame"), LOSMain.getRandom().nextInt(1000), 260.0D, LOSMain.getRandom().nextInt(1000));
                        while(true) {
                            if(l.getWorld().getHighestBlockAt(l).getType() != Material.WEB
                                    && l.getWorld().getHighestBlockAt(l).getLocation().getY() > 55
                                    && l.getWorld().getHighestBlockAt(l).getType() != Material.LAVA
                                    && l.getWorld().getHighestBlockAt(l).getType() != Material.STATIONARY_LAVA) {
                                break;
                            } else {
                                l = new Location(Bukkit.getWorld("endgame"), LOSMain.getRandom().nextInt(1000), 260.0D, LOSMain.getRandom().nextInt(1000));
                            }
                        }
                        p.teleport(l);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 450, 5));
                        return;
                    }
                }

            if (e.getLocation().getWorld().getName().equalsIgnoreCase("spawn")) {
                    Location l = new Location(Bukkit.getWorld("main"), LOSMain.getRandom().nextInt(1000), 260.0D, LOSMain.getRandom().nextInt(1000));

                    while(true) {
                        if(l.getWorld().getHighestBlockAt(l).getType() != Material.COAL_BLOCK
                                && l.getWorld().getHighestBlockAt(l).getLocation().getY() > 55
                                && l.getWorld().getHighestBlockAt(l).getType() != Material.LAVA
                                && l.getWorld().getHighestBlockAt(l).getType() != Material.STATIONARY_LAVA) {
                            break;
                        } else {
                            l = new Location(Bukkit.getWorld("main"), LOSMain.getRandom().nextInt(1000), 260.0D, LOSMain.getRandom().nextInt(1000));
                        }
                    }
                    p.teleport(l);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 450, 5));
                    p.sendMessage(ChatColor.DARK_GREEN + "Don't worry, you will land safely.");
                    LOSMain.getInstance().getEvents().addException(p);
                }
            } else if (e.getLocation().getWorld().getName().equalsIgnoreCase("main")) {
                if(LOSMain.getRandom().nextBoolean()) {
                    if(LOSMain.getRandom().nextBoolean()) {
                        p.teleport(new Location(Bukkit.getWorld("deathworld"), -10+LOSMain.getRandom().nextInt(21), 260.0D, 500+LOSMain.getRandom().nextInt(1000)));
                    } else {
                        p.teleport(new Location(Bukkit.getWorld("deathworld"), -10+LOSMain.getRandom().nextInt(21), 260.0D, -1*(500+LOSMain.getRandom().nextInt(1000))));
                    }
                } else {
                    if(LOSMain.getRandom().nextBoolean()) {
                        p.teleport(new Location(Bukkit.getWorld("deathworld"), -1*(500+LOSMain.getRandom().nextInt(1000)), 260.0D, -10+LOSMain.getRandom().nextInt(21)));
                    } else {
                        p.teleport(new Location(Bukkit.getWorld("deathworld"), 500+LOSMain.getRandom().nextInt(1000), 260.0D, -10+LOSMain.getRandom().nextInt(21)));
                    }
                }

                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 450, 5));
                p.addPotionEffect(new InfinitePotionEffect(PotionEffectType.BLINDNESS, 0));
                p.setCompassTarget(new Location(p.getWorld(), 0, 0, 0));
                p.getInventory().addItem(new ItemStack(Material.COMPASS));
                p.sendMessage("There is a surprise for you at the end of the compass, in the center...");
            }
        }
    }
}
