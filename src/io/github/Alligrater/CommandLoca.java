package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandLoca implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(sender instanceof Player) {
			Player player = (Player)sender;
			Location loca = player.getLocation();
			int locaX = loca.getBlockX();
			int locaY = loca.getBlockY();
			int locaZ = loca.getBlockZ();
			World world = player.getWorld();
			String worldname = world.getName();
			Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + "§b目前正在世界" + worldname + ", X=" + locaX + ", Y=" + locaY + ", Z=" + locaZ);
			return true;
		}
		else {
			sender.sendMessage("§a你你你你想干啥！");
			return false;
		}

	}
	
}
