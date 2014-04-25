package me.waternode.server.legion.of.sins.mechanics.spawn.tutorial;

import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Ervin
 * on 4/21/2014
 */
public class TutorialCommands implements CommandExecutor {

    LOSMain main;
    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("tut")) {
            if(args[0].contains("yes")) {
                Tutorial.tut.add(p.getName());
                p.teleport(Tutorial.loc);
                p.sendMessage("§cWelcome to Legion of Sins! You are now in the spawn.");
                return true;
            }
            if(args[0].contains("continue")) {
                if(Tutorial.tut.contains(p.getName())) {
                    Tutorial.tut.remove(p.getName());
                    Tutorial.tut1.add(p.getName());
                    p.sendMessage("§c As you go about the Tutorial, you will notice that you are warping from floating island to floating island. Don’t worry. The ADMINISTRATIVE GODS are teaching you the rules in a fun and exciting way, instead of making you toil in the depths of the underworld, reading sign after sign, grueling over the lack of information you..");
                } else if(Tutorial.tut5.contains(p.getName())) {
                    Tutorial.tut5.remove(p.getName());
                    Tutorial.tut6.add(p.getName());
                    p.sendMessage("§cYou see that mob over there? That’s a zombie. Yes, I know. The zombie must be drunk off of root beer or some other beverage because there are bubble surrounding him, and he keeps falling off of the island! Give me one second.");
                }
            }
            if(args[0].contains("setspawn")) {
                if(p.isOp()) {
                    main.getConfig().set("TutorialSpawn" + ".getX", p.getLocation().getBlockX());
                    main.getConfig().set("TutorialSpawn" + ".getZ", p.getLocation().getBlockZ());
                    main.getConfig().set("TutorialSpawn" + ".getY", p.getLocation().getBlockY());
                    p.sendMessage("§cTutorial Spawn has been set.");
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
