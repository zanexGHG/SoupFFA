package dev.zanex.util.manager;

import dev.zanex.soupffa.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {

    public void createScoreboard(Player player, String scoreboardName, String title, String[] lines) {
        Scoreboard scoreboard = player.getScoreboard();
        scoreboard.getObjective(scoreboardName).setDisplayName(title);
        scoreboard.getObjective(scoreboardName).setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void updateScoreboard(Player player, Scoreboard scoreboard, String scoreboardName, String title, String[] lines) {
        new BukkitRunnable() {
            @Override
            public void run() {
                scoreboard.getObjective(scoreboardName).setDisplayName(title);
                for(int i = 0; i < lines.length; i++) {
                    scoreboard.getObjective(scoreboardName).getScore(lines[i]).setScore(i);
                }
                player.setScoreboard(scoreboard);
            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0, 10);
    }

    public void removeScoreboard(Player player) {
        player.setScoreboard(null);
    }
}
