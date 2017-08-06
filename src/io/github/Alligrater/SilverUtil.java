package io.github.Alligrater;

import org.bukkit.plugin.java.JavaPlugin;

public class SilverUtil extends JavaPlugin{
	
	@Override
	public void onEnable() {
		this.getCommand("suname").setExecutor(new SetName());
		this.getCommand("sulore").setExecutor(new SetLore());
		this.getCommand("spam").setExecutor(new SpamCommand());
		this.getCommand("bling").setExecutor(new PingCommand());
		this.getCommand("mute").setExecutor(new MuteChat());
		this.getCommand("gift").setExecutor(new CommandGift());
		this.getCommand("loca").setExecutor(new CommandLoca());
		this.getCommand("viewonline").setExecutor(new CommandOnline());
		this.getCommand("rtp").setExecutor(new RtpMe());
		//this.getCommand("tellme").setExecutor(new TellMe());
		getServer().getPluginManager().registerEvents(new ChatBling(), this);
		getServer().getPluginManager().registerEvents(new JoinColor(), this);
		getServer().getPluginManager().registerEvents(new AttackBlood(), this);
		getServer().getPluginManager().registerEvents(new LeapOfFaith(), this);
		getServer().getPluginManager().registerEvents(new DeathDrop(), this);
		getServer().getPluginManager().registerEvents(new DisallowMining(), this);
		getServer().getPluginManager().registerEvents(new OPKeepInventory(), this);
		getServer().getPluginManager().registerEvents(new IgnoreWhitelist(), this);
		getServer().getPluginManager().registerEvents(new UseKela(), this);
		getServer().getPluginManager().registerEvents(new PathRunning(), this);
		//getServer().getPluginManager().registerEvents(new DNDWeapon(), this);
	}
	
	
	@Override
	public void onDisable() {
		
	}

}
