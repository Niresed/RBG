package me.niresed.rbg.Main;

import me.niresed.rbg.Commands.BlockSwitch;
import me.niresed.rbg.Utils.Generation;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

public final class RBG extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("BlockSwitch")).setExecutor(new BlockSwitch());
    }
    public void register() {
        Generation.start(getConfig());
    }
}