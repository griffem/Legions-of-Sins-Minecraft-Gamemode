package com.griffem.legionofsins.mechanics.spawn.tutorial;

import com.griffem.legionofsins.LOSMain;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
        if(Tutorial.tut1.contains(player.getName())) {
            new TutorialDelay().delayedMessage(player, "§cAs you go about the Tutorial, you will notice that you are warping from floating island to floating island. Don’t worry. The ADMINISTRATIVE GODS are teaching you the rules in a fun and exciting way, instead of making you toil in the depths of the underworld, reading sign after sign, grueling over the lack of information you…");
            Tutorial.tut1.remove(player.getName());
            Tutorial.tut2.add(player.getName());
        }
        if(Tutorial.tut2.contains(player.getName())) {
            new TutorialDelay().delayedMessage(player, "§cI am getting ahead of myself here. Let’s begin.");
            Tutorial.tut2.remove(player.getName());
            Tutorial.tut3.add(player.getName());
        }
        if(Tutorial.tut3.contains(player.getName())) {
            new TutorialDelay().delayedMessage(player, "§cYou see that destroyed house in the distance? That house has been destroyed; not by any mobs, but it has been destroyed by another PLAYER! This is a post-apocalyptic world! You can do WHATEVER you would like! Just don’t babble on to the Admins about someone destroying your home. You’re wasting other players’ TAX DOLLARS!");
            Tutorial.tut3.remove(player.getName());
            Tutorial.tut4.add(player.getName());
        }
        if(Tutorial.tut4.contains(player.getName())) {
            new TutorialDelay().delayedMessage(player, "§cI’m going to be completely honest here. There are no tax dollars at work. I just wanted to say that to string you along. NOW that you feel guilty, let’s continue. If you do want to stick around and inspect the house, go right on ahead and steal WHATEVER is in there. It’s probably going to disappear from your inventory upon completion of this tutorial. ");
            Tutorial.tut4.remove(player.getName());
            Tutorial.tut5.add(player.getName());
        }
        if(Tutorial.tut6.contains(player.getName())) {
            new TutorialDelay().delayedMessage(player, "§cNow that’s better. Here. Take this normal iron sword and hit it until it’s dead.");
            player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
            Tutorial.tut6.remove(player.getName());
            Tutorial.tut7.add(player.getName());
        }
    }
}
