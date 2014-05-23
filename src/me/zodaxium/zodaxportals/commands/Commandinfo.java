package me.zodaxium.zodaxportals.commands;

import me.zodaxium.zodaxportals.Reference;
import me.zodaxium.zodaxportals.portal.Portal;
import me.zodaxium.zodaxportals.portal.PortalManager;

import org.bukkit.entity.Player;

public class Commandinfo extends AbstractCommand{

	@Override
	public void execute(Player p, String[] args){
		if(args.length >= 2){
			String name = args[1];
			Portal portal = PortalManager.getInstance().getPortal(name);
			if(portal != null){
				p.sendMessage(Reference.colorize(Reference.PREFIX + "&aName: &9" + portal.getName() + "&a, Destination: &9" + portal.getDest()));
			}else{
				p.sendMessage(Reference.colorize(Reference.PREFIX + "&aUnknown portal!"));
			}
		}else{
			p.sendMessage(Reference.colorize(Reference.PREFIX + "&aUsage: /portal info (name)"));
		}
	}
}
