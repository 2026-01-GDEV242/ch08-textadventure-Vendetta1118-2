
/**
 * Represents an item in the game
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
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Get the name of an item
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the description of an item
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Get the weight of an item
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