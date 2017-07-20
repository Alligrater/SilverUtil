package io.github.Alligrater;

import org.bukkit.plugin.java.JavaPlugin;

public class SilverUtil extends JavaPlugin{
	
	@Override
	public void onEnable() {
		this.getCommand("suname").setExecutor(new SetName());
		this.getCommand("sulore").setExecutor(new SetLore());
		this.getCommand("suench").setExecutor(new SetEnchant());
		this.getCommand("bling").setExecutor(new PingCommand());
		this.getCommand("spam").setExecutor(new SpamCommand());
		getServer().getPluginManager().registerEvents(new MentionListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
}
