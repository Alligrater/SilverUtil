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
		for(double x = -0.5; x <= 0.5; x+=0.5) {
			for(double z = -0.5; z <= 0.5; z+=0.5) {
				under.add(world.getBlockAt(loca.add(x, 0, z)).getType());
			}
		}
		if(under.contains(Material.GRASS_PATH)) {
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 0), true);
		}
	}
}
