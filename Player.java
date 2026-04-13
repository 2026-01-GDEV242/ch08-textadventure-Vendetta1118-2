import java.util.ArrayList;
/**
 * The Player class represents the user playing the game.
 * It stores the player's inventory and allows items to be
 * added, removed, and displayed.
 * @author Joseph Schiavone
 * @version 2026.04.12
 */
public class Player
{
    private ArrayList<Item> inventory;
    
    /**
     * Create a new player with an empty inventory.
     */
    public Player()
    {
        inventory = new ArrayList<Item>();
    }
    
    /**
     * Add an item to the player's inventory.
     * 
     * @param item The item to add
     */
    public void addItem(Item item)
    {
        inventory.add(item);
    }
    
    /**
     * Remove an item from the inventory by name.
     * 
     * @param name The name of the item to remove
     * @return The removed item, or null if not found
     */
    public Item removeItem(String name)
    {
        for(Item item : inventory) {
            if(item.getName().equalsIgnoreCase(name)){
                inventory.remove(item);
                return item;
            }
        }
        return null;
    }
    
    /**
     * Print all items currently in the player's inventory.
     */
        public void showInventory()
    {
        if(inventory.isEmpty()) {
            System.out.println("You are not carrying anything.");
            return;
        }
        System.out.print("You are carrying: ");
        for(Item item: inventory) {
            System.out.println(" " + item.getName());
        }
        System.out.println();
    }
}