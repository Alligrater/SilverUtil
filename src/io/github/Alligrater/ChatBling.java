package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatBling implements Listener{
	@EventHandler
	public void onChatPop(AsyncPlayerChatEvent event) {
		Object[] online = Bukkit.getOnlinePlayers().toArray();
		for(Object p:online) {
			Player player = (Player)p;
			if (!MuteChat.muted.contains(player.getUniqueId())){
				player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, (float) 0.6, (float) 1.0);
			}
		}
	}
}
