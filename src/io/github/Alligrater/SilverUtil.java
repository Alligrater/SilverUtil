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
                    Bukkit.broadcastMessage("��e���ڻ���" + RedBagSend.getTotal() +"������������c/redbag claim��e����ȡ�ɣ�");
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

					String flowertype = "��";
					if(items.getType().equals(Material.RED_ROSE)) {
						if(typeid == 0) {
							flowertype = "���";
						}
						else if(typeid == 1) {
							flowertype = "����";
						}
						else if(typeid == 2) {
							flowertype = "�����";
						}
						else if(typeid == 3) {
							flowertype = "��ݻ�";
						}
						else if(typeid >= 4 && typeid < 8){
							flowertype = "������";
						}
						else {
							flowertype = "亾�";
						}

					}
					else if(items.getType().equals(Material.DOUBLE_PLANT)){
						if(typeid == 0) {
							flowertype = "���տ�";
						}
						else if(typeid == 1) {
							flowertype = "����";
						}
						else if(typeid == 4) {
							flowertype = "õ��";
						}
						else {
							flowertype = "ĵ��";
						}
						
					}
					else {
						flowertype = "�ѹ�Ӣ";
					}
					if(target != null) {
						if(marriagemap.containsKey(pname)) {
							previous = marriagemap.get(pname);
							if(!previous.equals(target.getName())) {
								String thebetrayed = previous;
								if(thebetrayed.equalsIgnoreCase("server")) {
									thebetrayed = "��������";
								}
								else if(thebetrayed.equalsIgnoreCase("[self]")) {
									thebetrayed = "�Լ�";
								}

									Bukkit.broadcastMessage(ChatColor.GREEN + Bukkit.getPlayer(pname).getName() + "��" + thebetrayed + "���ˣ�");
									if(Bukkit.getPlayer(previous) != null) {
										JoinColor.customtag.put(previous, "��a[����]");
										player.setDisplayName(JoinColor.updateDname(player));
									}
								}
								
						}
						
					//�������
						if(player.getUniqueId().equals(target.getUniqueId())) {
							String ft = flowertype;
							player.getInventory().setItemInMainHand(null);
					        BukkitScheduler scheduler = getServer().getScheduler();
					        player.sendMessage("��4�����㲻�����Լ���飡");
					        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
					            @Override
					            public void run() {
									player.sendMessage("��c�㲻�����Լ����...?");
					            }
					        }, 40L);
					        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
					            @Override
					            public void run() {
									player.sendMessage("��6���ˣ���������...�͸���һ�λ���ɣ�");
					            }
					        }, 80L);
					        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
					            @Override
					            public void run() {
									Bukkit.broadcastMessage("��6" + player.getName() + "��e��Ȼ��" + rose + "��" + ft + "���Լ�����ˣ�");
									player.playSound(player.getLocation(), Sound.ENTITY_GHAST_AMBIENT, (float)1.0,  (float)1.0);
									player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 30, 0.8,0.8,0.8,1.0);
									marriagemap.put(player.getName(), "[self]");
									JoinColor.customtag.put(player.getName(), "��d[����]");
									player.setDisplayName(JoinColor.updateDname(player));
									player.getInventory().addItem(items);
									setSingle(player.getName(),player.getName());
					            }
					        }, 120L);
					 //��Ϊ������������
						}
						else {
							player.getInventory().setItemInMainHand(null);
								marriagemap.put(pname, target.getName());
								if(findReverse(pname)) {
									Bukkit.broadcastMessage("��c���֡���6" + Bukkit.getPlayer(pname).getName() + "��e���6" + Bukkit.getPlayer(marriagemap.get(pname)).getName() + "��e����ˣ�");
									setSingle(target.getName(), player.getName());
									JoinColor.customtag.put(pname, "��d[�����]");
									JoinColor.customtag.put(target.getName(), "��d[�����]");
									player.setDisplayName(JoinColor.updateDname(player));
									target.setDisplayName(JoinColor.updateDname(target));
								}
								else {
									Bukkit.broadcastMessage(ChatColor.GOLD + Bukkit.getPlayer(pname).getName() + "��e��" + rose + "��" + flowertype + "���6" + Bukkit.getPlayer(marriagemap.get(pname)).getName() + "��e����ˣ�");
									JoinColor.customtag.put(pname, "��5[����˼]");
									player.setDisplayName(JoinColor.updateDname(player));
									target.getInventory().addItem(items);
									target.sendMessage("��e���յ������ԡ�6" + player.getName() + "��e����飡");
									target.sendMessage("��e�����6/marry " + player.getName() + "��e����Ӧ������飬����ʲôҲ����������ta�����ɣ�");
								}
						}
					}
					//������������
					else if(tname.equalsIgnoreCase("server")) {
						player.getInventory().setItemInMainHand(null);
						if(marriagemap.containsKey(pname)) {
							previous = marriagemap.get(pname);
							if(!Bukkit.getOfflinePlayer(previous).getName().equals("server")) {
								String thebetrayed = Bukkit.getOfflinePlayer(previous).getName();
								if(thebetrayed.equalsIgnoreCase("server")) {
									thebetrayed = "��������";
								}
								else if(thebetrayed.equalsIgnoreCase("[self]")) {
									thebetrayed = "�Լ�";
								}
									Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + "��" + thebetrayed + "���ˣ�");
									if(Bukkit.getPlayer(previous) != null) {
										JoinColor.customtag.put(previous, "��a[����]");
										Bukkit.getPlayer(previous).setDisplayName(JoinColor.updateDname(Bukkit.getPlayer(previous)));
									}
								
							}
						}
						

							marriagemap.put(pname, "server");
							if(findReverse(pname)) {
								Bukkit.broadcastMessage("��c���֡���6" + player.getName() + "��e���6���������e����ˣ�");
								setSingle("server", pname);
								JoinColor.customtag.put(pname, "��d[�����]");
								player.setDisplayName(JoinColor.updateDname(player));

								
							}
							else {
								Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + "��e��" + rose + "��" + flowertype + "���6���������e����ˣ�");
								JoinColor.customtag.put(player.getName(), "��5[����˼]");
								player.setDisplayName(JoinColor.updateDname(player));
							}
					}
					else {
						player.sendMessage("��c�����Ķ������ߣ�");
					}
				}
				else {
					player.sendMessage("��e�������û�С�c����e��");
				}
			}
				
			
			//�����������
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
								thebetrayed = "��������";
							}
							else if(thebetrayed.equalsIgnoreCase(previous + "[self]")) {
								thebetrayed = "�Լ�";
							}
								Bukkit.broadcastMessage(ChatColor.GREEN + "���������" + thebetrayed + "���ˣ�");
								if(Bukkit.getPlayer(previous) != null) {
									JoinColor.customtag.put(previous, "��a[����]");
									Bukkit.getPlayer(previous).setDisplayName(JoinColor.updateDname(Bukkit.getPlayer(previous)));
								}
							
						}
					}
					
					
					marriagemap.put("server", target.getName());
					if(findReverse("server")) {
						Bukkit.broadcastMessage("��c���֡���6���������e���6" + target.getName() + "��e����ˣ�");
						setSingle("server", target.getName());
						JoinColor.customtag.put(target.getName(), "��d[�����]");
						target.setDisplayName(JoinColor.updateDname(target));

					}
					else {
						Bukkit.broadcastMessage("��6���������e���6" + target.getName() + "��e����ˣ�");
						target.sendMessage("��e���յ������Է��������e����飡");
						target.sendMessage("��e�����6/marry server��e����Ӧ������飬����ʲôҲ����������ta�����ɣ�");
					}
				}
				else {
					sender.sendMessage("��c�����Ķ������ߣ�");
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
					JoinColor.customtag.put(key, "��9[�Թ���]");
					Bukkit.getPlayer(key).setDisplayName(JoinColor.updateDname(Bukkit.getPlayer(key)));
					Bukkit.getPlayer(key).playSound(Bukkit.getPlayer(key).getLocation(), Sound.ENTITY_WOLF_AMBIENT, (float)1.0, (float)1.0);
				}
			}
		}
	}
}
