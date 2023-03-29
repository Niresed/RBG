package me.niresed.ngb.Utils;

import org.bukkit.entity.Player;

public class NGBUtils {
    public static boolean duration;

    public static void repeat(Player player) throws InterruptedException {
        int count = 0;
        while (duration && count < 10000){
            Thread.sleep(10000);
            player.sendMessage("Hello");
            count += 1;
        }
    }
}
