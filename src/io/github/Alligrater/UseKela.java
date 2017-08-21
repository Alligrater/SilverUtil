package io.github.Alligrater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UseKela implements Listener {
	static HashMap<String, Integer> booksread = new HashMap<String, Integer>();
	@EventHandler
	public void onKelaUsed(BlockBreakEvent event) {
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
		item.getItemMeta();
		if(item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 10 && item.getType() == Material.GOLDEN_CARROT) {
			if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
				if(item.getAmount() <= 1) {
					event.getPlayer().getInventory().setItemInMainHand(null);
				}
				else {
					item.setAmount(item.getAmount() - 1);
				}
			}

			Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + "��6���˽������Ķ��һǧ�ˣ�");
		}
	}
	
	@EventHandler
	public void onKelaEat(PlayerItemConsumeEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand() != null) {
			ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
			item.getItemMeta();
			Player player = event.getPlayer();
			if(item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 10 && item.getType() == Material.GOLDEN_CARROT) {
				player.setFoodLevel(20);
				
				int type = (int) Math.ceil(Math.random()*4);
				switch(type) {
				case 0:
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 2), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 2), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 1), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 400, 1), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 2), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, 1), true);
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "��6����һ���ϺõĽ����,��e��������ȫ��������");
				case 1:
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 2), true);
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "��6����һ���ϺõĽ����,��c����&�������������");
					break;
				case 2:
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 150, 1), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 150, 1), true);
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "��6����һ���ϺõĽ����,��a�����ָ�&��ʳ�ȴ��������");
					break;
				case 3:
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1), true);
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "��6����һ���ϺõĽ����,��b��Ծ&���ٴ��������");
					break;
				case 4:
					for(Player nearby : Bukkit.getOnlinePlayers()) {
						if(nearby.getWorld().equals(player.getWorld())) {
							if (nearby.getLocation().distance(player.getLocation()) < 15) {
								nearby.setHealth(20);
								nearby.setFoodLevel(20);
							}
						}
					}
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "��6����һ���ϺõĽ����,��e��Χȫ���Ѫ��");
					break;
				}

			}
			
			else if(item.containsEnchantment(Enchantment.DAMAGE_ALL) && item.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 10 && item.getType() == Material.BREAD) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0), true);
				Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "��6ҧ��һ�ڷ���,��c������ʱ���������ˣ�");
			}
			else if(item.hasItemMeta()){
				if(item.getItemMeta().hasDisplayName()) {
					if(item.getItemMeta().getDisplayName().contains("����")) {
						Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " ��6�¶��س���һ�� " + item.getItemMeta().getDisplayName());
					}
				}

			}
		}
		
	} 
	
	@EventHandler
	public void onGouRead(PlayerInteractEvent event) {
		ItemStack item = event.getItem();
		if(item != null && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			if(item.getType().equals(Material.BOOK)) {
				if(!item.getEnchantments().isEmpty()) {
					if(item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 10) {
						if(item.getAmount() <= 1) {
							event.getPlayer().getInventory().setItemInMainHand(null);
						}
						else {
							item.setAmount(item.getAmount() - 1);
						}
						


						
						if(booksread.containsKey(event.getPlayer().getName())) {
							booksread.put(event.getPlayer().getName(), booksread.get(event.getPlayer().getName()) + 1);
							event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100 + (int)booksread.get(event.getPlayer().getName())*20, (int)booksread.get(event.getPlayer().getName())/8), true);
							event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100 + (int)booksread.get(event.getPlayer().getName())*20, (int)booksread.get(event.getPlayer().getName())/8), true);
							event.getPlayer().giveExp(5 + (int)booksread.get(event.getPlayer().getName()));
							event.getPlayer().sendMessage("��9������һЩ�����ľ��顭��+1s");


						}
						else {
							event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0), true);
							event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 0), true);
							booksread.put(event.getPlayer().getName(), 0);

							event.getPlayer().giveExp(5);
							event.getPlayer().sendMessage("��9������һЩ�����ľ��顭��+1s");
						}


						
					}
				}

			}
		}
	
	}
	
}
