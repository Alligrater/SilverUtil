package io.github.Alligrater;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class JoinColor implements Listener{
	static HashMap<String, String> customtag = new HashMap<String, String>();
	@EventHandler
	public void onPlayerlog(PlayerJoinEvent event) {
		
		Date current = new Date();
		Player player = event.getPlayer();
		Date last = new Date(player.getLastPlayed());
		
		if(last.getDate() != current.getDate()) {
			
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
			player.getInventory().addItem(items);
			player.sendMessage("§e你已领取一枚奖励火箭！");
		}
		
		if(last.getMonth() != current.getMonth()) {
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
			player.getInventory().addItem(items);
			player.sendMessage("§e你已领取一枚升级火箭！");
		}
		
		if(DisallowMining.violated.contains(event.getPlayer().getUniqueId())) {
			DisallowMining.violated.remove(event.getPlayer().getUniqueId());
		}
		
		boolean isonlist = false;
		for(RedBagPlayer rbp: RedBagSend.cplayer) {
			if(rbp.uuid.equals(event.getPlayer().getUniqueId())) {
				isonlist = true;
			}
		}
		
		if(!isonlist) {
			Date d = new Date();
			RedBagSend.cplayer.add(new RedBagPlayer(event.getPlayer().getUniqueId(), event.getPlayer().getLastPlayed()));
		}
		
		
		if(customtag.containsKey(player.getName())) {
			if(customtag.get(player.getName()).equals("§6[Monk]")) {
				
			}
			else {
				customtag.remove(player.getName());
			}
		}
		
		player.setDisplayName(updateDname(player));
		

		
		if(!RedBagSend.redbags.isEmpty()) {
			player.sendMessage("§e当前有未领完的红包，输入§c/redbag claim§e来领取吧！");
		}
		
		World world = player.getWorld();
			player.teleport(world.getSpawnLocation());
			player.sendMessage("§c为避免无法登陆，你已暂时被传送。");
	}
	
	@EventHandler
	public void onChatSend(PlayerChatEvent event) {
		event.getPlayer().setDisplayName(updateDname(event.getPlayer()));
	}
	
	public static String updateDname (Player player) {
		String title = "";
		
		String outstring = "";
		
		
		if(customtag.containsKey(player.getName())) {
			title = customtag.get(player.getName());
		}
		else if (player.isOp()){
			title = "§c[O]";

		}
		else if(DisallowMining.violated.contains(player.getUniqueId())){
			title = "§c[Vio]";
		}
		else if(player.hasPermission("silverutil.group.donor")) {
			title = "§b[D]";
		}
		else if(player.isBanned()) {
			title = "§8[B]";
		}
		else {
			title = "§2[P]";
		}
		
		outstring = title + player.getName();
		return outstring;
	}
	
	public static String getPrefix(Player player) {
		String title = "";
		if(customtag.containsKey(player.getName())) {
			title = customtag.get(player.getName());
		}
		else if (player.isOp()){
			title = "§c[O]";

		}
		else if(DisallowMining.violated.contains(player.getUniqueId())){
			title = "§c[Vio]";
		}
		else if(player.hasPermission("silverutil.group.donor")) {
			title = "§b[D]";
		}
		else if(player.isBanned()) {
			title = "§8[B]";
		}
		else {
			title = "§2[P]";
		}
		return title;
	}
	
	}
