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
				player.sendMessage("��a���������ѿ�����");
			}
			else {
				muted.add(player.getUniqueId());
				player.sendMessage("��c���������ѹرա�");
			}

		}
		else {
			sender.sendMessage("��c�����������ɶ��");
			return false;
		}
		
		return true;
	}

}
