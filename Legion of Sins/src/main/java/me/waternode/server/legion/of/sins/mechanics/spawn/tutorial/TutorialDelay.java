package me.waternode.server.legion.of.sins.mechanics.spawn.tutorial;

import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ervin
 * on 4/21/2014
 */
public class TutorialDelay extends BukkitRunnable {

    private Player player;
    private String message;

    public void delayedMessage(Player p, String m) {
        player = p;
        message = m;

        this.runTaskLater(LOSMain.getInstance(), 40L*(1));
    }

    @Override
    public void run() {
        player.sendMessage("§cBefore you start playing, you need to learn the ins and outs of the server. Please walk around and acquaint yourself a bit, then type [/tut continue] to proceed.");
    }

}
