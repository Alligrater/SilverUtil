package io.github.Alligrater;

import java.util.Date;
import java.util.UUID;

public class RedBagPlayer {
	UUID uuid;
	long lastclaim;
	public RedBagPlayer(UUID uuid, long lastclaim) {
		this.uuid = uuid;
		this.lastclaim = lastclaim;
	}
	
	public long getTimeDiff(long now) {
		long diff = now - this.lastclaim;
		return diff;
	}
	
}
