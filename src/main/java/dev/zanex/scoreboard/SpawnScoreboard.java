package dev.zanex.scoreboard;

import dev.zanex.player.SoupFFAPlayer;
import dev.zanex.soupffa.Main;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnScoreboard {
    private Player player;
    private SoupFFAPlayer ffaPlayer = new SoupFFAPlayer(player);
    FastBoard board = new FastBoard(player);

    SpawnScoreboard(Player player) {
        this.player = player;
    }

    public void createScroeboard() {
        board.updateTitle("§9§lSoupFFA");
        board.updateLines(
                "§7§m----------------",
                "§7Kills: §f" + ffaPlayer.getKills(),
                "§7Deaths: §f" + ffaPlayer.getDeaths(),
                "§7K/D: §f" + ffaPlayer.getKDR(),
                "§7§m----------------"
        );

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    board.delete();
                    cancel();
                    return;
                }

                board.updateLines(
                        "§7§m----------------",
                        "§7Kills: §f" + ffaPlayer.getKills(),
                        "§7Deaths: §f" + ffaPlayer.getDeaths(),
                        "§7K/D: §f" + ffaPlayer.getKDR(),
                        "§7§m----------------"
                );
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0, 20);
    }
}
