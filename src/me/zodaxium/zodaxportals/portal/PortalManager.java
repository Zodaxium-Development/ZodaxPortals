package me.zodaxium.zodaxportals.portal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.zodaxium.zodaxportals.ZodaxPortals;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
			String dest = plugin.getConfig().getString("Portal." + key + ".dest");
			String[] co1 = plugin.getConfig().getString("Portal." + key + ".corner1").split(":");
			String[] co2 = plugin.getConfig().getString("Portal." + key + ".corner2").split(":");
			Location c1 = new Location(Bukkit.getWorld(co1[0]), Double.parseDouble(co1[1]), Double.parseDouble(co1[2]), Double.parseDouble(co1[3]));
			Location c2 = new Location(Bukkit.getWorld(co2[0]), Double.parseDouble(co2[1]), Double.parseDouble(co2[2]), Double.parseDouble(co2[3]));
			Portal p = new Portal(key, dest, generateList(c1, c2));
			portals.add(p);
		}
	}
	
	public boolean createPortal(String name, String dest, Location c1, Location c2){
		if(exists(name)) 
			return false;
		Portal p = new Portal(name, dest, generateList(c1, c2));
		portals.add(p);
		plugin.getConfig().set("Portal." + name + ".dest", dest);
		plugin.getConfig().set("Portal." + name + ".corner1", s(c1));
		plugin.getConfig().set("Portal." + name + ".corner2", s(c2));
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
			if(p.getArea().contains(loc.getBlock()))
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
	
	public String s(Location l){
		return l.getWorld().getName() + ":" + l.getX() + ":" + l.getY() + ":" + l.getZ();
	}
}
