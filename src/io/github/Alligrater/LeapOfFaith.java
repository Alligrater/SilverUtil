package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import net.md_5.bungee.api.ChatColor;

public class LeapOfFaith implements Listener{
	@EventHandler
	public void onPlayerFall(EntityDamageEvent event) {
		if(event.getEntityType() == EntityType.PLAYER) {
			if(event.getCause() == DamageCause.FALL) {
				Location loca = event.getEntity().getLocation();
				loca = loca.add(0, -0.5, 0);
				
				List<Material> under = new ArrayList<Material>();
				for(double x = -0.5; x <= 0.5; x+=0.5) {
					for(double z = -0.5; z <= 0.5; z+=0.5) {
						under.add((event.getEntity().getWorld().getBlockAt(loca.add(x, 0, z))).getType());
					}
				}

				if(under.contains(Material.HAY_BLOCK)) {
					if(event.getEntity().getFallDistance() > 12 && event.getFinalDamage() * 0.1 < ((Player)event.getEntity()).getHealth()) {
						Bukkit.broadcastMessage(ChatColor.YELLOW + event.getEntity().getName() + "从" + Math.round(event.getEntity().getFallDistance()) +"格的高空中一跃而下，落到了草垛里活了下来！");
					}
					event.setDamage(event.getFinalDamage() * 0.1);
				}
			}
		}
	}
	
	@EventHandler
	public void playerFallDamage(EntityDamageByEntityEvent event) {
		if(event.getDamager().getFallDistance() >= 8 && event.getDamager() instanceof Player) {
			double damage = event.getDamage() + event.getDamager().getFallDistance() * 1.6;
			event.setDamage(damage);

			if(event.getEntityType().equals(EntityType.PLAYER)) {
				Player target = ((Player)event.getEntity());

				if (event.getFinalDamage() > target.getHealth()) {
					event.getDamager().setFallDistance(0);
					target.setDisplayName("[ASS]" + target.getDisplayName()); 
				}
				else {
					event.getDamager().setFallDistance((float) (event.getDamager().getFallDistance()*0.5));
				}
			}
		}
		
		if(event.getDamager().getType().equals(EntityType.PLAYER) && event.getEntityType().equals(EntityType.PLAYER)) {
			Player target = (Player) event.getEntity();
			Player ass = (Player) event.getDamager();
			float tyaw = target.getEyeLocation().getYaw() + 360;
			
			
			tyaw =  tyaw%360;
			tyaw = (tyaw + 360) % 360;
			if(tyaw >180)
				tyaw -=360;
			
			

			float ayaw = ass.getEyeLocation().getYaw() + 360;
			ayaw =  ayaw%360;
			ayaw = (ayaw + 360) % 360;
			if(ayaw >180)
				ayaw -=360;
			if(ayaw > tyaw - 120 && ayaw < tyaw + 120 && ass.isSneaking()) {
				event.setDamage(event.getDamage() * 2);
				if(event.getFinalDamage() > target.getHealth()) {
					target.setDisplayName("[BSTAB]" + target.getDisplayName()); 
				}
			}
			

		}
		
	
		
		
	}
	
	@EventHandler
	public void playerPin(PlayerDeathEvent event) {
		if(event.getEntity().getDisplayName().contains("[BSTAB]") && event.getEntity().getDisplayName().contains("[ASS]")) {
			String killer = event.getEntity().getKiller().getName();
			event.setDeathMessage(ChatColor.RED + killer + "高空背刺了" + event.getEntity().getName() + "!");
			event.getEntity().setDisplayName(event.getEntity().getDisplayName().replaceAll("\\[ASS\\]", ""));
			event.getEntity().setDisplayName(event.getEntity().getDisplayName().replaceAll("\\[BSTAB\\]", ""));
		}
		if(event.getEntity().getDisplayName().contains("[ASS]")) {
			String killer = event.getEntity().getKiller().getName();
			event.setDeathMessage(ChatColor.RED + killer + "高空刺杀了" + event.getEntity().getName() + "!");
			event.getEntity().setDisplayName(event.getEntity().getDisplayName().replaceAll("\\[ASS\\]", ""));
		}
		if(event.getEntity().getDisplayName().contains("[BSTAB]")) {
			String killer = event.getEntity().getKiller().getName();
			event.setDeathMessage(ChatColor.RED + killer + "背后刺杀了" + event.getEntity().getName() + "!");
			event.getEntity().setDisplayName(event.getEntity().getDisplayName().replaceAll("\\[BSTAB\\]", ""));
		}
		


	}
	
	/*
	public Entity getFOV(Player player) {
		Vector v = player.getEyeLocation().getDirection();
		double vx = v.getX();
		double vz = v.getZ();
		v.
		
		
		return player;
		
	}
	*/
}
