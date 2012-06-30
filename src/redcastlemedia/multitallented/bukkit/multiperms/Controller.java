package redcastlemedia.multitallented.bukkit.multiperms;

import java.util.HashMap;

/**
 *
 * @author Multitallented
 */
public class Controller {
    private static HashMap<String, Object> instances = new HashMap<>();
    
    public static Object getInstance(String name) {
        return instances.get(name);
    }
    
    public static void setInstance(String name, Object instance) {
        instances.put(name, instance);
    }
}
