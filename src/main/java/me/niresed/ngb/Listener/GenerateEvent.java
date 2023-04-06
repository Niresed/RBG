package me.niresed.ngb.Listener;

import me.niresed.ngb.Events.NGBEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GenerateEvent implements Listener {
    @EventHandler
    public static void GenerateBlock(NGBEvent event){
        System.out.println("Hello");
    }
}
