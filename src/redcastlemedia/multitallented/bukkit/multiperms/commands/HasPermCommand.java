/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package redcastlemedia.multitallented.bukkit.multiperms.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redcastlemedia.multitallented.bukkit.multiperms.Controller;
import redcastlemedia.multitallented.bukkit.multiperms.managers.PermissionsManager;

/**
 *
 * @author Multitallented
 */
public class HasPermCommand implements MPCommand {

    @Override
    public void handle(CommandSender cs, Command command, String label, String[] args) {
        if (args.length < 2) {
            return;
        }
        PermissionsManager pm = (PermissionsManager) Controller.getInstance("perms");
        Player p = Bukkit.getPlayer(args[0]);
        String name = args[0];
        if (p != null) {
            name = p.getName();
        }
        boolean hasPerms = pm.hasPermission(name, args[1]);
        if (hasPerms) {
            cs.sendMessage(ChatColor.GREEN + "[MultiPerms] " + name + " has " + args[1]);
        } else {
            cs.sendMessage(ChatColor.RED + "[MultiPerms] " + name + " doesnt have " + args[1]);
        }
    }
    
}
