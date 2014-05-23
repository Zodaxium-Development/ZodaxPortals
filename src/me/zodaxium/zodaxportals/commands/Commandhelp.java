package me.zodaxium.zodaxportals.commands;

import me.zodaxium.zodaxportals.Reference;

import org.bukkit.entity.Player;

public class Commandhelp extends AbstractCommand{

	@Override
	public void execute(Player p, String[] args) {
		p.sendMessage(Reference.colorize("&7------------ [&a ZodaxPortals &7] ------------"));
		p.sendMessage(Reference.colorize("&7- &a/portal help &f- &7Display this menu"));
		p.sendMessage(Reference.colorize("&7- &a/portal list &f- &7List all known portals"));
		p.sendMessage(Reference.colorize("&7- &a/portal info (portal) &f- &7Get info on a portal"));
		p.sendMessage(Reference.colorize("&7- &a/portal create (portal) (destination) &f- &7Create a portal"));
		p.sendMessage(Reference.colorize("&7- &a/portal delete (portal) &f- &7Delete a portal"));
	}
}
