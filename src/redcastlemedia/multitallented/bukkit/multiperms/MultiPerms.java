package redcastlemedia.multitallented.bukkit.multiperms;

import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import redcastlemedia.multitallented.bukkit.multiperms.commands.*;
import redcastlemedia.multitallented.bukkit.multiperms.processes.InitProcess;

public class MultiPerms extends JavaPlugin implements Listener {
    private HashMap<String, MPCommand> commands = new HashMap<>();
    
    @Override
    public void onDisable() {
        Logger log = Logger.getLogger("Minecraft");
        log.info("[MultiPerms] disabled!");
    }

    @Override
    public void onEnable() {
        new Controller();
        Controller.setInstance("plugin", this);
        new InitProcess();
        
        getServer().getPluginManager().registerEvents(this, this);
        
        //add commands here
        commands.put("hasperm", new HasPermCommand());
        commands.put("listperms", new ListPermsCommand());
        
        Logger log = Logger.getLogger("Minecraft");
        log.info("[MultiPerms] enabled!");
    }
    
    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
        if (commands.containsKey(label)) {
            commands.get(label).handle(cs, command, label, args);
        }
        return true;
    }
}

