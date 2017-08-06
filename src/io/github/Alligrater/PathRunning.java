package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PathRunning implements Listener{
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Location loca = event.getPlayer().getLocation().add(0, -0.2, 0);
		World world = event.getPlayer().getWorld();
		List<Material> under = new ArrayList<Material>();
		for(int x = -1; x <= 1; x++) {
			for(int z = -1; z <= 1; z++) {
				under.add(world.getBlockAt(loca.add(x, 0, z)).getType());
			}
		}
		if(under.contains(Material.GRASS_PATH)) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0), true);
		}
	}
}
