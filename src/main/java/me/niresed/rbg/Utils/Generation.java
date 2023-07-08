package me.niresed.rbg.Utils;

import me.niresed.rbg.Main.RBG;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Generation
{
    private static final Plugin plugin = RBG.getPlugin(RBG.class);

    public static int zones = plugin.getConfig().getInt("how many zones");

    public static boolean runTimer = true;

    public static void generateBlock(){
        World world = Bukkit.getWorld("world");
        while (runTimer)
        {
            for (String i : plugin.getConfig().getStringList("zones"))
            {
                String type = plugin.getConfig().getConfigurationSection(i).getString("type");

                if (type.equals("cane"))
                {
                    Location location = CaneUtils.generateLocation(i, world);
                    if (location != null)
                    {
                        location.setY(location.getY() + 1);
                        Block block = location.getBlock();
                        block.setType(Material.SUGAR_CANE);
                    }

                }
                else if (type.equals("clay"))
                {
                    Location location = ClayUtils.generateLocation(i, world);
                    if (location != null)
                    {
                        Block block = location.getBlock();
                        block.setType(Material.CLAY);
                    }

                }

            }

        }

    }
}
