package io.github.Alligrater;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class DisallowMining implements Listener{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		String world = player.getWorld().getName();
		
		if(block.getType() == Material.DIAMOND_ORE || block.getType() == Material.GOLD_ORE || block.getType() == Material.COAL_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.LAPIS_ORE || block.getType() == Material.EMERALD_ORE || block.getType() == Material.REDSTONE_ORE) {
			if(block.getBiome() != Biome.VOID && world.equals("world") && !player.hasPermission("silverutil.miningexempt")) {
				if(!player.getDisplayName().contains("§c[Vio]")) {
					event.setCancelled(true);
					player.damage(0);
					int locaX = player.getLocation().getBlockX();
					int locaY = player.getLocation().getBlockY();
					int locaZ = player.getLocation().getBlockZ();
					Bukkit.broadcastMessage("§c[警告]" + player.getName() + "§c正在尝试在主世界挖矿！坐标(" + locaX + "," + locaY + "," + locaZ + ")，类型：" + block.getType().name());
					player.sendMessage("§c在主世界挖矿是违反服规的行为。若对此判定有异议，请在公屏说明原因！");
					player.setDisplayName("§c[Vio]" + player.getDisplayName());
					block.getLocation();
					block.setType(Material.AIR);
				}
				else {
					event.setCancelled(true);
					block.setType(Material.AIR);
				}
			}
			else {
				//Prevents Biome Pollution, no matter it happens or not.
				World bworld = block.getWorld();
				block.setBiome(bworld.getBlockAt(block.getLocation().add(0, -1, 0)).getBiome());
			}
		}

	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		if(block.getType() == Material.DIAMOND_ORE || block.getType() == Material.GOLD_ORE || block.getType() == Material.COAL_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.LAPIS_ORE || block.getType() == Material.EMERALD_ORE || block.getType() == Material.REDSTONE_ORE) {
			block.setBiome((Biome.VOID));
			//Indicates the block is placed by player
		}
	}
}
