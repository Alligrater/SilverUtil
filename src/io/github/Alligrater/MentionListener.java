package io.github.Alligrater;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class MentionListener implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChatMention(PlayerChatEvent event) {
		if(!event.getPlayer().getDisplayName().equals(JoinColor.updateDname(event.getPlayer()))) {
			event.getPlayer().setDisplayName(JoinColor.updateDname(event.getPlayer()));
		}
		
		if(event.getMessage().contains("@")) {
			List<String> msgs = parse(event.getMessage());
			
			
			for(String msgpart :msgs) {
				if(msgpart.contains("@")) {
					if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(msgpart.substring(1)))) {
						Player online = Bukkit.getPlayer(msgpart.substring(1));
						online.sendTitle(ChatColor.AQUA + event.getPlayer().getName(), ChatColor.AQUA + "提到了你");
						online.playSound(online.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , (float) 1, 1);
						int index = msgs.indexOf(msgpart);
						msgpart = "§b" + msgpart + "§r";
						msgs.set(index, msgpart);
					}
					else if (msgpart.substring(1).equals("all") && event.getPlayer().hasPermission("chatvol.bling.all")){
						for(Player ponline:Bukkit.getOnlinePlayers()) {
							ponline.sendTitle(ChatColor.AQUA + event.getPlayer().getName(), ChatColor.AQUA + "提到了你");
							ponline.playSound(ponline.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , (float) 1, 1);
							int index = msgs.indexOf(msgpart);
							msgpart = "§b" + msgpart + "§r";
							msgs.set(index, msgpart);
						}
					}
					else {
						int index = msgs.indexOf(msgpart);
						msgpart = "§7" + msgpart + "§r";
						msgs.set(index, msgpart);
					}
				}
				
			}
			
			
			
			String finalmsg = "";
			for(String msgpart:msgs) {
				finalmsg = finalmsg + msgpart;
			}
			
			Player player = event.getPlayer();
			JoinColor.updateDname(event.getPlayer());
			event.setFormat("%s:§r %s");
			
		}
		
		else {
			Player player = event.getPlayer();
			JoinColor.updateDname(event.getPlayer());
			event.setFormat("%s:§r %s");
		}
	}
	
	public List<String> parse(String str) {
		List<String> list = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i<str.length() - 1; i++) {
			if((str.substring(i, i+1).equals(" ") || str.substring(i, i+1).equals("@")) && i != str.length() - 2) {
				list.add(temp);
				temp = "";
				temp = temp + str.substring(i, i+1);

			}
			else if(i == str.length() - 2) {
				 temp = temp + str.substring(i);
				 list.add(temp);
				 break;
			}
			else {
				temp = temp + str.substring(i, i+1);
			}
		}
		
		return list;
	}
	
	
}