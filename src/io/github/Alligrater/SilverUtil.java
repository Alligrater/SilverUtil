package io.github.Alligrater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class SilverUtil extends JavaPlugin{
	HashMap<String, String> marriagemap = new HashMap<String, String>();
	static ArrayList<String> forbidden = new ArrayList<String>();
	@Override
	public void onEnable() {
		forbidden.add("create");
		forbidden.add("mg");
		forbidden.add("crypt");
		forbidden.add("pve");
		forbidden.add("crypt");
		for(Player player: Bukkit.getOnlinePlayers()) {
			player.setDisplayName(JoinColor.updateDname(player));
		}
		this.getCommand("suname").setExecutor(new SetName());
		this.getCommand("sulore").setExecutor(new SetLore());
		this.getCommand("spam").setExecutor(new SpamCommand());
		this.getCommand("bling").setExecutor(new PingCommand());
		this.getCommand("mutebling").setExecutor(new MuteChat());
		this.getCommand("gift").setExecutor(new CommandGift());
		this.getCommand("loca").setExecutor(new CommandLoca());
		this.getCommand("viewonline").setExecutor(new CommandOnline());
		this.getCommand("rtp").setExecutor(new RtpMe());
		this.getCommand("monk").setExecutor(new VowofPoverty());
		this.getCommand("impost").setExecutor(new Impost());
		this.getCommand("say").setExecutor(new Sayitout());
		this.getCommand("redbag").setExecutor(new RedBagSend());
		this.getCommand("getdrop").setExecutor(new AcquireDrop());
		this.getCommand("saylocal").setExecutor(new LocalTalk());
		getServer().getPluginManager().registerEvents(new ChatBling(), this);
		getServer().getPluginManager().registerEvents(new JoinColor(), this);
		getServer().getPluginManager().registerEvents(new AttackBlood(), this);
		getServer().getPluginManager().registerEvents(new LeapOfFaith(), this);
		getServer().getPluginManager().registerEvents(new DeathDrop(), this);
		getServer().getPluginManager().registerEvents(new OPKeepInventory(), this);
		getServer().getPluginManager().registerEvents(new IgnoreWhitelist(), this);
		getServer().getPluginManager().registerEvents(new UseKela(), this);
		getServer().getPluginManager().registerEvents(new PathRunning(), this);
		getServer().getPluginManager().registerEvents(new DieofVow(), this);
		getServer().getPluginManager().registerEvents(new MentionListener(), this);
		
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	if(RedBagSend.getTotal() > 0) {
                    Bukkit.broadcastMessage("§e现在还有" + RedBagSend.getTotal() +"个红包，输入§c/redbag claim§e来领取吧！");
            	}
            }
        }, 0L, 6000L);
	}
	
	
	@Override
	public void onDisable() {
		HashMap<String, String> storemarriage = new HashMap<String, String>();
		storemarriage = marriagemap;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg3) {

		if (cmd.getName().equalsIgnoreCase("marry") && arg3.length == 1) {
			if(sender instanceof Player) {
				Player player = (Player)sender;
				String tname = arg3[0];
				Player target = Bukkit.getPlayer(tname);
				String pname = player.getName();
				String previous = null;
				ItemStack items = player.getInventory().getItemInMainHand().clone();
				MaterialData md = items.getData();
				short typeid = items.getDurability();
				if(items.getType().equals(Material.RED_ROSE) || items.getType().equals(Material.YELLOW_FLOWER) || (items.getType().equals(Material.DOUBLE_PLANT)) && typeid != 2 && typeid != 3) {
					int rose = items.getAmount();

					String flowertype = "花";
					if(items.getType().equals(Material.RED_ROSE)) {
						if(typeid == 0) {
							flowertype = "罂粟";
						}
						else if(typeid == 1) {
							flowertype = "兰花";
						}
						else if(typeid == 2) {
							flowertype = "绒球葱";
						}
						else if(typeid == 3) {
							flowertype = "茜草花";
						}
						else if(typeid >= 4 && typeid < 8){
							flowertype = "郁金香";
						}
						else {
							flowertype = "浜菊";
						}

					}
					else if(items.getType().equals(Material.DOUBLE_PLANT)){
						if(typeid == 0) {
							flowertype = "向日葵";
						}
						else if(typeid == 1) {
							flowertype = "丁香";
						}
						else if(typeid == 4) {
							flowertype = "玫瑰";
						}
						else {
							flowertype = "牡丹";
						}
						
					}
					else {
						flowertype = "蒲公英";
					}
					if(target != null) {
						if(marriagemap.containsKey(pname)) {
							previous = marriagemap.get(pname);
							if(!previous.equals(target.getName())) {
								String thebetrayed = previous;
								if(thebetrayed.equalsIgnoreCase("server")) {
									thebetrayed = "服务器娘";
								}
								else if(thebetrayed.equalsIgnoreCase("[self]")) {
									thebetrayed = "自己";
								}

									Bukkit.broadcastMessage(ChatColor.GREEN + Bukkit.getPlayer(pname).getName() + "把" + thebetrayed + "绿了！");
									if(Bukkit.getPlayer(previous) != null) {
										JoinColor.customtag.put(previous, "§a[被绿]");
										player.setDisplayName(JoinColor.updateDname(player));
									}
								}
								
						}
						
					//自我求婚
						if(player.getUniqueId().equals(target.getUniqueId())) {
							String ft = flowertype;
							player.getInventory().setItemInMainHand(null);
					        BukkitScheduler scheduler = getServer().getScheduler();
					        player.sendMessage("§4你你你不能向自己求婚！");
					        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
					            @Override
					            public void run() {
									player.sendMessage("§c你不能向自己求婚...?");
					            }
					        }, 40L);
					        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
					            @Override
					            public void run() {
									player.sendMessage("§6算了，我想了想...就给你一次机会吧！");
					            }
					        }, 80L);
					        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
					            @Override
					            public void run() {
									Bukkit.broadcastMessage("§6" + player.getName() + "§e居然用" + rose + "朵" + ft + "向自己求婚了！");
									player.playSound(player.getLocation(), Sound.ENTITY_GHAST_AMBIENT, (float)1.0,  (float)1.0);
									player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 30, 0.8,0.8,0.8,1.0);
									marriagemap.put(player.getName(), "[self]");
									JoinColor.customtag.put(player.getName(), "§d[自恋]");
									player.setDisplayName(JoinColor.updateDname(player));
									player.getInventory().addItem(items);
									setSingle(player.getName(),player.getName());
					            }
					        }, 120L);
					 //作为玩家向他人求婚
						}
						else {
							player.getInventory().setItemInMainHand(null);
								marriagemap.put(pname, target.getName());
								if(findReverse(pname)) {
									Bukkit.broadcastMessage("§c【囍】§6" + Bukkit.getPlayer(pname).getName() + "§e与§6" + Bukkit.getPlayer(marriagemap.get(pname)).getName() + "§e结婚了！");
									setSingle(target.getName(), player.getName());
									JoinColor.customtag.put(pname, "§d[秀恩爱]");
									JoinColor.customtag.put(target.getName(), "§d[秀恩爱]");
									player.setDisplayName(JoinColor.updateDname(player));
									target.setDisplayName(JoinColor.updateDname(target));
								}
								else {
									Bukkit.broadcastMessage(ChatColor.GOLD + Bukkit.getPlayer(pname).getName() + "§e用" + rose + "朵" + flowertype + "向§6" + Bukkit.getPlayer(marriagemap.get(pname)).getName() + "§e求婚了！");
									JoinColor.customtag.put(pname, "§5[单相思]");
									player.setDisplayName(JoinColor.updateDname(player));
									target.getInventory().addItem(items);
									target.sendMessage("§e你收到了来自§6" + player.getName() + "§e的求婚！");
									target.sendMessage("§e输入§6/marry " + player.getName() + "§e来答应他的求婚，或者什么也不做来无视ta的求婚吧！");
								}
						}
					}
					//向服务器娘求婚
					else if(tname.equalsIgnoreCase("server")) {
						player.getInventory().setItemInMainHand(null);
						if(marriagemap.containsKey(pname)) {
							previous = marriagemap.get(pname);
							if(!Bukkit.getOfflinePlayer(previous).getName().equals("server")) {
								String thebetrayed = Bukkit.getOfflinePlayer(previous).getName();
								if(thebetrayed.equalsIgnoreCase("server")) {
									thebetrayed = "服务器娘";
								}
								else if(thebetrayed.equalsIgnoreCase("[self]")) {
									thebetrayed = "自己";
								}
									Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + "把" + thebetrayed + "绿了！");
									if(Bukkit.getPlayer(previous) != null) {
										JoinColor.customtag.put(previous, "§a[被绿]");
										Bukkit.getPlayer(previous).setDisplayName(JoinColor.updateDname(Bukkit.getPlayer(previous)));
									}
								
							}
						}
						

							marriagemap.put(pname, "server");
							if(findReverse(pname)) {
								Bukkit.broadcastMessage("§c【囍】§6" + player.getName() + "§e与§6服务器娘§e结婚了！");
								setSingle("server", pname);
								JoinColor.customtag.put(pname, "§d[秀恩爱]");
								player.setDisplayName(JoinColor.updateDname(player));

								
							}
							else {
								Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "§e用" + rose + "朵" + flowertype + "向§6服务器娘§e求婚了！");
								JoinColor.customtag.put(player.getName(), "§5[单相思]");
								player.setDisplayName(JoinColor.updateDname(player));
							}
					}
					else {
						player.sendMessage("§c你求婚的对象不在线！");
					}
				}
				else {
					player.sendMessage("§e你的手里没有§c花§e！");
				}
			}
				
			
			//服务器娘求婚
			else {
				
				String tname = arg3[0];
				Player target = Bukkit.getPlayer(tname);
				String previous;


				if(target != null) {
					
					
					if(marriagemap.containsKey("server")) {
						previous = marriagemap.get("server");
						if(!previous.equals(target.getName())) {
							String thebetrayed = Bukkit.getOfflinePlayer(previous).getName();
							if(thebetrayed.equalsIgnoreCase("server")) {
								thebetrayed = "服务器娘";
							}
							else if(thebetrayed.equalsIgnoreCase(previous + "[self]")) {
								thebetrayed = "自己";
							}
								Bukkit.broadcastMessage(ChatColor.GREEN + "服务器娘把" + thebetrayed + "绿了！");
								if(Bukkit.getPlayer(previous) != null) {
									JoinColor.customtag.put(previous, "§a[被绿]");
									Bukkit.getPlayer(previous).setDisplayName(JoinColor.updateDname(Bukkit.getPlayer(previous)));
								}
							
						}
					}
					
					
					marriagemap.put("server", target.getName());
					if(findReverse("server")) {
						Bukkit.broadcastMessage("§c【囍】§6服务器娘§e与§6" + target.getName() + "§e结婚了！");
						setSingle("server", target.getName());
						JoinColor.customtag.put(target.getName(), "§d[秀恩爱]");
						target.setDisplayName(JoinColor.updateDname(target));

					}
					else {
						Bukkit.broadcastMessage("§6服务器娘§e向§6" + target.getName() + "§e求婚了！");
						target.sendMessage("§e你收到了来自服务器娘§e的求婚！");
						target.sendMessage("§e输入§6/marry server§e来答应他的求婚，或者什么也不输来无视ta的求婚吧！");
					}
				}
				else {
					sender.sendMessage("§c你求婚的对象不在线！");
				}

			}
			// TODO Auto-generated method stub
			return true;
		}
		return false;
		
	}
	
	public boolean findReverse(String pname) {
		String tname = marriagemap.get(pname);
		if(marriagemap.get(tname) != null) {
			if(marriagemap.get(tname).equals(pname)) {
				return true;
			}
			else {
				return false;
			}
		}

		else {
			return false;
		}
		
	}
	
	public void setSingle(String pname, String tname) {
		Set<String> keys = marriagemap.keySet();
		for(String key:keys) {
			if(marriagemap.get(key).equals(pname) || marriagemap.get(key).equals(tname)) {
				if(Bukkit.getPlayer(key) != null && !key.equals(tname) && !key.equals(pname)) {
					JoinColor.customtag.put(key, "§9[吃狗粮]");
					Bukkit.getPlayer(key).setDisplayName(JoinColor.updateDname(Bukkit.getPlayer(key)));
					Bukkit.getPlayer(key).playSound(Bukkit.getPlayer(key).getLocation(), Sound.ENTITY_WOLF_AMBIENT, (float)1.0, (float)1.0);
				}
			}
		}
	}
}
