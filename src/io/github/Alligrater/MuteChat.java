package io.github.Alligrater;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChat implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(player.getDisplayName().contains("[Muted]")) {
				player.setDisplayName(player.getDisplayName().replaceAll("¡ì7\\[Muted\\]", ""));
				player.sendMessage("¡ìaChat Now Unmuted.");
			}
			else {
				player.setDisplayName("¡ì7[Muted]"+player.getDisplayName());
				player.sendMessage("¡ìcChat Now Muted.");
			}

		}
		else {
			sender.sendMessage("¡ìcÄãÄãÄãÄãÏë¸ÉÉ¶£¡");
			return false;
		}
		
		return true;
	}

}
