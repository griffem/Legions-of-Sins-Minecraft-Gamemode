package me.waternode.server.legion.of.sins.mechanics.spawn.tutorial;

import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;

/**
 * Created by Ervin
 * on 4/21/2014
 */
public class Tutorial {

    public static LOSMain main;
    public static ArrayList<String> tut = new ArrayList<String>();
    public static ArrayList<String> tut1 = new ArrayList<String>();
    public static ArrayList<String> tut2 = new ArrayList<String>();
    public static ArrayList<String> tut3 = new ArrayList<String>();
    public static ArrayList<String> tut4 = new ArrayList<String>();
    public static ArrayList<String> tut5 = new ArrayList<String>();
    public static ArrayList<String> tut6 = new ArrayList<String>();
    public static ArrayList<String> tut7 = new ArrayList<String>();
    public static ArrayList<String> tut8 = new ArrayList<String>();
    public static ArrayList<String> tut9 = new ArrayList<String>();
    public static ArrayList<String> tut10 = new ArrayList<String>();
    public static ArrayList<String> tut11 = new ArrayList<String>();
    public static ArrayList<String> tut12 = new ArrayList<String>();


    // Tutorial Spawn Point
    public static Location loc = new Location(Bukkit.getWorld("spawn"), main.getConfig().getInt(".getX"), main.getConfig().getInt(".getZ"), main.getConfig().getInt(".getY"));
}
