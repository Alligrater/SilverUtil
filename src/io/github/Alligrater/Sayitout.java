package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sayitout implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		String message = concatArray(arg3, 0);
		
		if(sender instanceof Player) {
			if(message.equals("")) {
				
			}
			else {
				Player player = (Player)sender;
				JoinColor.updateDname(player);
				Bukkit.broadcastMessage(player.getDisplayName() + ": ¡ìr" + message);
			}

		}
		else {
			if(message.equals("")) {
				
			}
			else {
				Bukkit.broadcastMessage("¡ìd[X]·þÎñÆ÷Äï: ¡ìr" + message);
			}
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

