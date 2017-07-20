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
			if(player.getDisplayName().contains("[MUTED]")) {
				player.setDisplayName(player.getDisplayName().substring(8));
				player.sendMessage("��aChat Now Unmuted.");
			}
			else {
				player.setDisplayName("��c[MUTED]"+player.getDisplayName());
				player.sendMessage("��cChat Now Muted.");
			}

		}
		else {
			sender.sendMessage("��c�����������ɶ��");
			return false;
		}
		
		return true;
	}

}
