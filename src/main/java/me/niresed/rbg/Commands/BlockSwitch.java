package me.niresed.rbg.Commands;

import me.niresed.rbg.Main.RBG;
import me.niresed.rbg.Utils.Generation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BlockSwitch implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("rbg.rbg")) {
                if (args[0].equals("on")) {
                    if (!Generation.runTimer) {
                        Generation.runTimer = true;
                        Generation.start(RBG.getPlugin(RBG.class).getConfig());
                    }
                } else if (args[0].equals("off")) {
                    Generation.runTimer = false;
                } else {
                    player.sendMessage(ChatColor.RED + "Wrong command");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You haven't permission for this command");
            }
        } else {
            if (args[0].equals("on")) {
                if (!Generation.runTimer) {
                    Generation.runTimer = true;
                    Generation.start(RBG.getPlugin(RBG.class).getConfig());
                }
            } else if (args[0].equals("off")) {
                Generation.runTimer = false;
            } else {
                Bukkit.getLogger().info(ChatColor.RED + "Wrong command");
            }
        }
        return true;
    }
}
