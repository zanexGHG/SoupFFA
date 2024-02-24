package dev.zanex.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildCommand implements CommandExecutor {

  public List<Player> playersInBuildMode = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("you have to be a Player to execute this command");
            return false;
        }
        Player player =(Player) sender;

        if (!player.hasPermission("SoupFFa.command.build")){
           player.sendMessage("You don't have the permissions to execute this command");
           return false;
        }

        if (args.length ==1){
            try{
               Player player1 =  Bukkit.getPlayer(args[1]);

            }catch ()
        }


          return true;
    }

    private void activateBuildMode(Player player){

    }

    public List<Player> getPlayersInBuildMode() {
        return playersInBuildMode;
    }
}
