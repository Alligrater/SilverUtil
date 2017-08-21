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

			Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + "§6用了金坷垃，亩产一千八！");
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
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§e所有属性全部提升！");
				case 1:
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 2), true);
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§c攻击&防御大幅提升！");
					break;
				case 2:
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 150, 1), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 150, 1), true);
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§a生命恢复&饱食度大幅提升！");
					break;
				case 3:
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2), true);
					player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1), true);
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§b跳跃&移速大幅提升！");
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
					Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§e周围全体回血！");
					break;
				}

			}
			
			else if(item.containsEnchantment(Enchantment.DAMAGE_ALL) && item.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 10 && item.getType() == Material.BREAD) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0), true);
				Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6咬了一口法棍,§c力量短时间内提升了！");
			}
			else if(item.hasItemMeta()){
				if(item.getItemMeta().hasDisplayName()) {
					if(item.getItemMeta().getDisplayName().contains("狗粮")) {
						Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " §6孤独地吃了一口 " + item.getItemMeta().getDisplayName());
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
							event.getPlayer().sendMessage("§9你获得了一些人生的经验……+1s");


						}
						else {
							event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0), true);
							event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 0), true);
							booksread.put(event.getPlayer().getName(), 0);

							event.getPlayer().giveExp(5);
							event.getPlayer().sendMessage("§9你获得了一些人生的经验……+1s");
						}


						
					}
				}

			}
		}
	
	}
	
}
