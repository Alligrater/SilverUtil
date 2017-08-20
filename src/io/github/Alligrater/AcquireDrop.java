package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class AcquireDrop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(arg3.length == 1 && player.hasPermission("silverutil.getdrop")) {
				if(arg3[0].equals("SilverKela")) {
					ItemStack deathdrop = new ItemStack(Material.GOLDEN_CARROT);
					ItemMeta dmeta = deathdrop.getItemMeta();
					dmeta.setDisplayName("§e金 §a坷垃");
					List<String> lores = new ArrayList<String>();
					lores.add("Make Farming Great Again");
					dmeta.setLore(lores);
					dmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					deathdrop.setItemMeta(dmeta);
					deathdrop.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 10);
					player.getWorld().dropItem(player.getLocation(), deathdrop);
				}
				else if(arg3[0].equals("1733333")) {
					ItemStack deathdrop = new ItemStack(Material.BREAD);
					ItemMeta dmeta = deathdrop.getItemMeta();
					dmeta.setDisplayName("§6法棍");
					dmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					deathdrop.setItemMeta(dmeta);
					deathdrop.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
					player.getWorld().dropItem(player.getLocation(), deathdrop);
				}
				else if(arg3[0].equals("DeadlyMelody")) {
					ItemStack deathdrop = new ItemStack(Material.BOOK);
					ItemMeta dmeta = deathdrop.getItemMeta();
					dmeta.setDisplayName("§6《他改变了中国》");
					List<String> lores = new ArrayList<String>();
					lores.add("苟...？");
					dmeta.setLore(lores);
					dmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					deathdrop.setItemMeta(dmeta);
					deathdrop.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
					player.getWorld().dropItem(player.getLocation(), deathdrop);
				}
				else if(arg3[0].equals("LootBeacon")) {
					ItemStack items = new ItemStack(Material.FIREWORK);
					items.addUnsafeEnchantment(Enchantment.LUCK, 10);
					FireworkMeta fwm = (FireworkMeta) items.getItemMeta();
					fwm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					fwm.setDisplayName("§e奖励火箭");
					List<String> lores = new ArrayList<String>();
					lores.add("§7在平地上发射，就会降落补给箱！");
					lores.add("§7补给箱掉落概率：");
					lores.add("§7木质：95%");
					lores.add("§7铁质：4%");
					lores.add("§7金质：1%");
					fwm.setLore(lores);
					fwm.setPower(0);
					FireworkEffect effect = FireworkEffect.builder().flicker(false).trail(true).withColor(Color.RED, Color.MAROON).with(Type.BURST).build();
					fwm.addEffect(effect);
					
					items.setItemMeta(fwm);
					player.getWorld().dropItem(player.getLocation(), items);
				}
				
				else if(arg3[0].equals("MonthlyBeacon")) {
					ItemStack items = new ItemStack(Material.FIREWORK);
					items.addUnsafeEnchantment(Enchantment.LUCK, 20);
					FireworkMeta fwm = (FireworkMeta) items.getItemMeta();
					fwm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					fwm.setDisplayName("§e升级火箭");
					List<String> lores = new ArrayList<String>();
					lores.add("§7在平地上发射，就会降落补给箱！");
					lores.add("§7补给箱掉落概率：");
					lores.add("§7铁质：98%");
					lores.add("§7金质：2%");
					fwm.setLore(lores);
					fwm.setPower(0);
					FireworkEffect effect = FireworkEffect.builder().flicker(false).trail(true).withColor(Color.FUCHSIA,Color.PURPLE).with(Type.BURST).build();
					fwm.addEffect(effect);
					
					items.setItemMeta(fwm);
					player.getWorld().dropItem(player.getLocation(), items);
				}
				
				else {
					
				}
				return true;
			}
			else {
				return false;
			}

		}
		return false;
	}
	
}
