package io.github.Alligrater;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChat implements CommandExecutor{
	public static ArrayList<UUID> muted = new ArrayList<UUID>();
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(muted.contains(player.getUniqueId())) {
				muted.remove(player.getUniqueId());
				player.sendMessage("§a聊天声音已开启。");
			}
			else {
				muted.add(player.getUniqueId());
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
