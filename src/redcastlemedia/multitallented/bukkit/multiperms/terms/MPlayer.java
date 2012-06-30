package redcastlemedia.multitallented.bukkit.multiperms.terms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Multitallented
 */
public class MPlayer {
    private List<String> permissions = new ArrayList<>();
    private List<String> groups = new ArrayList<>();
    private final String name;
    
    public MPlayer(String name, List<String> permissions, List<String> groups) {
        this.name = name;
        this.permissions = permissions;
        this.groups = groups;
    }
    
    public String getName() {
        return name;
    }
    
    public List<String> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
    
    public List<String> getGroups() {
        return groups;
    }
    
    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
