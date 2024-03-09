package dev.zanex.listener;

import dev.zanex.commands.BuildCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class DisableEventListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(BuildCommand.playersInBuildMode.contains(event.getPlayer().getUniqueId()))return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(BuildCommand.playersInBuildMode.contains(event.getPlayer().getUniqueId())) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onAchiev(PlayerAchievementAwardedEvent event) {
        event.setCancelled(BuildCommand.playersInBuildMode.contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(BuildCommand.getPlayersInBuildMode().isEmpty());
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        event.setCancelled(BuildCommand.getPlayersInBuildMode().isEmpty());
    }
}
