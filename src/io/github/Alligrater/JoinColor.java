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
			player.setDisplayName("��c"+ player.getName() + "��r");
		}
		else {
			player.setDisplayName("��2"+ player.getName() + "��r");
		}
	}
}
