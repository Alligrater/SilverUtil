package io.github.Alligrater;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinColor implements Listener{
	@EventHandler
	public void onChatPop(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(player.isOp()) {
			player.setDisplayName("§c"+ player.getName() + "§r");
		}
		else {
			player.setDisplayName("§2"+ player.getName() + "§r");
		}
		
		//Prevents Flying Softlock
		Location loca = player.getLocation();
		int locaY = loca.getBlockY();
		int locaX = loca.getBlockX();
		int locaZ = loca.getBlockZ();
		loca.setY(locaY);
		loca.setX(locaX);
		loca.setZ(locaZ);
		World world = player.getWorld();
		if(world.getBlockAt(loca).getType() == Material.VINE || world.getBlockAt(loca).getType() == Material.LADDER) {
			
			
			for(int i = 0; i < 255; i++) {
				loca = loca.add(0, -1, 0);
				
				if(world.getBlockAt(loca).getType() != Material.VINE && world.getBlockAt(loca).getType() != Material.LADDER && world.getBlockAt(loca).getType() != Material.AIR) {
					player.teleport(loca.add(0, 1, 0));
					player.sendMessage("§c为避免无法登陆，你已被传送。");
					break;
				}
				
			}
			}
		}
	}
