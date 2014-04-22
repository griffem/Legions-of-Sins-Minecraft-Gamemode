package me.waternode.server.legion.of.sins.mechanics.spawn.tutorial;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Ervin
 * on 4/21/2014
 */
public class TutorialCommands implements CommandExecutor {

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
                }
            }
        }
        return false;
    }
}
