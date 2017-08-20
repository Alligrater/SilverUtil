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
							player.sendMessage("��e��ǰû��ʣ��ĺ����Ҫ�����á�c/redbag send [����] ��e��������");
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
								player.sendMessage("��e����ȡ�ˡ�c" + items.getAmount() + "��e����Ʒ��");
								player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, (float)1.0, (float)1.0);
								if(getTotal() <= 0) {
									Bukkit.broadcastMessage(player.getDisplayName() + "��e��ȡ�����һ�������");
								}
								else {
									Bukkit.broadcastMessage(player.getDisplayName() + "��e��ȡ��1����������ڻ���" + getTotal() +"������������c/redbag claim��e����ȡ�ɣ�");
								}
								
								cplayer.get(pindex).lastclaim = new Date().getTime();
								if(redbags.get(index).items.getAmount() <= 0) {
									redbags.remove(index);
								}

							}
							else {
								player.sendMessage("��e����������ȴ�У���ȴ�" + (int)(300 - diff/1000) + "�룡");
							}
							
							

							}
						}
					else if(arg3[0].equals("claimall") && player.hasPermission("silverutil.redbag.claimall")) {
						if(getTotal() <= 0) {
							player.sendMessage("��e��ǰû��ʣ��ĺ����Ҫ�����á�c/redbag send [����] ��e��������");
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
							Bukkit.broadcastMessage(player.getDisplayName() + "��e��ȡ�����еĺ����");
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
								sender.sendMessage("��e�����������������֡�");
								return false;
							}
							if(((int)itemcount/Integer.parseInt(arg3[1])) * Integer.parseInt(arg3[1]) == itemcount) {
								redbags.add(new RedBag(player.getName(), player.getInventory().getItemInMainHand(), itemcount/Integer.parseInt(arg3[1])));
								Bukkit.broadcastMessage(player.getDisplayName() + "��e������" + redbags.get(redbags.size()-1).remain() +"������������c/redbag claim��e����ȡ�ɣ�");
								player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, (float)1.0, (float)1.0);
								player.getInventory().setItemInMainHand(null);
								
							}
							else {
								sender.sendMessage("��e��������޷���������");
							}
						}
						else {
							sender.sendMessage("��e�������û�ж�����");
						}

						
					}
					

				}

				else if(arg3.length >= 3) {
					return false;
				}
				else {
					player.sendMessage("��cSilverUtil��� ��eInspired by connection_lost, developed by SilverKela");
					player.sendMessage("��e��ǰ�С�c"+ getTotal() + "��e�����δ��ȡ��");
					player.sendMessage("��e�����c/redbag claim��e ���Դ�Ŀǰ���к������ȡ���һ����");
					player.sendMessage("��e�����c/redbag send [�������]��e �����ͳ����");
					player.sendMessage("��e��Ʒ��������Ҫ���Ա��������������");
					return true;
				}
				return true;
			}
			else {
				player.sendMessage("��c�㲻��������ʹ��/redbag!");
			}

		}
		else {
			sender.sendMessage("��c������������");
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
