package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RtpMe implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		if(arg3.length == 1 && sender.hasPermission("SilverUtil.rtp")) {
			Player player = Bukkit.getPlayer(arg3[0]);
			if(player != null) {
				double size = 15000;
				int locaX = (int) ((Math.random() - 0.5)*size*2);;
				int locaZ = (int) ((Math.random() - 0.5)*size*2);;
				int locaY = player.getWorld().getHighestBlockYAt(locaX, locaZ);
				while(player.getWorld().getBlockAt(locaX, locaY - 1, locaZ).getType() == Material.WATER || player.getWorld().getBlockAt(locaX, locaY - 1, locaZ).getType() == Material.STATIONARY_WATER ||  player.getWorld().getBlockAt(locaX, locaY - 1, locaZ).getType() == Material.LAVA ||  player.getWorld().getBlockAt(locaX, locaY - 1, locaZ).getType() == Material.STATIONARY_LAVA) {
					Bukkit.getLogger().info("[RtpMe]Attempting to teleport player to (" + locaX + "," + locaY + "," + locaZ + ") ... but failed. Retrying.");
					locaX = (int) ((Math.random() - 0.5)*size*2);
					locaZ = (int) ((Math.random() - 0.5)*size*2);
					locaY = player.getWorld().getHighestBlockYAt(locaX, locaZ);
				}
				player.sendMessage("§b你已被传送至 X=" + locaX + ", Y=" + locaY + ", Z=" + locaZ);
				Location loca = new Location(player.getWorld(), locaX, locaY, locaZ);
				player.teleport(loca);
				return true;
			}
			else {
				sender.sendMessage("§c你你你你想干嘛！");
				return false;
			}


		}
		else {
			sender.sendMessage("§c你你你你想干嘛！");
			return true;
		}
		// TODO Auto-generated method stub
	}

}
