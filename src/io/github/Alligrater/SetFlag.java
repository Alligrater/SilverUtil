package io.github.Alligrater;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetFlag implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		if(sender instanceof Player && arg3.length == 1) {
			Player player = (Player)sender;
			if(player.getInventory().getItemInMainHand() != null) {
				ItemStack item = player.getInventory().getItemInMainHand();
				
			}
		}
		return false;
	}

}
