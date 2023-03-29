package me.niresed.ngb;

import me.niresed.ngb.Commands.NGBOff;
import me.niresed.ngb.Commands.NGBOn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NGB extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("NGBOn")).setExecutor(new NGBOn());
        Objects.requireNonNull(getCommand("NGBOff")).setExecutor(new NGBOff());
    }
    @EventHandler
    public static void ss(PlayerJoinEvent e){

    }
//
//
//    public static void generatedBlock(boolean onOrOff, Player player){
//        Thread.sleep();
//        Location location = generatedLocation(player);
//    }
//    public static Location generatedLocation(Player player) {
//
//    }
//
//    public static boolean isLocationNormal(Location location){
//
//    }

    @Override
    public void onDisable() {
    }
}
