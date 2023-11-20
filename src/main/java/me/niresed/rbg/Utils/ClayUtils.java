package me.niresed.rbg.Utils;

import me.niresed.rbg.Main.RBG;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class ClayUtils {
    private static final Plugin plugin = RBG.getPlugin(RBG.class);

    public static Location generateLocation(String zone, World world) {
        ArrayList<Integer> coordinate = (ArrayList<Integer>) plugin.getConfig().getIntegerList(zone);
        ConfigurationSection config = plugin.getConfig().getConfigurationSection(zone);
        Location location = Generation.generateRandomLocation(world, config);
        for (int i = 0; i < 30; i++) {
            boolean isLocationSafeCheck = isLocationSafe(location);
            if (!(isLocationSafeCheck)) {
                location = Generation.generateRandomLocation(world, config);
            } else {
                location = getHighestBlockYATWater(location);
                if (location != null) {
                    return location;
                }
                break;
            }
        }
        return null;
    }

    private static boolean isLocationSafe(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);
        Block above2x = location.getWorld().getBlockAt(x, y + 2, z);

        return !(!(below.getType() == Material.WATER) || (block.getType().isSolid()) || (above.getType().isSolid()) || (above2x.getType().isSolid()));
    }

    private static Location getHighestBlockYATWater(Location location) {
        Block block;
        Block above;
        Block below;
        int x;
        int y;
        int z;
        for (int i = 0; i < 30; i++) {
            x = (int) location.getX();
            y = (int) location.getY();
            z = (int) location.getZ();

            below = location.getWorld().getBlockAt(x, y - 1, z);
            block = location.getWorld().getBlockAt(x, y, z);
            above = location.getWorld().getBlockAt(x, y + 1, z);
            location.setY(y - 1);
            if (below.isSolid() && block.getType() == Material.WATER && !(above.getType() == Material.AIR)) {
                return block.getLocation();
            }
        }
        return null;
    }
}
