package com.dustinschreiber.AnvilSaver;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnvilSaver extends JavaPlugin implements Listener{
    
	double pluginVersion = 1.0;
	
	@Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
    	getLogger().info("[WELCOME] AnvilSaver " + pluginVersion + " enabled!");
    }
 
    @Override
    public void onDisable() {
        getLogger().info("[BYE] AnvilSaver " + pluginVersion + " disabled!");
    }
    
    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent event){
    	if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
    		if(event.getClickedBlock().getType() == Material.ANVIL){
    			if(event.getClickedBlock().getData() > 7){
    				byte anvilRotation = event.getClickedBlock().getData();
    				event.getClickedBlock().setData((byte) (anvilRotation - 8));
    			}
    		}
    	}
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
    	Player player = event.getPlayer();
    	Material tool = player.getItemInHand().getType();
    	if((tool == Material.DIAMOND_PICKAXE) || (tool == Material.GOLD_PICKAXE) || (tool == Material.IRON_PICKAXE) || (tool == Material.STONE_PICKAXE)){
    		if(event.getBlock().getType() == Material.ANVIL){
    			if(event.getBlock().getData() > 7){
    				event.setCancelled(true);
    				event.getBlock().setType(Material.AIR);
    				event.getBlock().setType(Material.ANVIL);
    				player.sendMessage(ChatColor.GREEN + "New anvil created!");
    			}
    		}
    	}
    }
}