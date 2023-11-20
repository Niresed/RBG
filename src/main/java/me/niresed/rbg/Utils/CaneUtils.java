package me.niresed.rbg.Utils;

import me.niresed.rbg.Main.RBG;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class CaneUtils {
    private static final Plugin plugin = RBG.getPlugin(RBG.class);
    private static final HashSet<Material> trueBlocks = new HashSet<>();
    private static final ArrayList<Location> savedCoordinates = new ArrayList<>();

    static {
        trueBlocks.add(Material.SAND);
        trueBlocks.add(Material.GRASS_BLOCK);
        trueBlocks.add(Material.DIRT);
    }

    public static Location generateLocation(String zone, World world) {
        ConfigurationSection config = plugin.getConfig().getConfigurationSection(zone);
        Location location = generateFromArray();
        if (location != null) {
            return location;
        }
        location = Generation.generateRandomLocation(world, config);
        for (int i = 0; i < 300; i++) {
            boolean isLocationSafeCheck = isLocationSafe(location);
            boolean isLocationHasWaterInNearby = placeSugarCane(location);

            if (!(isLocationSafeCheck) || !(isLocationHasWaterInNearby)) {
                location = Generation.generateRandomLocation(world, config);
                continue;
            }

            location.setY(location.getY() + 1);
            if (location.getBlock().getType() != Material.AIR) {
                continue;
            }
            savedCoordinates.add(location);
            return location;
        }
        return null;
    }

    private static Location generateFromArray() {
        for (Location location : savedCoordinates) {
            Block block = location.getBlock();

            if (block.getType() == Material.SUGAR_CANE) {
                continue;
            }

            boolean isLocationSafeCheck = isLocationSafe(location);
            boolean isLocationHasWaterInNearby = placeSugarCane(location);
            if (isLocationSafeCheck || isLocationHasWaterInNearby) {
                return location;
            } else {
                savedCoordinates.remove(location);
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

        return !(!(trueBlocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid()) || (above2x.getType().isSolid())
                || (block.getType() == Material.SUGAR_CANE));
    }

    private static boolean placeSugarCane(Location location) {
        location.setY(location.getY() - 1);
        Block mainBlock = location.getBlock();
        Block[] blocks = {
                mainBlock.getRelative(BlockFace.WEST),
                mainBlock.getRelative(BlockFace.EAST),
                mainBlock.getRelative(BlockFace.NORTH),
                mainBlock.getRelative(BlockFace.SOUTH),
        };
        for (Block block : blocks) {
            if (block.getType() == Material.WATER) {
                return true;
            }

        }

        return false;
    }
}
