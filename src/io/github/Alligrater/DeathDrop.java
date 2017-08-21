package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeathDrop implements Listener{
	@EventHandler
	public void onOwnerDied(PlayerDeathEvent event) {
		Player player = event.getEntity();
		World world = player.getWorld();
		Location loca = player.getLocation();
		if(world.getName().equals("world") || world.getName().equals("world_nether") || world.getName().equals("world_the_end") || world.getName().equals("mine")) {
			if(player.getName().equals("SilverKela")) {
				
				ItemStack deathdrop = new ItemStack(Material.GOLDEN_CARROT);
				ItemMeta dmeta = deathdrop.getItemMeta();
				dmeta.setDisplayName("��e�� ��a����");
				List<String> lores = new ArrayList<String>();
				lores.add("Make Farming Great Again");
				dmeta.setLore(lores);
				dmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				deathdrop.setItemMeta(dmeta);
				deathdrop.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 10);
				world.dropItem(loca, deathdrop);
			}
			else if(player.getName().equals("1733333")) {
				ItemStack deathdrop = new ItemStack(Material.BREAD);
				ItemMeta dmeta = deathdrop.getItemMeta();
				dmeta.setDisplayName("��6����");
				dmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				deathdrop.setItemMeta(dmeta);
				deathdrop.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
				world.dropItem(loca, deathdrop);
			}
			else if(player.getName().equals("DeadlyMelody")) {
				ItemStack deathdrop = new ItemStack(Material.BOOK);
				ItemMeta dmeta = deathdrop.getItemMeta();
				dmeta.setDisplayName("��6�����ı����й���");
				dmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				List<String> lores = new ArrayList<String>();
				lores.add("��...��");
				dmeta.setLore(lores);
				deathdrop.setItemMeta(dmeta);
				deathdrop.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
				deathdrop.setAmount(8);
				world.dropItem(loca, deathdrop);
			}
			else if(player.getName().equals("Efline")) {
				ItemStack deathdrop = new ItemStack(Material.RECORD_6);
				ItemMeta dmeta = deathdrop.getItemMeta();
				dmeta.setDisplayName("��9�ܻ���ָ");
				dmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				List<String> lores = new ArrayList<String>();
				lores.add("��7SHIELDING");
				lores.add("��7װ���ڸ���ʱ:");
				lores.add("��9 ����ʱ���鲢�����");
				lores.add("��9 ��������17��");
				dmeta.setLore(lores);
				deathdrop.setItemMeta(dmeta);
				world.dropItem(loca, deathdrop);
			}
		}
	}
}
