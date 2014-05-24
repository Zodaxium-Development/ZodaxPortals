package me.zodaxium.zodaxportals.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.zodaxium.zodaxportals.portal.PortalManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class ZPTabCompleter implements TabCompleter{

	private final String[] cmds = {"create", "delete", "help", "info", "list"};
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
		List<String> completions = new ArrayList<String>();
		if(args.length == 1){
			String pcmd = args[0];
			List<String> commands = new ArrayList<String>(Arrays.asList(cmds));
			StringUtil.copyPartialMatches(pcmd, commands, completions);
		}
		if(args.length == 2 && (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("info"))){
			String portal = args[1];
			List<String> portals = PortalManager.getInstance().getNames();
			StringUtil.copyPartialMatches(portal, portals, completions);
		}
		Collections.sort(completions);
		return completions;
	}
}
