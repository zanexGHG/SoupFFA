package dev.zanex.commands;

import dev.zanex.soupffa.Main;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvseeCommandListener implements CommandExecutor, Listener {
    private Player player;
    private Player target;
    private Inventory inv;
    private ItemStack placeholder;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("§c§l! §7Only players can execute this command.");
        }

        player = (Player) sender;

        if(!player.hasPermission("soupffa.command.invsee")) {
            player.sendMessage("§c§l! §7You do not have permission to execute this command.");
            return true;
        }

        if(args.length != 1) {
            player.sendMessage("§c§l! §7Usage: §b/invsee <PLAYER>");
            return true;
        }

        if(Main.getInstance().getServer().getPlayer(args[0]) != null) {
            target = Main.getInstance().getServer().getPlayer(args[0]);

            OpenPlayerInventory(player, target);
        } else {
            player.sendMessage("§c§l! §7The player §b" + args[0] + " §7is not online.");
        }

        return false;
    }

    // make this faster and cleaner and add listener
    private void OpenPlayerInventory(Player player, Player target) {
        Inventory targetInv = target.getInventory();
        inv = Bukkit.createInventory(player, 54, "§8Inventory of §b" + target.getName());

        ItemStack[] armorContents = target.getEquipment().getArmorContents();
        ItemStack[] craftingContents = target.getOpenInventory().getTopInventory().getContents();

        ArrayUtils.reverse(armorContents);

        placeholder = new ItemStack(Material.THIN_GLASS);
        ItemMeta meta = placeholder.getItemMeta();
        meta.setDisplayName("§fplaceholder");
        placeholder.setItemMeta(meta);

        inv.setItem(0, armorContents[0]);
        inv.setItem(1, armorContents[1]);
        inv.setItem(2, armorContents[2]);
        inv.setItem(3, armorContents[3]);
        inv.setItem(4, placeholder);
        inv.setItem(5, placeholder);
        inv.setItem(6, placeholder);
        inv.setItem(7, craftingContents[1]);
        inv.setItem(8, craftingContents[2]);
        inv.setItem(9, placeholder);
        inv.setItem(10, placeholder);
        inv.setItem(11, placeholder);
        inv.setItem(12, placeholder);
        inv.setItem(13, placeholder);
        inv.setItem(14, placeholder);
        inv.setItem(15, placeholder);
        inv.setItem(16, craftingContents[3]);
        inv.setItem(17, craftingContents[4]);

        for(int i = 9; i < 36; i++) {
            inv.setItem(i + 9, targetInv.getItem(i));
        }

        for(int i = 0; i < 9; i++) {
            inv.setItem(i + 45, targetInv.getItem(i));
        }

        player.openInventory(inv);
    }

    @EventHandler
    public void onItemMove(InventoryMoveItemEvent event) {
        if(event.getItem().getItemMeta().getDisplayName().equals(placeholder.getItemMeta().getDisplayName())) {
            event.setCancelled(true);
        }
    }
}