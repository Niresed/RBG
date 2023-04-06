package me.niresed.ngb.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.PluginEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class NGBEvent extends PluginEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    public NGBEvent(@NotNull Plugin plugin, Player player) {
        super(plugin);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
