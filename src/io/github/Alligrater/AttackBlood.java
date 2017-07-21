package io.github.Alligrater;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.material.MaterialData;


public class AttackBlood implements Listener{
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent event) {
		Entity target = event.getEntity();
		World world = target.getWorld();
		Location loca = target.getLocation();
		int damage = (int) Math.round(event.getDamage());
		int count = Math.round(damage*10/2);
		loca = loca.add(0, 1, 0);
		//world.playEffect(target.getLocation(), Effect.STEP_SOUND,152);
		if(target instanceof Player && target.getName().equals("SilverKela")) {
			world.spawnParticle(Particle.BLOCK_CRACK, loca, count, 0.2,0.2,0.2,0.1);
		}
		else {
			world.spawnParticle(Particle.BLOCK_CRACK, loca, count, 0.2,0.2,0.2,0.1,new MaterialData(Material.REDSTONE_BLOCK));
		}


	}
}
