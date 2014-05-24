package me.zodaxium.zodaxportals.portal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.zodaxium.zodaxportals.ZodaxPortals;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class PortalManager{

	private static PortalManager pm;
	public ZodaxPortals plugin;
	public List<Portal> portals = new ArrayList<Portal>();
	
	public PortalManager(ZodaxPortals plugin){
		this.plugin = plugin;
	}
	
	public static PortalManager getInstance(){
		if(pm == null)
			pm = new PortalManager(ZodaxPortals.getInstance());
		return pm;
	}
	
	public void loadPortals(){
		Set<String> keys = plugin.getConfig().getConfigurationSection("Portal").getKeys(false);
		if(keys.isEmpty() || keys == null) return;
		for(String key : keys){
			String[] info = plugin.getConfig().getString("Portal." + key).split(":");
			World world = Bukkit.getWorld(info[0]);
			String dest = info[1];
			Location c1 = new Location(world, Double.parseDouble(info[2]), Double.parseDouble(info[3]), Double.parseDouble(info[4]));
			Location c2 = new Location(world, Double.parseDouble(info[5]), Double.parseDouble(info[6]), Double.parseDouble(info[7]));
			Portal p = new Portal(key, world, dest, generateList(c1, c2));
			portals.add(p);
		}
	}
	
	public boolean createPortal(String name, World world, String dest, Location c1, Location c2){
		if(exists(name)) 
			return false;
		Portal p = new Portal(name, world, dest, generateList(c1, c2));
		portals.add(p);
		plugin.getConfig().set("Portal." + name, s(world, dest, c1, c2));
		plugin.saveConfig();
		return true;
	}
	
	public boolean deletePortal(String name){
		Portal p = getPortal(name);
		if(p == null)
			return false;
		portals.remove(p);
		plugin.getConfig().set("Portal." + name, null);
		plugin.saveConfig();
		return true;
	}
	
	public boolean exists(String s){
		for(Portal p : portals){
			if(s.equalsIgnoreCase(p.getName()))
				return true;
		}
		return false;
	}
	
	public List<String> getNames(){
		List<String> names = new ArrayList<String>();
		for(Portal p : portals){
			names.add(p.getName());
		}
		return names;
	}
	
	public Portal getPortal(String name){
		for(Portal p : portals){
			if(p.getName().equalsIgnoreCase(name))
				return p;
		}
		return null;
	}
	
	public Portal inPortal(Location loc){
		for(Portal p : portals){
			if(p.getArea().contains(loc.getBlock()) && p.getWorld().equals(loc.getWorld()))
				return p;
		}
		return null;
	}
	
	public List<Block> generateList(Location loc1, Location loc2){
        List<Block> list = new ArrayList<Block>();
 
        int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
        int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
 
        int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
        int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
 
        int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
        int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
 
        for(int x = bottomBlockX; x <= topBlockX; x++){
            for(int z = bottomBlockZ; z <= topBlockZ; z++){
                for(int y = bottomBlockY; y <= topBlockY; y++){
                    list.add(loc1.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return list;
    }
	
	public String s(World world, String dest, Location l1, Location l2){
		return world.getName() + ":" + dest + ":" + l1.getBlockX() + ":" + l1.getBlockY() + ":" + l1.getBlockZ() + ":" + l2.getBlockX() + ":" + l2.getBlockY() + ":" + l2.getBlockZ();
	}
}
