package me.waternode.server.legion.of.sins.mechanics.spawn.tutorial;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Ervin
 * on 4/21/2014
 */
public class TutorialHandler implements Listener {

    @EventHandler
    public void onPJE(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()) {
            p.sendMessage("§cWould you like to play the Tutorial? Type [/tut yes] to accept.");
        }
    }
}
