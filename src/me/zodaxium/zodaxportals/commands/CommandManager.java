package me.zodaxium.zodaxportals.commands;

import me.zodaxium.zodaxportals.Reference;
import me.zodaxium.zodaxportals.ZodaxPortals;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor{

	ZodaxPortals plugin;
	
	public CommandManager(ZodaxPortals plugin, String cmd){
		plugin.getCommand(cmd).setExecutor(this);
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.hasPermission(Reference.PERM_ADMIN)){
				AbstractCommand cmd = new Commandhelp();
				if(args.length == 0){
					cmd.execute(p, args);
					return true;
				}
				switch(args[0].toLowerCase()){
					case "help":
						cmd = new Commandhelp();
						break;
					case "list":
						cmd = new Commandlist();
						break;
					case "info":
						cmd = new Commandinfo();
						break;
					case "create":
						cmd = new Commandcreate(plugin);
						break;
					case "delete":
						cmd = new Commanddelete();
						break;
					default:
						cmd = new Commandhelp();
						break;
				}
				cmd.execute(p, args);
				return true;
			}else{
				p.sendMessage(Reference.DENY_PERM);
			}
		}else{
			sender.sendMessage(Reference.DENY_CONSOLE);
		}
		return true;
	}
}