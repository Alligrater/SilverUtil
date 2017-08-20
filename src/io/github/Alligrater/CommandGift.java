package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class CommandGift implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {

		// TODO Auto-generated method stub
		if(sender instanceof Player && arg3.length == 1) {
			Player player = (Player)sender;
			Player receiver = Bukkit.getPlayer(arg3[0]);
			if(receiver != null) {
				if((!SilverUtil.forbidden.contains(receiver.getWorld().getName()) && !SilverUtil.forbidden.contains(receiver.getWorld().getName())) || (player.hasPermission("silverutil.gift.anywhere") || receiver.hasPermission("silverutil.gift.anywhere"))) {

					
					ItemStack inhand = player.getInventory().getItemInMainHand();
					ItemMeta newmeta = inhand.getItemMeta();
					List<String> newlore = new ArrayList<String>();
					
					if(newmeta.hasLore()) {
						newlore = newmeta.getLore();
						newlore.add("Received from " + player.getName());
					}
					else {
						newlore.add("Received from " + player.getName());
					}
					
					if(inhand != null && arg3.length == 1 && receiver != null) {
						player.sendMessage("§b礼物已送出！");
						player.getInventory().setItemInMainHand(null);
						newmeta.setLore(newlore);
						inhand.setItemMeta(newmeta);
						receiver.getInventory().addItem(inhand);
						receiver.sendMessage("§b你收到了来自" + ChatColor.AQUA + player.getName() + ChatColor.AQUA + "的礼物!");
					}
					else {
						player.sendMessage("§c参数有误，没能送出礼物。");
						return false;
					}
				}
				else {
					player.sendMessage("§c该玩家当前不在常规世界！");
				}
			}
			else {
				player.sendMessage("§c该玩家当前不在线！");
			}
			
		}
		else if(sender instanceof Player && arg3.length == 2) {
			Player player = (Player)sender;
			Player receiver = Bukkit.getPlayer(arg3[0]);
			if(receiver != null) {
				if(arg3[1].equals("nl") || arg3[1].equals("nolore")) {

					if((!SilverUtil.forbidden.contains(receiver.getWorld().getName()) && !SilverUtil.forbidden.contains(receiver.getWorld().getName())) || (player.hasPermission("silverutil.gift.anywhere") || receiver.hasPermission("silverutil.gift.anywhere"))) {
						ItemStack inhand = player.getInventory().getItemInMainHand();
						if(inhand != null && arg3.length == 2) {
							player.sendMessage("§b礼物已送出！");
							player.getInventory().setItemInMainHand(null);
							receiver.getInventory().addItem(inhand);
							receiver.sendMessage("§b你收到了来自" + ChatColor.AQUA + player.getName() + ChatColor.AQUA + "的礼物!");
						}
						else {
							player.sendMessage("§c参数有误，没能送出礼物。");
							return false;
						}
					}
					else {
						player.sendMessage("§c该玩家当前不在常规世界！");
					}

				}
			}
			else {
				player.sendMessage("§c该玩家当前不在线！");
			}

			}

		return true;
	}
	
}
