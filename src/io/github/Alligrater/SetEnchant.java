package io.github.Alligrater;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetEnchant implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {

		if(!(sender instanceof Player) || !sender.hasPermission("silverutil.setenchant")) {
			sender.sendMessage("¡ì4No Permission");
			return false;
		}
		else {
			Player player = (Player)sender;
			if(player.getInventory().getItemInMainHand() != null && (arg3.length >= 1)) {
				ItemStack items = player.getInventory().getItemInMainHand();
				String be = arg3[0];
				if(be.equals("add") && arg3.length == 3) {
					try {
						int i = Integer.parseInt(arg3[2]);
					} catch(Exception e){
						sender.sendMessage("¡ì4Invalid number");
						return false;
					}
					Enchantment ench = Enchantment.getByName(arg3[1]);
					if(ench == null || Integer.parseInt(arg3[2]) < ench.getMaxLevel()) {
						sender.sendMessage("¡ì4Invalid Enchantment");
						return false;
					}
					else {
						items.addUnsafeEnchantment(ench, Integer.parseInt(arg3[2]));
						sender.sendMessage("¡ìbEnchanted!");
						return true;
					}
						
				}
				else if(be.equals("remove") && arg3.length == 2) {
					Enchantment ench = Enchantment.getByName(arg3[1]);
					if(ench == null) {
						sender.sendMessage("¡ì4Invalid Enchantment");
						return false;
					}
					else {
						items.removeEnchantment(ench);
						sender.sendMessage("¡ìbRemoved!");
						return true;
					}

				}
				
				else if(be.equals("list")) {
					
					sender.sendMessage("ARROW_DAMAGE,ARROW_FIRE,ARROW_INFINITE,ARROW_KNOCKBACK,BINDING_CURSE,DAMAGE_ALL,DAMAGE_ARTHROPODS,DAMAGE_UNDEAD,DEPTH_STRIDER,DIG_SPEED,DURABILITY,FIRE_ASPECT,FROST_WALKER,KNOCKBACK,LOOT_BONUS_BLOCKS,LOOT_BONUS_MOBS,LUCK,LURE,MENDING,OXYGEN,PROTECTION_ENVIRONMENTAL,PROTECTION_EXPLOSIONS,PROTECTION_FALL,PROTECTION_FIRE,PROTECTION_PROJECTILE,SILK_TOUCH,SWEEPING_EDGE,THORNS,VANISHING_CURSE,WATER_WORKER");
					return true;
				}
				
				else {
					sender.sendMessage("¡ì4Invalid Command");
					return false;
				}
			}
			else {
				sender.sendMessage("¡ì4No Item In Hand");
				return false;
			}
		}
	}
}
