package io.github.Alligrater;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetLore implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		if(!(sender instanceof Player) || !sender.hasPermission("silverutil.setlore")) {
			sender.sendMessage("ÄãÄãÄãÄãÏë¸ÉÂï£¡");
			return false;
		}
		else {
			Player player = (Player) sender;
			if(player.getInventory().getItemInMainHand() != null && (arg3.length >= 2)) {
				String be = arg3[0];
				ItemMeta oldmeta = player.getInventory().getItemInMainHand().getItemMeta();
				List<String> oldlore = oldmeta.getLore();
				List<String> newlore = oldlore;
				if(be == "add" && arg3.length == 2) {
					newlore.add(arg3[1]);
				}
				else if(be == "remove" && arg3.length == 2) {
					try {
						int i = Integer.parseInt(arg3[1]);
						oldlore.get(i);
					} catch (Exception e) {
						sender.sendMessage("ÄãÄãÄãÄãÏë¸ÉÂï£¡");
						throw e;
					}
					newlore.remove(Integer.parseInt(arg3[1]));
				}
				else if (be == "set" && arg3.length == 3) {
					try {
						int i = Integer.parseInt(arg3[1]);
						oldlore.get(i);
					} catch (Exception e) {
						sender.sendMessage("ÄãÄãÄãÄãÏë¸ÉÂï£¡");
						throw e;
					}
					newlore.set(Integer.parseInt(arg3[1]), arg3[2]);
				}
				else {
					sender.sendMessage("ÄãÄãÄãÄãÏë¸ÉÂï£¡");
					return false;
				}
				return true;
			}
			else {
				sender.sendMessage("ÄãÄãÄãÄãÏë¸ÉÂï£¡");
				return false;
			}
		}
	}
}
