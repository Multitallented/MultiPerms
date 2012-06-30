package redcastlemedia.multitallented.bukkit.multiperms.terms;

import java.util.List;

/**
 *
 * @author Multitallented
 */
public class Group {
    private int priority;
    private List<String> groups;
    private List<String> permissions;
    private final String name;
    
    public Group(String name, List<String> groups, int priority, List<String> permissions) {
        this.name = name;
        this.priority = priority;
        this.groups = groups;
        this.permissions = permissions;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
    
    public List<String> getGroups() {
        return groups;
    }
    
    public List<String> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
