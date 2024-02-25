package dev.zanex.commands;

import dev.zanex.util.Information;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SoupFFACommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage("§8§m                                      §8§l[§9§lSoupFFA§8§l]§8§m                                      ");
        sender.sendMessage("§a> §9§lSoupFFA §fis a neat FFA plugin based on HardcoreGames.");
        sender.sendMessage("§a> §fVersion: §a" + Information.version);
        sender.sendMessage("§a> §fAuthors: §bzanexGHG§f, §9MoritzMCC");
        sender.sendMessage("§a> §fGitHub: §3§nhttps://github.com/zanexGHG/SoupFFA");
        sender.sendMessage("§8§m                                                                                            ");

        return false;
    }
}
