package de.niklas.nikapi.spigot;

import de.niklas.nikapi.spigot.config.YAMLConfig;
import de.niklas.nikapi.spigot.inventory.InventoryManager;
import de.niklas.nikapi.spigot.inventory.InventoryMenu;
import de.niklas.nikapi.spigot.inventory.PaginatedInventoryMenu;
import de.niklas.nikapi.spigot.item.custom.mob.CustomMob;
import de.niklas.nikapi.spigot.item.custom.mob.LootItem;
import de.niklas.nikapi.spigot.item.custom.mob.MobManager;
import de.niklas.nikapi.spigot.listeners.*;
import de.niklas.nikapi.spigot.nms.MinecraftVersion;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

public class NikAPI extends JavaPlugin {

    private static NikAPI instance;

    @Override
    public void onLoad() {
        getServer().getLogger().log(Level.INFO, "Plugin-API wird geladen...");
    }
    @Override
    public void onEnable() {
        init(this);
        getServer().getLogger().log(Level.INFO, "Plugin-API wurde aktiviert.");
    }
    @Override
    public void onDisable() {
        getServer().getLogger().log(Level.INFO, "Plugin-API wurde deaktiviert.");
    }

    public void init(Plugin plugin) {
        instance = this;
        new InventoryManager();
        new MobManager();
        initListeners(plugin);
    }
    public void initListeners(Plugin plugin) {
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), plugin);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), plugin);
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), plugin);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("test")) {
            //System.out.println(getServer().getClass().getPackage().getName().split("\\.")[3]);
            InventoryMenu menu = new InventoryMenu(9, "Test GUI");
            menu.setItem(1, new ItemStack(Material.ARROW), action -> {
                action.getPlayer().sendMessage("Hallo :D");
                action.getPlayer().sendMessage("Leftclick: " + action.isLeftClick());
                action.getPlayer().sendMessage("Rightclick: " + action.isRightClick());
                action.getPlayer().sendMessage("Shiftclick: " + action.isShiftClick());
                action.getPlayer().sendMessage("Middle mouse click: " + action.isMiddleMouseClick());
            });
            menu.setItem(2, new ItemStack(Material.DIRT), action -> {
                action.getPlayer().sendMessage("Tschau :D");
            });
            menu.setItem(3, new ItemStack(Material.DIAMOND));
            InventoryMenu subMenu = new InventoryMenu(9, "Nice GUI");
            subMenu.setItem(1, new ItemStack(Material.DIAMOND), action -> {
                action.getPlayer().sendMessage("Sub Menu works");
            });
            subMenu.setFillItem(new ItemStack(Material.WHEAT));
            menu.setSubMenuItem(4, new ItemStack(Material.ANVIL), subMenu);
            menu.open((Player) sender);
            return true;
        } else if(command.getName().equals("paginated")) {
            PaginatedInventoryMenu menu = new PaginatedInventoryMenu(9, "Paginated Inventory", 0, new ItemStack(Material.ARROW), 8, new ItemStack(Material.ARROW));
            menu.setItem(1, new ItemStack(Material.ARROW), player -> {
                player.getPlayer().sendMessage("Hallo :D");
            });
            InventoryMenu test = new InventoryMenu(9, "Paginated Inventory 2");
            test.setItem(4, new ItemStack(Material.DIAMOND));
            menu.addPage(test);
            InventoryMenu test2 = new InventoryMenu(9, "Paginated Inventory 3");
            test2.setItem(4, new ItemStack(Material.DIRT));
            menu.addPage(test2);
            InventoryMenu test3 = new InventoryMenu(9, "Paginated Inventory 4");
            test3.setItem(4, new ItemStack(Material.GOLD_INGOT));
            menu.addPage(test3);

            InventoryMenu test4 = new InventoryMenu(9, "Paginated Inventory 5");
            /*List<ItemStack> items = new LinkedList<>();
            items.add(new ItemStack(Material.DIAMOND));
            items.add(new ItemStack(Material.EMERALD));
            items.add(new ItemStack(Material.IRON_INGOT));
            items.add(new ItemStack(Material.GOLD_INGOT));
            items.add(new ItemStack(Material.COAL));
            items.add(new ItemStack(Material.LAPIS_ORE));
            items.add(new ItemStack(Material.LEATHER));
            items.add(new ItemStack(Material.DIRT));
            items.add(new ItemStack(Material.WHEAT));
            test4.addItemStacks(items);*/
            menu.addPage(test4);

            InventoryMenu test5 = new InventoryMenu(9, "Paginated Inventory 6");
            test5.setItem(4, new ItemStack(Material.LAVA_BUCKET));
            menu.addPage(test5);
            menu.open((Player) sender);
        } else if(command.getName().equalsIgnoreCase("lol")) {
            InventoryMenu menu = new InventoryMenu(9, "Paginated Inventory");
            List<ItemStack> items = new LinkedList<>();
            items.add(new ItemStack(Material.DIAMOND));
            items.add(new ItemStack(Material.EMERALD));
            items.add(new ItemStack(Material.IRON_INGOT));
            items.add(new ItemStack(Material.GOLD_INGOT));
            items.add(new ItemStack(Material.COAL));
            items.add(new ItemStack(Material.LAPIS_ORE));
            items.add(new ItemStack(Material.LEATHER));
            items.add(new ItemStack(Material.DIRT));
            items.add(new ItemStack(Material.WHEAT));
            menu.setItem(0, new ItemStack(Material.LAVA_BUCKET));
            //menu.addItemStacks(items);
            menu.open((Player) sender);
            return true;
        } else if(command.getName().equalsIgnoreCase("addpage")) {
            PaginatedInventoryMenu menu = new PaginatedInventoryMenu(9, "Paginated Inventory", 0, new ItemStack(Material.ARROW), 8, new ItemStack(Material.ARROW));
            return true;
        } else if(command.getName().equalsIgnoreCase("add")) {
            PaginatedInventoryMenu menu = new PaginatedInventoryMenu(9, "Paginated Inventory ...", 0, new ItemStack(Material.ARROW), 8, new ItemStack(Material.ARROW));
            InventoryMenu lol = new InventoryMenu(9, "Paginated Inventory :)");
            lol.setItem(1, new ItemStack(Material.ANVIL));
            menu.addPage(lol);
            /*InventoryMenu lol = new InventoryMenu(9, "Paginated Inventory :)");
            lol.setItem(1, new ItemStack(Material.ANVIL));
            menu.addPage(lol);
            InventoryMenu lol2 = new InventoryMenu(9, "Paginated Inventory :(");
            lol.setItem(1, new ItemStack(Material.DIRT));
            menu.addPage(lol2);*/
            /*menu.addItem(new ItemStack(Material.DIAMOND));
            menu.addItem(new ItemStack(Material.EMERALD));
            menu.addItem(new ItemStack(Material.IRON_INGOT));
            menu.addItem(new ItemStack(Material.GOLD_INGOT));
            menu.addItem(new ItemStack(Material.COAL));
            menu.addItem(new ItemStack(Material.LAPIS_ORE));
            menu.addItem(new ItemStack(Material.LEATHER));
            menu.addItem(new ItemStack(Material.DIRT));
            menu.addItem(new ItemStack(Material.WHEAT));
            menu.addItem(new ItemStack(Material.LAVA_BUCKET));

            menu.addItem(new ItemStack(Material.ANVIL));*/
            /*menu.addItem(new ItemStack(Material.EMERALD));
            menu.addItem(new ItemStack(Material.IRON_INGOT));
            menu.addItem(new ItemStack(Material.GOLD_INGOT));
            menu.addItem(new ItemStack(Material.COAL));
            menu.addItem(new ItemStack(Material.LAPIS_ORE));
            menu.addItem(new ItemStack(Material.LEATHER));*/
            //menu.addItem(new ItemStack(Material.DIRT));
            //menu.addItem(new ItemStack(Material.WHEAT));
            //menu.addItem(new ItemStack(Material.LAVA_BUCKET));
            menu.open((Player) sender);
            return true;
        } else if(command.getName().equalsIgnoreCase("config")) {
            //JsonConfig config = new JsonConfig("test.json", null);
            return true;
        }
        if(command.getName().equalsIgnoreCase("spawn")) {
            CustomMob mob = new CustomMob("Test Zombie - §c100/100❤", true, 100, EntityType.ZOMBIE, new ItemStack(Material.DIAMOND_AXE), null, new LootItem(new ItemStack(Material.DIAMOND), 30), new LootItem(new ItemStack(Material.DIAMOND_HOE), 60), new LootItem(new ItemStack(Material.DIAMOND_AXE), 10));
            mob.spawn(((Player) sender).getLocation());
            return true;

            /*@EventHandler
            public void onEntityDamage(EntityDamageEvent event) {
                if(!MobManager.getInstance().getCustomMobs().containsKey(event.getEntity())) return;
                LivingEntity livingEntity = (LivingEntity) event.getEntity();
                livingEntity.setCustomName("Test Zombie - " + ChatColor.RED + (int) livingEntity.getHealth() + "/" + (int) livingEntity.getMaxHealth() + "❤");
            }*/
        }
        if(command.getName().equalsIgnoreCase("ymlconfig")) {
            try {
                YAMLConfig config = new YAMLConfig("plugins/API", "test.yml");
                config.addDefaultEntry("Test", "Wow");
                config.addDefaultEntry("Wow", "LOL");
                config.save();
                System.out.println(config.getString("Test"));
                System.out.println(config.getString("Wow"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(command.getName().equalsIgnoreCase("customworld")) {
            String worldName = "custombiome";
            String biome = "DESERT";
            if(MinecraftVersion.getVersion() != null) {
                System.out.println(MinecraftVersion.getVersion().name());
            }
            //String versionPath = "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
            String versionPath = "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            try {
                Class clazz = Class.forName(versionPath + ".BiomeBase");
                Field[] fields = clazz.getDeclaredFields();
                for(int i = 0; i < fields.length; i++) {
                    System.out.println("Field: " + fields[i].toString());
                }
                /*Field plainsField = clazz.getDeclaredField(biome);
                plainsField.setAccessible(true);
                Object plainsBiome = plainsField.get(null);
                Field biomesField = clazz.getDeclaredField("biomes");
                biomesField.setAccessible(true);
                Object[] biomes = (Object[]) biomesField.get(null);
                for(int i = 0; i < biomes.length; i++) {
                    biomes[i] = plainsBiome;
                }
                biomesField.set(null, biomes);
                WorldCreator worldCreator = new WorldCreator(worldName);
                worldCreator.environment(World.Environment.NORMAL);
                worldCreator.type(WorldType.LARGE_BIOMES);
                Bukkit.createWorld(worldCreator);*/
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return  true;
        }
        return false;
    }

    public static NikAPI getInstance() {
        return instance;
    }
}
