package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MentionListener implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChatMention(AsyncPlayerChatEvent event) {
		if(event.getMessage().contains("@")) {
			List<String> msgs = parse(event.getMessage());
			for(String msgpart :msgs) {
				if(msgpart.contains("@")) {
					if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(msgpart.substring(1)))) {
						Bukkit.getPlayer(msgpart.substring(1)).sendTitle(ChatColor.AQUA + event.getPlayer().getName(), ChatColor.AQUA + "Ã·µΩ¡Àƒ„");
						int index = msgs.indexOf(msgpart);
						msgpart = "°Ïb" + msgpart + "°Ïr";
						msgs.set(index, msgpart);
					}
					else {
						int index = msgs.indexOf(msgpart);
						msgpart = "°Ïb" + msgpart + "°Ïr";
						msgs.set(index, msgpart);
					}
				}
				
			}
			
			String finalmsg = "";
			for(String msgpart:msgs) {
				finalmsg = finalmsg + msgpart;
			}
			
			event.setMessage(finalmsg);
		}
	}
	
	public List<String> parse(String str) {
		List<String> list = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i<str.length() - 1; i++) {
			if(!str.substring(i, i+1).equals(" ") || !str.substring(i, i+1).equals("@") || i == str.length() - 2) {
				temp = temp + str.substring(i, i+1);
			}
			else {
				list.add(temp);
				temp = "";
				temp = temp + str.substring(i, i+1);
			}
		}
		
		return list;
	}
	
}
