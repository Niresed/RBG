package me.niresed.ngb.Utils;

import me.niresed.ngb.Main.NGB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class NGBUtils {
    // plugin
    private static final Plugin plugin = NGB.getPlugin(NGB.class);
    // coordinate, in config.yml
    private static final ArrayList<Integer> coordinate = (ArrayList<Integer>) plugin.getConfig().getIntegerList("zone");
    // trueBlocks, blocks on which you can spawn reeds
    public static HashSet<Material> trueBlocks = new HashSet<>();

    static {
        trueBlocks.add(Material.SAND);
        trueBlocks.add(Material.GRASS);
        trueBlocks.add(Material.DIRT);
    }

    public static Location generateLocation(){
        World world = Bukkit.getWorld("world");
        Location location = generateRandomLocation(world);

        while (true){
            if(!(isSuitablePlace(location) && isLocationSafe(location))){
                location = generateRandomLocation(world);
            } else{
                break;
            }
        }
        return location;
    }

    public static Location generateRandomLocation(World world){
        Random random = new Random();
        int minX = coordinate.get(0), minZ = coordinate.get(1);
        int maxX = coordinate.get(2), maxZ = coordinate.get(3);
        int x = random.nextInt((maxX - minX + 1) + minX);
        int y = 0;
        int z = random.nextInt((maxZ - minZ + 1) + minZ);

        Location location = new Location(world, x, y, z);
        y = location.getWorld().getHighestBlockYAt(location) + 1;
        location.setY(y);
        return location;
    }
    private static boolean isLocationSafe(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);
        Block above2x = location.getWorld().getBlockAt(x, y + 2, z);
        return !(!trueBlocks.contains(below.getType()) || (block.getType().isSolid()) ||
                (above.getType().isSolid()) || (above2x.getType().isSolid()));
    }
    public static boolean isSuitablePlace(Location location){
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Block left = location.getWorld().getBlockAt(x, y, z - 1);
        Block right = location.getWorld().getBlockAt(x, y, z + 1);
        Block up = location.getWorld().getBlockAt(x + 1, y, z);
        Block under = location.getWorld().getBlockAt(x - 1, y, z);
        Block leftUnder = location.getWorld().getBlockAt(x - 1, y, z - 1);
        Block rightUnder = location.getWorld().getBlockAt(x - 1, y, z + 1);
        Block leftUp = location.getWorld().getBlockAt(x + 1, y, z - 1);
        Block rightUp = location.getWorld().getBlockAt(x + 1, y, z + 1);
        return (left.getType() == Material.WATER && right.getType() == Material.WATER &&
                up.getType() == Material.WATER && under.getType() == Material.WATER &&
                leftUnder.getType() == Material.WATER && rightUnder.getType() == Material.WATER &&
                leftUp.getType() == Material.WATER && rightUp.getType() == Material.WATER);
    }
}
