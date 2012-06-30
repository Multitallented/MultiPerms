package redcastlemedia.multitallented.bukkit.multiperms.managers;

import java.util.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import redcastlemedia.multitallented.bukkit.multiperms.Controller;
import redcastlemedia.multitallented.bukkit.multiperms.terms.Group;
import redcastlemedia.multitallented.bukkit.multiperms.terms.MPlayer;

/**
 *
 * @author Multitallented
 */
public class PermissionsManager {
    private HashMap<String, MPlayer> players = new HashMap<>();
    private HashMap<String, HashSet<String>> finalPermissions = new HashMap<>();
    private HashMap<String, Group> groups = new HashMap<>();
    
    public PermissionsManager() {
        FileConfiguration usersConfig = (FileConfiguration) Controller.getInstance("usersconfig");
        FileConfiguration groupsConfig = (FileConfiguration) Controller.getInstance("groupsconfig");
        
        for (String s : groupsConfig.getKeys(false)) {
            ConfigurationSection cs = groupsConfig.getConfigurationSection(s);
            List<String> tempPerms = cs.getStringList("permissions");
            List<String> tempGroups = cs.getStringList("groups");
            Group tempGroup = new Group(s, tempGroups, cs.getInt("priority", 0), tempPerms);
            groups.put(s, tempGroup);
        }
        for (String s : usersConfig.getKeys(false)) {
            ConfigurationSection cs = usersConfig.getConfigurationSection(s);
            List<String> tempPerms = cs.getStringList("permissions");
            List<String> tempGroups = cs.getStringList("groups");
            players.put(s, new MPlayer(s, tempPerms, tempGroups));
        }
        
        initPerms();
    }
    
    private void initPerms() {
        for (String s : players.keySet()) {
            MPlayer p = players.get(s);
            ArrayList<Group> sortedGroups = new ArrayList<>();
            HashSet<String> tempPerms = new HashSet<>();
            for (String s1 : p.getGroups()) {
                sortedGroups.add(groups.get(s1));
            }
            Collections.sort(sortedGroups, new Comparator<Group>() {

                @Override
                public int compare(Group o1, Group o2) {
                    return o1.getPriority() - o2.getPriority();
                }
        
            });
            for (Group g : sortedGroups) {
                try {
                    for (String s2 : g.getPermissions()) {
                        if (s2.startsWith("^")) {
                            if (tempPerms.contains(s2.substring(1))) {
                                tempPerms.remove(s2.substring(1));
                            }
                        } else {
                            tempPerms.add(s2);
                        }
                    }
                } catch (NullPointerException npe) {
                    
                }
            }
            for (String s3 : p.getPermissions()) {
                try {
                    if (s3.startsWith("^")) {
                        if (tempPerms.contains(s3.substring(1))) {
                            tempPerms.remove(s3.substring(1));
                        }
                    } else {
                        tempPerms.add(s3);
                    }
                } catch (NullPointerException npe) {
                    
                }
            }
            finalPermissions.put(s, tempPerms);
        }
    }
    
    public boolean hasPermission(String name, String perm) {
        try {
            return finalPermissions.get(name).contains(perm);
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean hasGroup(String name, String group) {
        try {
            return players.get(name).getGroups().contains(group);
        } catch (Exception e) {
            return false;
        }
    }
    
    public HashSet<String> getPerms(String name) {
        return finalPermissions.get(name);
    }
}
