package io.github.Alligrater;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OPKeepInventory implements Listener{
	@EventHandler
	public void onOpDies(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if(player.isOp()) {
			event.setKeepInventory(true);
			event.setKeepInventory(true);
		}
	}
}
