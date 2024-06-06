package dev.zanex.player;

import dev.zanex.soupffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SoupFFAPlayer {
    private Player player;
    private boolean isSpectator;
    private boolean isFrozen;
    private boolean isInCombat;
    private int kills;
    private int deaths;

    public SoupFFAPlayer(Player player) {
        this.player = player;
    }

    public void setSpectator(boolean spectator) {
        isSpectator = spectator;

        if (spectator) {
            player.setAllowFlight(true);
            player.setFlying(true);
            for(Player online : Bukkit.getOnlinePlayers()) {
                online.hidePlayer(player);
            }

            // set items for spectator mode and save items from player mode
        } else {
            player.setAllowFlight(false);
            player.setFlying(false);
            for(Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(player);
            }

            // remove items for spectator mode and set items for player mode
        }
    }

    public boolean isSpectator() {
        return isSpectator;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;

        new BukkitRunnable() {
            @Override
            public void run() {
                player.setWalkSpeed(frozen ? 0 : 0.2f);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, frozen ? Integer.MAX_VALUE : 0, 0));
            }
        }.runTask(Main.getInstance());
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setInCombat(boolean inCombat) {
        isInCombat = inCombat;

        // if hit in combat is true then let other players hit the 2 in combat
    }

    public boolean isInCombat() {
        return isInCombat;
    }

    public void addKill() {
        kills++;
    }

    public int getKills() {
        return kills;
    }

    public void addDeath() {
        deaths++;
    }

    public int getDeaths() {
        return deaths;
    }

    public double getKDR() {
        return kills / deaths;
    }
}
