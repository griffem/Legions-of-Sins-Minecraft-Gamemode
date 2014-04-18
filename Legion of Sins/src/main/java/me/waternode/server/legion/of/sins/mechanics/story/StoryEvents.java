package me.waternode.server.legion.of.sins.mechanics.story;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Ervin
 * on 4/18/2014
 */
public class StoryEvents implements Listener {

    @EventHandler
    public void onPJE(PlayerJoinEvent e) {
        if(!e.getPlayer().hasPlayedBefore()) {
            e.getPlayer().sendMessage(ChatColor.GOLD + "[Tutorial]" + ChatColor.GOLD + "Would you like to play the Tutorial? Type [/tut yes] to accept.");
        }
    }
}
