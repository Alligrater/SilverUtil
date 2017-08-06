package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class DeathDrop implements Listener{
	@EventHandler
	public void onOwnerDied(PlayerDeathEvent event) {
		Player player = event.getEntity();
		World world = player.getWorld();
		Location loca = player.getLocation();
		if(player.getName().equals("SilverKela")) {
			
			ItemStack deathdrop = new ItemStack(Material.GOLDEN_CARROT);
			ItemMeta dmeta = deathdrop.getItemMeta();
			dmeta.setDisplayName("¡ìe½ð ¡ìa¿ÀÀ¬");
			List<String> lores = new ArrayList<String>();
			lores.add("Make Farming Great Again");
			dmeta.setLore(lores);
			deathdrop.setItemMeta(dmeta);
			deathdrop.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 10);
			world.dropItem(loca, deathdrop);
		}
		
	}
}
