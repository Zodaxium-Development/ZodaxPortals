package me.zodaxium.zodaxportals.commands;

import me.zodaxium.zodaxportals.Reference;
import me.zodaxium.zodaxportals.portal.PortalManager;

import org.bukkit.entity.Player;

public class Commanddelete extends AbstractCommand{

	@Override
	public void execute(Player p, String[] args) {
		if(args.length >= 2){
			String name = args[1];
			if(PortalManager.getInstance().deletePortal(name))
				p.sendMessage(Reference.colorize(Reference.PREFIX + "&aPortal deleted!"));
			else
				p.sendMessage(Reference.colorize(Reference.PREFIX + "&aPortal not found!"));
		}else{
			p.sendMessage(Reference.colorize(Reference.PREFIX + "&aUsage: /portal delete (name)"));
		}
	}
}
