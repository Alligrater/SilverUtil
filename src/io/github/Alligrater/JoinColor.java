package io.github.Alligrater;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinColor implements Listener{
	@EventHandler
	public void onChatPop(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(player.isOp()) {
			player.setDisplayName("¡ìc"+ player.getName() + "¡ìr");
		}
		else {
			player.setDisplayName("¡ì2"+ player.getName() + "¡ìr");
		}
	}
}
