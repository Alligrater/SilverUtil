package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandOnline implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(sender.hasPermission("silverutil.getonline")) {
			for(Player player:Bukkit.getOnlinePlayers()) {
				
				Location loca = player.getLocation();
				int locaX = loca.getBlockX();
				int locaY = loca.getBlockY();
				int locaZ = loca.getBlockZ();
				String world = player.getWorld().getName();
				String pname = JoinColor.updateDname(player);
				sender.sendMessage(ChatColor.AQUA + pname + ChatColor.AQUA + " " + world + "@(" + locaX + ", " + locaY + ", " + locaZ + ")");
			}
			return true;
		}
		else {
			sender.sendMessage("§c你不能这样做！");
			return false;
		}

	}

}
