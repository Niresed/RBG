package me.niresed.ngb.Main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NGB extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
    }
}
