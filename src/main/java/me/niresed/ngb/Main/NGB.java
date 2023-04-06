package me.niresed.ngb.Main;

import me.niresed.ngb.Events.NGBEvent;
import me.niresed.ngb.Listener.GenerateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class NGB extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new GenerateEvent(), this);
        Player player = null;
        for (Player player_1 : Bukkit.getOnlinePlayers()){
            player = player_1;
            break;
        }
        if (player != null){
            BukkitScheduler scheduler = getServer().getScheduler();
            NGBEvent event = new NGBEvent(this, player);
            scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    Bukkit.getServer().getPluginManager().callEvent(event);
                }
            }, 0L, 20L);
        }
    }

    @Override
    public void onDisable() {
    }
}
