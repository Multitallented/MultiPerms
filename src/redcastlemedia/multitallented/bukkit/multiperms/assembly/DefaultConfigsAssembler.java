package redcastlemedia.multitallented.bukkit.multiperms.assembly;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import redcastlemedia.multitallented.bukkit.multiperms.Controller;
import redcastlemedia.multitallented.bukkit.multiperms.MultiPerms;

/**
 *
 * @author Multitallented
 */
public class DefaultConfigsAssembler {
    public static boolean createUsersFile(File file) {
        try {
            file.createNewFile();
        } catch (Exception e) {
            Logger log = Logger.getLogger("Minecraft");
            log.severe("[MultiPerms] unable to create default users.yml");
            Bukkit.getPluginManager().disablePlugin((MultiPerms) Controller.getInstance("plugin"));
            return false;
        }
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (Exception e) {
            Logger log = Logger.getLogger("Minecraft");
            log.severe("[MultiPerms] unable to load default users.yml");
            Bukkit.getPluginManager().disablePlugin((MultiPerms) Controller.getInstance("plugin"));
            return false;
        }
        ArrayList<String> tempPermissions = new ArrayList<>();
        tempPermissions.add("bukkit.broadcast");
        config.set("Multitallented.permissions", tempPermissions);
        
        ArrayList<String> tempGroups = new ArrayList<>();
        tempGroups.add("default");
        config.set("Multitallented.groups", tempGroups);
        try {
            config.save(file);
        } catch (Exception e) {
            Logger log = Logger.getLogger("Minecraft");
            log.severe("[MultiPerms] unable to save default users.yml");
            Bukkit.getPluginManager().disablePlugin((MultiPerms) Controller.getInstance("plugin"));
            return false;
        }
        return true;
    }
    
    public static boolean createGroupsFile(File file) {
        try {
            file.createNewFile();
        } catch (Exception e) {
            Logger log = Logger.getLogger("Minecraft");
            log.severe("[MultiPerms] unable to create default groups.yml");
            Bukkit.getPluginManager().disablePlugin((MultiPerms) Controller.getInstance("plugin"));
            return false;
        }
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (Exception e) {
            Logger log = Logger.getLogger("Minecraft");
            log.severe("[MultiPerms] unable to load default groups.yml");
            Bukkit.getPluginManager().disablePlugin((MultiPerms) Controller.getInstance("plugin"));
            return true;
        }
        ArrayList<String> tempPermissions = new ArrayList<>();
        tempPermissions.add("^bukkit.broadcast");
        config.set("default.permissions", tempPermissions);
        
        ArrayList<String> tempGroups = new ArrayList<>();
        config.set("default.groups", tempGroups);
        config.set("default.priority", 0);
        
        
        ArrayList<String> tempPermissions1 = new ArrayList<>();
        tempPermissions1.add("bukkit.broadcast");
        config.set("admin.permissions", tempPermissions1);
        
        ArrayList<String> tempGroups1 = new ArrayList<>();
        tempGroups1.add("default");
        config.set("admin.groups", tempGroups1);
        config.set("admin.priority", 1000);
        try {
            config.save(file);
        } catch (Exception e) {
            Logger log = Logger.getLogger("Minecraft");
            log.severe("[MultiPerms] unable to save default groups.yml");
            Bukkit.getPluginManager().disablePlugin((MultiPerms) Controller.getInstance("plugin"));
            return false;
        }
        return true;
    }
}
