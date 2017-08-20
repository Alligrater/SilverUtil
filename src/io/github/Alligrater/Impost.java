package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Impost implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(sender.hasPermission("silverutil.impost")) {
			String pname = arg3[0];
			Player target = Bukkit.getPlayer(pname);
			String message = concatArray(arg3, 1);
			message = message.replaceAll("&", "§");
			message = message.replaceAll("§§", "&");
			if(target != null) {
				if(message.equals("")) {
					
				}
				else {
					Bukkit.broadcastMessage(target.getDisplayName() + "§o: §r" + message);
				}
			}
			else{
				if(message.equals("")) {

				}
				else {
					pname = pname.replaceAll("&", "§");
					pname = pname.replaceAll("§§", "&");
					Bukkit.broadcastMessage(getdname(pname) + "§o: §r" + message);
				}

			}
			
		}
		else {
			sender.sendMessage("§c你你你你想干嘛！");
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
	
	public static String getdname (String pname) {
		if(pname.contains("[") && pname.contains("]")) {
			return pname;
		}
		else if(pname.equalsIgnoreCase("server")) {
			return "§d[X]服务器娘";
		}
		else {
			OfflinePlayer player = Bukkit.getOfflinePlayer(pname);
			String title = "";
			
			String outstring = "";
			
			
			if(VowofPoverty.monk.contains(player.getUniqueId())) {
				title = "§6[Monk]";
			}
			else if (player.isOp()){
				title = "§c[O]";

			}
			else if(player.isBanned()) {
				title = "§8[B]";
			}

			else {
				title = "§2[P]";
			}
			
			outstring = title + player.getName();
			return outstring;
		}
			

	}
	
}
