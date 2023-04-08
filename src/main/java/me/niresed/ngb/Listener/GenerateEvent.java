package me.niresed.ngb.Listener;

import me.niresed.ngb.Events.NGBEvent;
import me.niresed.ngb.Utils.NGBUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GenerateEvent implements Listener {
    @EventHandler
    public static void GenerateBlock(NGBEvent event){
        Location location = NGBUtils.generateLocation();
        System.out.println(location);
//        Block block = location.getBlock();
//        block.setType(Material.SUGAR_CANE);
//        Bukkit.getLogger().info("Block");
    }
}
