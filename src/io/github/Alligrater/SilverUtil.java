package io.github.Alligrater;

import org.bukkit.plugin.java.JavaPlugin;

public class SilverUtil extends JavaPlugin{
	
	@Override
	public void onEnable() {
		this.getCommand("setname").setExecutor(new SetName());
	}
	
	@Override
	public void onDisable() {
		
	}
}
