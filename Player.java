import java.util.ArrayList;
/**
 * This class Represents the player and their inventory
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private ArrayList<Item> inventory;
    
    public Player()
    {
        inventory = new ArrayList<Item>();
    }
    
    public void addItem(Item item)
    {
        inventory.add(item);
    }
    
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