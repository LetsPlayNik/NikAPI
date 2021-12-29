package de.niklas.api.spigot;

import de.niklas.api.spigot.inventory.InventoryManager;
import de.niklas.api.spigot.inventory.InventoryMenu;
import de.niklas.api.spigot.inventory.PaginatedInventoryMenu;
import de.niklas.api.spigot.listeners.InventoryClickListener;
import de.niklas.api.spigot.listeners.InventoryCloseListener;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class NikAPI extends JavaPlugin {

    private InventoryManager inventoryManager;

    @Override
    public void onLoad() {
        getServer().getLogger().log(Level.INFO, "Plugin-API wird geladen...");
    }
    @Override
    public void onEnable() {
        initListeners();
        inventoryManager = new InventoryManager();
        getServer().getLogger().log(Level.INFO, "Plugin-API wurde aktiviert.");
    }
    @Override
    public void onDisable() {
        getServer().getLogger().log(Level.INFO, "Plugin-API wurde deaktiviert.");
    }

    public void initListeners() {
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("test")) {
            //System.out.println(getServer().getClass().getPackage().getName().split("\\.")[3]);
            InventoryMenu menu = new InventoryMenu(9, "Test GUI");
            menu.setItem(1, new ItemStack(Material.ARROW), player -> {
                player.sendMessage("Hallo :D");
            });
            menu.setItem(2, new ItemStack(Material.DIRT), player -> {
                player.sendMessage("Tschau :D");
            });
            menu.setItem(3, new ItemStack(Material.DIAMOND));
            InventoryMenu subMenu = new InventoryMenu(9, "Nice GUI");
            subMenu.setItem(1, new ItemStack(Material.DIAMOND), player -> {
                player.sendMessage("Sub Menu works");
            });
            subMenu.setFillItem(new ItemStack(Material.WHEAT));
            menu.setSubMenuItem(4, new ItemStack(Material.ANVIL), subMenu);
            menu.open((Player) sender);
            return true;
        } else if(command.getName().equals("paginated")) {
            PaginatedInventoryMenu menu = new PaginatedInventoryMenu(9, "Paginated Inventory");
            menu.setItem(1, new ItemStack(Material.ARROW), player -> {
                player.sendMessage("Hallo :D");
            });
            menu.setBackwardsItem(0, new ItemStack(Material.ARROW));
            menu.setForwardItem(8, new ItemStack(Material.ARROW));
            InventoryMenu test = new InventoryMenu(9, "Paginated Inventory 2");
            test.setItem(4, new ItemStack(Material.DIAMOND));
            menu.addPage(test);
            menu.open((Player) sender);
        }
        return false;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}
