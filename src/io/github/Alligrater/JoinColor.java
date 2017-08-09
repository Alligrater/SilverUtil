package io.github.Alligrater;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinColor implements Listener{
	@EventHandler
	public void onPlayerlog(PlayerJoinEvent event) {
		
		if(DisallowMining.violated.contains(event.getPlayer().getUniqueId())) {
			DisallowMining.violated.remove(event.getPlayer().getUniqueId());
		}
		
		Player player = event.getPlayer();
		player.setDisplayName(updateDname(player));
		
		
		
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
		
		
		if(VowofPoverty.monk.contains(player.getUniqueId())) {
			title = "§6[Monk]";
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
		else {
			title = "§2[P]";
		}
		
		outstring = title + player.getName();
		return outstring;
	}
	
	}
