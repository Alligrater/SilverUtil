package io.github.Alligrater;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RedBagSend implements CommandExecutor{
	static ArrayList<RedBag> redbags = new ArrayList<RedBag>();
	static ArrayList<RedBagPlayer> cplayer = new ArrayList<RedBagPlayer>();

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {

		// TODO Auto-generated method stub
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(!SilverUtil.forbidden.contains(player.getWorld().getName()) || player.hasPermission("silverutil.gift.anywhere")) {
				if(arg3.length == 1) {
					if(arg3[0].equals("claim")) {
						if(getTotal() <= 0) {
							player.sendMessage("§e当前没有剩余的红包！要不你用§c/redbag send [数量] §e发几个？");
						}
						else {
							
							int pindex = -1; 
							if(getPlayerIndex(player) != -1) {
								pindex = getPlayerIndex(player);
							}
							else {
								cplayer.add(new RedBagPlayer(player.getUniqueId(), player.getLastPlayed()));
								pindex = cplayer.size() - 1;
							}
							
							
							Date d = new Date();
							
							long diff = cplayer.get(pindex).getTimeDiff(d.getTime());
							if(diff > 300000 || player.hasPermission("silverutil.redbag.unlimit")) {
								double rng = Math.random();
								int index = (int)((rng*redbags.size() + 1) - 1);
								ItemStack items = redbags.get(index).claim();
								player.getInventory().addItem(items);
								player.sendMessage("§e你领取了§c" + items.getAmount() + "§e个物品！");
								player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, (float)1.0, (float)1.0);
								if(getTotal() <= 0) {
									Bukkit.broadcastMessage(player.getDisplayName() + "§e领取了最后一个红包！");
								}
								else {
									Bukkit.broadcastMessage(player.getDisplayName() + "§e领取了1个红包！现在还有" + getTotal() +"个红包，输入§c/redbag claim§e来领取吧！");
								}
								
								cplayer.get(pindex).lastclaim = new Date().getTime();
								if(redbags.get(index).items.getAmount() <= 0) {
									redbags.remove(index);
								}

							}
							else {
								player.sendMessage("§e领红包还在冷却中！请等待" + (int)(300 - diff/1000) + "秒！");
							}
							
							

							}
						}
					else if(arg3[0].equals("claimall") && player.hasPermission("silverutil.redbag.claimall")) {
						if(getTotal() <= 0) {
							player.sendMessage("§e当前没有剩余的红包！要不你用§c/redbag send [数量] §e发几个？");
						}
						else {
							for(int rbi = 0; rbi < redbags.size(); rbi++) {
								int remain = redbags.get(rbi).remain();
								for(int rb = 0; rb < remain; rb++) {
									ItemStack items = redbags.get(rbi).claim();
									player.getInventory().addItem(items);
								}
							}
							redbags.clear();
							Bukkit.broadcastMessage(player.getDisplayName() + "§e领取了所有的红包！");
							player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, (float)1.0, (float)1.0);
						}
						return true;
					}
					else {
						return false;
					}
				}
				else if(arg3.length == 2) {
					if(arg3[0].equals("send")) {
						if(player.getInventory().getItemInMainHand() != null) {
							
							int itemcount = player.getInventory().getItemInMainHand().getAmount();
							try {
								int i = Integer.parseInt(arg3[1]);
							} catch (Exception e) {
								sender.sendMessage("§e参数有误，请输入数字。");
								return false;
							}
							if(((int)itemcount/Integer.parseInt(arg3[1])) * Integer.parseInt(arg3[1]) == itemcount) {
								redbags.add(new RedBag(player.getName(), player.getInventory().getItemInMainHand(), itemcount/Integer.parseInt(arg3[1])));
								Bukkit.broadcastMessage(player.getDisplayName() + "§e发送了" + redbags.get(redbags.size()-1).remain() +"个红包！输入§c/redbag claim§e来领取吧！");
								player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, (float)1.0, (float)1.0);
								player.getInventory().setItemInMainHand(null);
								
							}
							else {
								sender.sendMessage("§e红包数量无法被整除！");
							}
						}
						else {
							sender.sendMessage("§e你的手里没有东西！");
						}

						
					}
					

				}

				else if(arg3.length >= 3) {
					return false;
				}
				else {
					player.sendMessage("§cSilverUtil红包 §eInspired by connection_lost, developed by SilverKela");
					player.sendMessage("§e当前有§c"+ getTotal() + "§e个红包未领取。");
					player.sendMessage("§e输入§c/redbag claim§e 可以从目前所有红包中领取随机一个。");
					player.sendMessage("§e输入§c/redbag send [红包总数]§e 可以送出红包");
					player.sendMessage("§e物品的数量需要可以被红包数量整除。");
					return true;
				}
				return true;
			}
			else {
				player.sendMessage("§c你不能在这里使用/redbag!");
			}

		}
		else {
			sender.sendMessage("§c你你你你想干嘛！");
			return true;
		}
		return true;

	}
	
	public static int getTotal() {
		int count = 0;
		
		for(RedBag rb: redbags) {
			count += rb.remain();
		}
		return count;
		
	}
	
	public int getPlayerIndex(Player player) {
		int i = 0;
		for(RedBagPlayer rbp: cplayer) {
			
			if(rbp.uuid.equals(player.getUniqueId())) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	


}
