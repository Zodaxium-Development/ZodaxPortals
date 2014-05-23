package me.zodaxium.zodaxportals.commands;

import me.zodaxium.zodaxportals.Reference;
import me.zodaxium.zodaxportals.ZodaxPortals;
import me.zodaxium.zodaxportals.portal.PortalManager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.selections.Selection;

public class Commandcreate extends AbstractCommand{

	ZodaxPortals plugin;
	
	public Commandcreate(ZodaxPortals plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void execute(Player p, String[] args){
		if(args.length >= 3){
			String name = args[1];
			String dest = args[2];
			Selection sel = plugin.we.getSelection(p);
			if(sel != null){
				Location c1 = sel.getMinimumPoint();
				Location c2 = sel.getMaximumPoint();
				if(c1.getWorld() == c2.getWorld()){
					if(PortalManager.getInstance().createPortal(name, dest, c1, c2))
						p.sendMessage(Reference.colorize(Reference.PREFIX + "&aPortal created!"));
					else
						p.sendMessage(Reference.colorize(Reference.PREFIX + "&aPortal already exists!"));
				}else{
					p.sendMessage(Reference.colorize(Reference.PREFIX + "&aSelections must be in the same world!"));
				}
			}else{
				p.sendMessage(Reference.colorize(Reference.PREFIX + "&aNo WorldEdit selection found!"));
			}
		}else{
			p.sendMessage(Reference.colorize(Reference.PREFIX + "&aUsage: /portal create (name) (destination)"));
		}
	}
}