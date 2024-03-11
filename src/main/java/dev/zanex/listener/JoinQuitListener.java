package dev.zanex.listener;

import dev.zanex.soupffa.Main;
import dev.zanex.util.MySQLManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {
    private final MySQLManager mySQLManager = new MySQLManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        mySQLManager.executeUpdateStatement(Main.getConnection(), ("INSERT INTO `players` (`uuid`, `name`, `kills`, `deaths`) VALUES ('" + event.getPlayer().getUniqueId() + "', '" + event.getPlayer().getName() + "', 0, 0)"));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
}
