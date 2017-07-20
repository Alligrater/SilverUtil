package io.github.Alligrater;

import org.bukkit.plugin.java.JavaPlugin;

public class SilverUtil extends JavaPlugin{
	
	@Override
	public void onEnable() {
		this.getCommand("suname").setExecutor(new SetName());
		this.getCommand("sulore").setExecutor(new SetLore());
		this.getCommand("suench").setExecutor(new SetEnchant());
		getServer().getPluginManager().registerEvents(new MentionListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
}
