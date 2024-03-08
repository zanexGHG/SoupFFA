package dev.zanex.commands;

import dev.zanex.soupffa.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("§c§l! §7Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("soupffa.command.fly")) {
            player.sendMessage("§c§l! §7You do not have permission to execute this command.");
            return true;
        }

        if(args.length == 0) {
            if(player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.sendMessage("§a§l> §7Your flight mode has been set to §bFALSE§7.");
            } else {
                player.setAllowFlight(true);
                player.sendMessage("§a§l> §7Your flight mode has been set to §bTRUE§7.");
            }
        } else if(args.length == 1) {
            if(Main.getInstance().getServer().getPlayer(args[0]) != null) {
                Player target = Main.getInstance().getServer().getPlayer(args[0]);

                if(target.getAllowFlight()) {
                    target.setAllowFlight(false);
                    player.sendMessage("§a§l> §7The flight mode of §b" + target.getName() + " §7has been set to §bFALSE§7.");
                    target.sendMessage("§a§l> §7Your flight mode has been set to §bFALSE§7.");
                } else {
                    target.setAllowFlight(true);
                    player.sendMessage("§a§l> §7The flight mode of §b" + target.getName() + " §7has been set to §bTRUE§7.");
                    target.sendMessage("§a§l> §7Your flight mode has been set to §bTRUE§7.");
                }
            } else {
                player.sendMessage("§c§l! §7The player §b" + args[0] + " §7is not online.");
            }
        } else {
            player.sendMessage("§c§l! §7Usage: §b/fly [PLAYER]");
        }

        return false;
    }
}
