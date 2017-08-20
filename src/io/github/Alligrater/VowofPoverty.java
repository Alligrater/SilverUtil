package io.github.Alligrater;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class VowofPoverty implements CommandExecutor {
	public static ArrayList<UUID> monk = new ArrayList<UUID>();
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if (monk.contains(player.getUniqueId())) {
				monk.remove(player.getUniqueId());
				Bukkit.broadcastMessage(ChatColor.GOLD + ((Player) sender).getName() + "不再是Monk了！");
				if (JoinColor.customtag.get(((Player) sender).getName()) != null && JoinColor.customtag.get(((Player) sender).getName()).equals("§6[Monk]")) {
					JoinColor.customtag.remove(((Player) sender).getName());
				}
				player.setDisplayName(JoinColor.updateDname(player));
			}
			else {
				monk.add(player.getUniqueId());
				Bukkit.broadcastMessage(ChatColor.GOLD + ((Player) sender).getName() + "化身为Monk！");
				JoinColor.customtag.put(((Player) sender).getName(), "§6[Monk]");
				player.setDisplayName(JoinColor.updateDname(player));
			}
		}
		
		return true;
	}
}
