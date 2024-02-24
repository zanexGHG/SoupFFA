package dev.zanex.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildCommand implements CommandExecutor {
    public static boolean buildMode = false;
    public List<Player> playersInBuildMode = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("you have to be a Player to execute this command");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("SoupFFa.command.build")){
           player.sendMessage("You don't have the permissions to execute this command");
           return false;
        }

        if (args.length == 1 && Bukkit.getServer().getOnlinePlayers().contains(args[0])){

        } else {
            player.sendMessage("§c§l! §7This player is not online or does not exist.");
        }


          return true;
    }

    private void activateBuildMode(Player player){

    }

    public List<Player> getPlayersInBuildMode() {
        return playersInBuildMode;
    }
}
