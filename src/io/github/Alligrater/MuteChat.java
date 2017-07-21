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
				player.setDisplayName(player.getDisplayName().replaceAll("§7\\[Muted\\]", ""));
				player.sendMessage("§a聊天声音已开启。");
			}
			else {
				player.setDisplayName("§7[Muted]"+player.getDisplayName());
				player.sendMessage("§c聊天声音已关闭。");
			}

		}
		else {
			sender.sendMessage("§c你你你你想干啥！");
			return false;
		}
		
		return true;
	}

}
