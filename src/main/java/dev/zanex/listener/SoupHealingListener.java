package dev.zanex.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SoupHealingListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getItem() != null && event.getItem().getType() == Material.MUSHROOM_SOUP) {

            double health = event.getPlayer().getHealth();
            double maxHealth = event.getPlayer().getMaxHealth();
            int hunger = event.getPlayer().getFoodLevel();

            if(health < maxHealth) {
                event.getPlayer().setHealth(Math.min(health + 7, maxHealth));
                event.getItem().setType(Material.BOWL);
            } else if(health == maxHealth && hunger < 20) {
                event.getPlayer().setFoodLevel(Math.min(hunger + 7, 20));
                event.getItem().setType(Material.BOWL);
            }
        }
    }
}