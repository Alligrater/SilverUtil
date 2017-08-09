package io.github.Alligrater;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class IgnoreWhitelist implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerLoginEvent event) {
		Set<OfflinePlayer> whitelisted = Bukkit.getWhitelistedPlayers();
		String joiner = event.getPlayer().getName().toLowerCase();
		for(OfflinePlayer wlplayer : whitelisted) {
			if(wlplayer.getName().toLowerCase().equals(joiner) && event.getResult().equals(PlayerLoginEvent.Result.KICK_WHITELIST)) {
				event.setResult(Result.ALLOWED);
			}
			else {
				
			}
		}
	}
}
