package io.github.Alligrater;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class DieofVow implements Listener{
	public static ArrayList<UUID> deadmonk = new ArrayList<UUID>();
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		ItemStack items = player.getInventory().getItemInOffHand();
		if (deadmonk.contains(event.getEntity().getUniqueId())) {
			event.setDeathMessage(ChatColor.GOLD + player.getName() + ChatColor.GOLD + " 因贫穷的誓言自杀了！");
			VowofPoverty.monk.remove(event.getEntity().getUniqueId());
			deadmonk.remove(event.getEntity().getUniqueId());
			Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "不再是Monk了！");
			if (JoinColor.customtag.get(player.getName()) != null && JoinColor.customtag.get(player.getName()).equals("§6[Monk]")) {
				JoinColor.customtag.remove(player.getName());
			}
			player.setDisplayName(JoinColor.updateDname(player));
		}
		else {
			if(UseKela.booksread.containsKey(event.getEntity().getName())) {
				UseKela.booksread.remove(event.getEntity().getName());
				event.getEntity().sendMessage("你啊，还是要学习一个……");
			}
		}
	}
	
	@EventHandler
	public void onPickupVow(PlayerPickupItemEvent event) {
		Material itemtype = event.getItem().getItemStack().getType();
		if((itemtype == Material.GOLD_INGOT || itemtype == Material.GOLD_NUGGET || itemtype == Material.GOLD_BLOCK) && VowofPoverty.monk.contains(event.getPlayer().getUniqueId()) && event.getPlayer().getGameMode() != GameMode.CREATIVE) {

			deadmonk.add(event.getPlayer().getUniqueId());
			event.getPlayer().damage(999);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_PLACE, (float) 1, (float)0.5);
		}
	}
}
