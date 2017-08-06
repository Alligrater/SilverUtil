package io.github.Alligrater;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TellMe implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		Player player = (Player)arg0;
		float v = player.getEyeLocation().getYaw();
		v =  v%360;
		v = (v + 360) % 360;
		if(v >180)
		    v -=360;
		player.sendMessage("Yaw:" +  v);
		return true;
	}

}
