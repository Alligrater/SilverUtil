package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(arg3.length == 1) {
			if(sender instanceof Player) {
				Player player = (Player)sender;
				Player receiver = Bukkit.getPlayer(arg3[0]);
				if (Bukkit.getPlayer(arg3[0]) != null) {
					receiver.sendTitle(ChatColor.AQUA + player.getName(), ChatColor.AQUA + "Bling了你");
					receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , 1, 1);
					sender.sendMessage(ChatColor.AQUA + "Bling成功发送！");
				}
				
				else if(arg3[0].equals("all") && sender.hasPermission("silverutil.bling.all")) {
					int i = 0;
					for (Player players: Bukkit.getOnlinePlayers()) {
						players.sendTitle(ChatColor.AQUA + player.getName(), ChatColor.AQUA + "Bling了你");
						players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , 1, 1);
						i++;
					}
					sender.sendMessage(ChatColor.AQUA + "Bling成功发送至" + i + "人！");
				}
				
				else {
					sender.sendMessage(ChatColor.DARK_RED + arg3[0] + ChatColor.DARK_RED + " is not online!");
				}
			}
			else {

				Player receiver = Bukkit.getPlayer(arg3[0]);
				if (Bukkit.getPlayer(arg3[0]) != null) {
					receiver.sendTitle(ChatColor.AQUA + "控制台", ChatColor.AQUA + "Bling了你");
					receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , 1, 1);
					sender.sendMessage(ChatColor.AQUA + "Bling成功发送！");
				}
				
				else if(arg3[0].equals("all") && sender.hasPermission("silverutil.bling.all")) {
					int i = 0;
					for (Player players: Bukkit.getOnlinePlayers()) {
						players.sendTitle(ChatColor.AQUA + "控制台", ChatColor.AQUA + "Bling了你");
						players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , 1, 1);
						i++;
					}
					sender.sendMessage(ChatColor.AQUA + "Bling成功发送至" + i + "人！");
				}
				
				else {
					sender.sendMessage(ChatColor.DARK_RED + arg3[0] + ChatColor.DARK_RED + "当前不在线！");
				}
			}
		}
		else if(arg3.length > 1) {
			if(sender instanceof Player) {
				Player player = (Player)sender;
				Player receiver = Bukkit.getPlayer(arg3[0]);
				if (Bukkit.getPlayer(arg3[0]) != null) {
					receiver.sendTitle(ChatColor.AQUA + player.getName(), ChatColor.AQUA + "Bling了你");
					receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , 1, 1);
					receiver.sendMessage(ChatColor.AQUA + player.getName() + " Bling了你并说: " + concatArray(arg3, 1));
					sender.sendMessage(ChatColor.AQUA + "Bling成功发送！");
				}
				
				else if(arg3[0].equals("all") && sender.hasPermission("silverutil.bling.all")) {
					int i = 0;
					for (Player players: Bukkit.getOnlinePlayers()) {
						players.sendTitle(ChatColor.AQUA + player.getName(), ChatColor.AQUA + "Bling了你");
						players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , 1, 1);
						players.sendMessage(ChatColor.AQUA + player.getName() + " Bling了你并说: " + concatArray(arg3, 1));
						i++;
					}
					sender.sendMessage(ChatColor.AQUA + "Bling成功发送至" + i + "人！");
				}
				
				else {
					sender.sendMessage(ChatColor.DARK_RED + arg3[0] + ChatColor.DARK_RED + "当前不在线！");
				}
			}
			else {

				Player receiver = Bukkit.getPlayer(arg3[0]);
				if (Bukkit.getPlayer(arg3[0]) != null) {
					receiver.sendTitle(ChatColor.AQUA + "控制台", ChatColor.AQUA + "Bling了你");
					receiver.playSound(receiver.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , 1, 1);
					receiver.sendMessage(ChatColor.AQUA + "控制台Bling了你并说: " + concatArray(arg3, 1));
					sender.sendMessage(ChatColor.AQUA + "Bling成功发送！");
				}
				
				else if(arg3[0].equals("all") && sender.hasPermission("silverutil.bling.all")) {
					int i = 0;
					for (Player players: Bukkit.getOnlinePlayers()) {
						players.sendTitle(ChatColor.AQUA + "控制台", ChatColor.AQUA + "Bling了你");
						players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , 1, 1);
						players.sendMessage(ChatColor.AQUA + "控制台Bling了你并说: " + concatArray(arg3, 1));
						i++;
					}
					sender.sendMessage(ChatColor.AQUA + "Bling成功发送至" + i + "人！");
				}
				
				else {
					sender.sendMessage(ChatColor.DARK_RED + arg3[0] + ChatColor.DARK_RED + "当前不在线！");
				}
			}
		}
		else {
			sender.sendMessage(ChatColor.DARK_RED + "你不能Bling空气！");
			return false;
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
