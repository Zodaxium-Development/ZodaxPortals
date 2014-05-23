package me.zodaxium.zodaxportals.commands;

import java.util.List;

import me.zodaxium.zodaxportals.Reference;
import me.zodaxium.zodaxportals.portal.PortalManager;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

public class Commandlist extends AbstractCommand{
	
	public Commandlist(){}
	
	@Override
	public void execute(Player p, String[] args) {
		List<String> portals = PortalManager.getInstance().getNames();
		if(!portals.isEmpty())
			p.sendMessage(Reference.colorize(Reference.PREFIX + "&aPortals: &9" + StringUtils.join(portals, "&a,&9 ")));
		else
			p.sendMessage(Reference.colorize(Reference.PREFIX + "&aPortals: &9none"));
	}
}