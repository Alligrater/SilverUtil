package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocalTalk implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(sender.hasPermission("silverutil.saylocal")) {
			String world = arg3[0];
			String message = concatArray(arg3, 1);
			message = message.replaceAll("&", "§");
			message = message.replaceAll("§§", "&");
				for(Player player: Bukkit.getOnlinePlayers()) {
					if(player.getWorld().getName().equals(world)) {
						player.sendMessage(message);
					}
				}
			
			return true;
		}
		else {
			sender.sendMessage("§c你没有权限这样做！");
		}
		return true;
	}
	
	public String concatArray(String[] arg3, int start) {
		String out = "";
		for (int i = start; i < arg3.length; i++) {
			if(i == arg3.length - 1) {
				out = out+arg3[i];
			}
			else {
				out = out+arg3[i] + " ";
			}
		}
		
		return out;
	}
	
}
