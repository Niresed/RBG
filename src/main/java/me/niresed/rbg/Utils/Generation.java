package me.niresed.rbg.Utils;

import me.niresed.rbg.Main.RBG;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;
import java.util.Random;

public class Generation {
    private static final Plugin plugin = RBG.getPlugin(RBG.class);

    public static boolean runTimer = true;

    private static final int countOfZones = plugin.getConfig().getStringList("zones").size();

    public static void start(FileConfiguration configuration) {
        World world = Bukkit.getWorld(Objects.requireNonNull(configuration.getString("world")));

        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            if (runTimer) {
                generateBlock(world);
            } else {
                scheduler.cancelTasks(plugin);
            }
        }, 20L, plugin.getConfig().getLong("cooldown") / countOfZones);
    }


    private static void generateBlock(World world) {
        for (String zone : plugin.getConfig().getStringList("zones")) {
            if (!runTimer) {
                break;
            }
            String type = Objects.requireNonNull(plugin.getConfig().getConfigurationSection(zone)).getString("type");

            if (type.equals("cane")) {
                Location location = CaneUtils.generateLocation(zone, world);
                if (location != null) {
                    Block block = location.getBlock();
                    block.setType(Material.SUGAR_CANE);
                }

            } else if (type.equals("clay")) {
                Location location = ClayUtils.generateLocation(zone, world);
                if (location != null) {
                    Block block = location.getBlock();
                    block.setType(Material.CLAY);
                }

            }

        }
    }

    public static Location generateRandomLocation(World world, ConfigurationSection config) {
        int minX = config.getInt("x1"), minZ = config.getInt("z1");
        int maxX = config.getInt("x2"), maxZ = config.getInt("z2");

        Random random = new Random();
        int x = random.nextInt(minX, maxX + 1);
        int y = 0;
        int z = random.nextInt(minZ, maxZ + 1);

        Location location = new Location(world, x, y, z);
        y = location.getWorld().getHighestBlockYAt(location) + 1;
        location.setY(y);

        return location;
    }
}
