package dev.zanex.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§l! §7Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("soupffa.command.gamemode")) {
            player.sendMessage("§c§l! §7You do not have permission to execute this command.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§c§l! §7Usage: §b/gm <GAMEMODE> [PLAYER]");
            return true;
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "0":
                case "s":
                case "survival":
                    if(player.getGameMode() == GameMode.SURVIVAL) {
                        player.sendMessage("§c§l! §7You are already in §aSURVIVAL§7.");
                        return true;
                    }

                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("§a§l> §7Your gamemode has been updated to §aSURVIVAL§7.");
                    break;

                case "1":
                case "c":
                case "creative":
                    if(player.getGameMode() == GameMode.CREATIVE) {
                        player.sendMessage("§c§l! §7You are already in §aCREATIVE§7.");
                        return true;
                    }

                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage("§a§l> §7Your gamemode has been updated to §aCREATIVE§7.");
                    break;

                case "2":
                case "a":
                case "adventure":
                    if(player.getGameMode() == GameMode.ADVENTURE) {
                        player.sendMessage("§c§l! §7You are already in §aADVENTURE§7.");
                        return true;
                    }

                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage("§a§l> §7Your gamemode has been updated to §aADVENTURE§7.");
                    break;

                case "3":
                case "sp":
                case "spectator":
                    if(player.getGameMode() == GameMode.SPECTATOR) {
                        player.sendMessage("§c§l! §7You are already in §aSPECTATOR§7.");
                        return true;
                    }

                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("§a§l> §7Your gamemode has been updated to §aSPECTATOR§7.");
                    break;

                default:
                    player.sendMessage("§c§l! §7Invalid gamemode. Usage: §b/gm <0|1|2|3> [player]");
                    break;
            }
            return true;
        }

        if (args.length == 2) {
            if (Bukkit.getServer().getPlayer(args[1]) != null) {
                Player target = Bukkit.getServer().getPlayer(args[1]);

                switch (args[0]) {
                    case "0":
                    case "s":
                    case "survival":
                        if(target.getGameMode() == GameMode.SURVIVAL) {
                            player.sendMessage("§c§l! §7" + target.getName() + " is already in §aSURVIVAL§7.");
                            return true;
                        }

                        target.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("§a§l> §7" + target.getName() + "'s gamemode has been updated to §aSURVIVAL§7.");
                        target.sendMessage("§a§l> §7Your gamemode has been updated to §aSURVIVAL§7 by " + player.getDisplayName() + ".");
                        break;

                    case "1":
                    case "c":
                    case "creative":
                        if(target.getGameMode() == GameMode.CREATIVE) {
                            player.sendMessage("§c§l! §7" + target.getName() + " is already in §aCREATIVE§7.");
                            return true;
                        }

                        target.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("§a§l> §7" + target.getName() + "'s gamemode has been updated to §aCREATIVE§7.");
                        target.sendMessage("§a§l> §7Your gamemode has been updated to §aCREATIVE §7by " + player.getDisplayName() + ".");
                        break;

                    case "2":
                    case "a":
                    case "adventure":
                        if(target.getGameMode() == GameMode.ADVENTURE) {
                            player.sendMessage("§c§l! §7" + target.getName() + " is already in §aADVENTURE§7.");
                            return true;
                        }

                        target.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage("§a§l> §7" + target.getName() + "'s gamemode has been updated to §aADVENTURE§7.");
                        target.sendMessage("§a§l> §7Your gamemode has been updated to §aADVENTURE §7by " + player.getDisplayName() + ".");
                        break;

                    case "3":
                    case "sp":
                    case "spectator":
                        if(target.getGameMode() == GameMode.SPECTATOR) {
                            player.sendMessage("§c§l! §7" + target.getName() + " is already in §aSPECTATOR§7.");
                            return true;
                        }

                        target.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("§a§l> §7" + target.getName() + "'s gamemode has been updated to §aSPECTATOR§7.");
                        target.sendMessage("§a§l> §7Your gamemode has been updated to §aSPECTATOR §7by " + player.getDisplayName() + ".");
                        break;

                    default:
                        player.sendMessage("§c§l! §7Invalid gamemode. Usage: §b/gm <0|1|2|3> [player]");
                        break;
                }
            } else {
                player.sendMessage("§c§l! §7Player not found.");
            }
            return true;
        }

        return false;
    }
}
