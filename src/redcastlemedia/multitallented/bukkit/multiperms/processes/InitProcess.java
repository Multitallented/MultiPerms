package redcastlemedia.multitallented.bukkit.multiperms.processes;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import redcastlemedia.multitallented.bukkit.multiperms.Controller;
import redcastlemedia.multitallented.bukkit.multiperms.MultiPerms;
import redcastlemedia.multitallented.bukkit.multiperms.assembly.DefaultConfigsAssembler;
import redcastlemedia.multitallented.bukkit.multiperms.managers.PermissionsManager;

/**
 *
 * @author Multitallented
 */
public class InitProcess {
    public InitProcess() {
        MultiPerms mp = (MultiPerms) Controller.getInstance("plugin");
        
        loadConfigs(mp);
        
        Controller.setInstance("perms", new PermissionsManager());
        
    }
    
    /**
     * loads groups.yml and users.yml files and stores them
     * in the Controller under usersconfig and groupsconfig
     * @param mp 
     */
    private void loadConfigs(MultiPerms mp) {
        
        File usersFile = new File(mp.getDataFolder(), "users.yml");
        if (!usersFile.exists()) {
            File folder = mp.getDataFolder();
            folder.mkdirs();
            if (!DefaultConfigsAssembler.createUsersFile(usersFile)) {
                return;
            }
        }
        FileConfiguration usersConfig = new YamlConfiguration();
        try {
            usersConfig.load(usersFile);
        } catch (Exception e) {
            Logger log = Logger.getLogger("Minecraft");
            log.severe("[MultiPerms] unable to load users.yml");
            Bukkit.getPluginManager().disablePlugin((MultiPerms) Controller.getInstance("plugin"));
            return;
        }
        Controller.setInstance("usersconfig", usersConfig);
        
        File groupsFile = new File(mp.getDataFolder(), "groups.yml");
        if (!groupsFile.exists()) {
            if (!DefaultConfigsAssembler.createGroupsFile(groupsFile)) {
                return;
            }
        }
        FileConfiguration groupsConfig = new YamlConfiguration();
        try {
            groupsConfig.load(groupsFile);
        } catch (Exception e) {
            Logger log = Logger.getLogger("Minecraft");
            log.severe("[MultiPerms] unable to load groups.yml");
            Bukkit.getPluginManager().disablePlugin((MultiPerms) Controller.getInstance("plugin"));
            return;
        }
        Controller.setInstance("groupsconfig", groupsConfig);
    }
}
