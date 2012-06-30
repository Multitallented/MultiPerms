/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package redcastlemedia.multitallented.bukkit.multiperms.commands;

import java.util.HashSet;
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
public class ListPermsCommand implements MPCommand {

    @Override
    public void handle(CommandSender cs, Command command, String label, String[] args) {
        if (args.length < 1) {
            return;
        }
        String name = args[0];
        Player p = Bukkit.getPlayer(args[0]);
        try {
            name = p.getName();
        } catch (NullPointerException npe) {
            
        }
        PermissionsManager pm = (PermissionsManager) Controller.getInstance("perms");
        HashSet<String> perms = pm.getPerms(args[0]);
        try {
            for (String s : perms) {
                cs.sendMessage(ChatColor.GREEN + s);
            }
        } catch (NullPointerException npe) {
            cs.sendMessage(ChatColor.RED + "[MultiPerms] Enable to find permissions for " + name);
        }
    }
    
}
