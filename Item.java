
/**
 * The Item class represents objects that can be found in rooms
 * and carried by the player. Each item has a name, description,
 * and weight.
 *
 * @author Joseph Schiavone
 * @version 2026.04.12
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private int weight;

    /**
     * Create a new item.
     * 
     * @param name The name of the item
     * @param description A short description of the item
     * @param weight The weight of the item
     */
    public Item(String name, String description, int weight)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Get the name of the item.
     * @return The item's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the description of the item.
     * @return The item's description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Get the weight of the item.
     * @return The item's weight
     */
    public int getWeight()
    {
        return weight;
    }
    
    public String toString()
    {
        return name + ": " + description + " (weight: " + weight + ")";
    }
}