/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package redcastlemedia.multitallented.bukkit.multiperms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Multitallented
 */
public interface MPCommand {
    public abstract void handle(CommandSender cs, Command command, String label, String[] args);
}
