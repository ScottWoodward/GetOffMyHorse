/**
 * Copyright 2013 - 2014 Scott Woodward
 *
 * This file is part of GetOffMyHorse
 *
 * GetOffMyHorse is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GetOffMyHorse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GetOffMyHorse.  If not, see <http://www.gnu.org/licenses/>. 
 */
package com.scottwoodward.getoffmyhorse;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * GetOffMyHorse.java
 * Purpose: Ensures that only a horse's tamer can interact with it. 
 *
 * @author Scott Woodward
 */
public class GetOffMyHorse extends JavaPlugin implements Listener{
    
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onHorseInteract(PlayerInteractEntityEvent event)
    {
        if(event.getRightClicked() != null && event.getRightClicked() instanceof Horse)
        {
            Horse horse = (Horse)event.getRightClicked();
            if(horse.getOwner() instanceof OfflinePlayer && event.getPlayer() != null)
            {
                if(!((OfflinePlayer)horse.getOwner()).getName().equalsIgnoreCase(event.getPlayer().getName()))
                {
                    event.getPlayer().sendMessage(ChatColor.DARK_RED + "That isn't your horse");
                    event.setCancelled(true);
                }
            }
        }
    }

}
