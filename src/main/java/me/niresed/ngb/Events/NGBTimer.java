package me.niresed.ngb.Events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class NGBTimer extends JavaPlugin {
    public NGBTimer(JavaPlugin plugin) {
        BukkitScheduler scheduler = getServer().getScheduler();
        NGBEvent event = new NGBEvent(plugin);
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().getPluginManager().callEvent(event);
            }
        }, 0L, 20L);
    }

}
