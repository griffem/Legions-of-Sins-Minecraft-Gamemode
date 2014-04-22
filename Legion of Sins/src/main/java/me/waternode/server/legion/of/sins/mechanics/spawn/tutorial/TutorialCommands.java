package me.waternode.server.legion.of.sins.mechanics.spawn.tutorial;

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

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("tut")) {
            if(args[0].contains("yes")) {
                Tutorial.tut.add(p.getName());
                p.teleport(Tutorial.loc);
                p.sendMessage("§cWelcome to Legion of Sins! You are now in the spawn.");
            }
        }

        return false;
    }
}
