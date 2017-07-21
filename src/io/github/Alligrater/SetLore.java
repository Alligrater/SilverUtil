package io.github.Alligrater;

import java.util.ArrayList;
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
			sender.sendMessage("¡ì4No Permission");
			return true;
		}
		else {
			Player player = (Player) sender;
			if(player.getInventory().getItemInMainHand() != null) {
				ItemStack items = player.getInventory().getItemInMainHand();
				ItemMeta imeta = items.getItemMeta();
				String be = arg3[0];
				List<String> oldlore = new ArrayList<String>();
				if (imeta.hasLore()) {
					oldlore = imeta.getLore();
				}
				else {
					
				}
				
				
				if(be.equals("add")) {
					String lore = concatArray(arg3,1);
					lore = lore.replaceAll("&", "¡ì");
					lore = lore.replaceAll("¡ì¡ì", "&");
					oldlore.add(lore);
					sender.sendMessage("¡ìbLore Added!");
				}
				
				else if(be.equals("remove") && arg3.length == 2) {
					try {
						int i = Integer.parseInt(arg3[1]);
						oldlore.get(i);
					} catch (Exception e) {
						sender.sendMessage("¡ì4Invalid Number");
						return false;
					}
					oldlore.remove(Integer.parseInt(arg3[1]));
					sender.sendMessage("¡ìbLore Removed!");
				}
				
				else if (be.equals("set")) {
					try {
						int i = Integer.parseInt(arg3[1]);
						oldlore.get(i);
					} catch (Exception e) {
						sender.sendMessage("¡ì4Invalid Number");
						return false;
					}
					String lore = concatArray(arg3,2);
					lore = lore.replaceAll("&", "¡ì");
					lore = lore.replaceAll("¡ì¡ì", "&");
					oldlore.set(Integer.parseInt(arg3[1]), lore);
					sender.sendMessage("¡ìbLore Replaced!");
				}
				
				else {
					sender.sendMessage("¡ì4Invalid Command");
					return false;
				}
				
				
				imeta.setLore(oldlore);
				
				items.setItemMeta(imeta);
				
				return true;
				
			}
			else {
				sender.sendMessage("¡ì4No Item In Hand");
				return true;
			}
		}
	}
	
	public String concatArray(String[] arg3, int start) {
		String out = "";
		for (int i = start; i < arg3.length; i++) {
			out = out+arg3[i] + " ";
		}
		
		return out;
	}
}
