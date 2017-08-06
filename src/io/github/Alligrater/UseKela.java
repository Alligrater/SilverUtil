package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UseKela implements Listener {
	@EventHandler
	public void onKelaUsed(BlockBreakEvent event) {
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
		ItemMeta imeta = item.getItemMeta();
		if(item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 10 && item.getType() == Material.GOLDEN_CARROT) {
			if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
				if(item.getAmount() <= 1) {
					event.getPlayer().getInventory().remove(item);
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
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
		ItemMeta imeta = item.getItemMeta();
		Player player = event.getPlayer();
		if(item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 10 && item.getType() == Material.GOLDEN_CARROT) {
			player.setFoodLevel(20);
			
			int type = (int) Math.ceil(Math.random()*4);
			switch(type) {
			case 0:
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 400, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, 1));
				Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§e所有属性全部提升！");
			case 1:
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 2));
				Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§c攻击&防御大幅提升！");
				break;
			case 2:
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 150, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 150, 1));
				Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§a生命恢复&饱食度大幅提升！");
				break;
			case 3:
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1));
				Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§b跳跃&移速大幅提升！");
				break;
			case 4:
				for(Player nearby : Bukkit.getOnlinePlayers()) {
					if (nearby.getLocation().distance(player.getLocation()) < 15) {
						nearby.setHealth(20);
						nearby.setFoodLevel(20);
					}

				}
				Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§6吸了一口上好的金坷垃,§e周围全体回血！");
				break;
			}
				
			





		}
	}
}
