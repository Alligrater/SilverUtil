package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpamCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if(sender.hasPermission("silverutil.spammsg")) {
			if(arg3.length >= 1) {
				String spammsg = concatArray(arg3);
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + spammsg);
				Bukkit.broadcastMessage(ChatColor.RED + spammsg);
				Bukkit.broadcastMessage(ChatColor.DARK_RED + spammsg);
				Bukkit.broadcastMessage(ChatColor.GOLD + spammsg);
				Bukkit.broadcastMessage(ChatColor.YELLOW + spammsg);
				Bukkit.broadcastMessage(ChatColor.GREEN + spammsg);
				Bukkit.broadcastMessage(ChatColor.AQUA + spammsg);
				Bukkit.broadcastMessage(ChatColor.DARK_BLUE + spammsg);
				Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + spammsg);
			}
			else {
				sender.sendMessage(ChatColor.DARK_RED + "You can't spam nothing!");
				return false;
			}
		}
		else {
			sender.sendMessage(ChatColor.DARK_RED + "You are not allowed to do this!");
		}
		return true;
	}
	
	
	public String concatArray(String[] arg3) {
		String out = "";
		for (int i = 0; i < arg3.length; i++) {
			out = out+arg3[i] + " ";
		}
		
		return out;
	}
}
