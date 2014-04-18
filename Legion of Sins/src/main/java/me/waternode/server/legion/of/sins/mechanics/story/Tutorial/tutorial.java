package me.waternode.server.legion.of.sins.mechanics.story.Tutorial;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ervin
 * on 4/18/2014
 */
public class tutorial implements CommandExecutor {

    List<String> tutpart1 = new Tutorial<Player, TutState>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        String tprefix = ChatColor.GOLD + "[Tutorial]";

        if(cmd.getLabel().equalsIgnoreCase("tutorial") || (cmd.getName().equalsIgnoreCase("tut"))) {
            Player p = (Player) sender;
            if(p.hasPermission("los.tutorial")) {
                if(args[0].equalsIgnoreCase("yes")) {
                    p.sendMessage(tprefix + ChatColor.RED + "Welcome to Legion of Sins! You are now in the spawn!");
                    tutpart1.add(p.getName());

                }
            }
        }
        return false;
    }
}
