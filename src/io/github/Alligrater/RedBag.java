package io.github.Alligrater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RedBag {
	String playername;
	ItemStack items;
	int claimamount;
	public RedBag(String playername, ItemStack items, int claimamount) {
		this.playername = playername;
		this.items = items;
		this.claimamount = claimamount;
	}
	
	public ItemStack claim() {
		ItemStack newstack = this.items.clone();
		newstack.setAmount(claimamount);
		ItemMeta newmeta = this.items.getItemMeta();
		List<String> lore = new ArrayList<String>();
		if(newmeta.hasLore()) {
			lore = newmeta.getLore();
		}
		else {
			
		}
		lore.add("Redbag sent by " + this.playername);
		newmeta.setLore(lore);
		newstack.setItemMeta(newmeta);
		this.items.setAmount(this.items.getAmount() - this.claimamount);
		//Bukkit.getLogger().info("[Redbag] Amount = " + newstack.getAmount());
		return newstack;
	}
	
	public int remain() {
		return this.items.getAmount()/this.claimamount;
	}
}
