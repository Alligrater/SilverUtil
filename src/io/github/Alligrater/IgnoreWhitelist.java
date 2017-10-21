package io.github.Alligrater;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

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
