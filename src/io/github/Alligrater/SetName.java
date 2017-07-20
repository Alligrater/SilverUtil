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
			sender.sendMessage("ÄãÄãÄãÄãÏë¸ÉÂï£¡");
			return false;
		}
		else {
			Player player = (Player) sender;
			if(player.getInventory().getItemInMainHand() != null && (arg3.length == 1)) {
				String rename = arg3[0];
				ItemStack items = player.getInventory().getItemInMainHand();
				rename.replaceAll("&", "¡ì");
				rename.replaceAll("¡ì¡ì", "&");
				ItemMeta remeta = player.getInventory().getItemInMainHand().getItemMeta();
				remeta.setDisplayName(rename);
				items.setItemMeta(remeta);
				return true;
			}
			else {
				sender.sendMessage("ÄãÄãÄãÄãÏë¸ÉÂï£¡");
				return false;
			}
		}
	}

}
