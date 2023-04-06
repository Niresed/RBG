package me.niresed.ngb.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NGBUtils {
    public static Location generateLocation(Player player){
        Location location = new Location(player.getWorld(), 0, 0, 0);
        return location;
    }
}
