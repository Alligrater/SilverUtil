package io.github.Alligrater;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetName implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		if(!(sender instanceof Player) || !sender.hasPermission("silverutil.setname")) {
			sender.sendMessage("¡ì4No Permission");
			return true;
		}
		else {
			Player player = (Player) sender;
			if(player.getInventory().getItemInMainHand() != null) {
				String rename = concatArray(arg3);
				ItemStack items = player.getInventory().getItemInMainHand();
				rename = rename.replaceAll("&", "¡ì");
				rename = rename.replaceAll("¡ì¡ì", "&");
				ItemMeta remeta = player.getInventory().getItemInMainHand().getItemMeta();
				remeta.setDisplayName(rename);
				items.setItemMeta(remeta);
				sender.sendMessage("¡ìbRenamed!");
				return true;
			}
			else {
				sender.sendMessage("¡ì4No Item In Hand");
				return true;
			}
		}
	}
	
	public String concatArray(String[] arg3) {
		String out = "";
		for (int i = 0; i < arg3.length; i++) {
			out = out+arg3[i] + " ";
		}
		
		return out;
	}

}
