
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Joseph Schiavone
 * @version 2026.04.10
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room foyer, dining, library, basement, lab, bedroom, hallway, safeRoom, kitchen;
      
        // create the rooms
        foyer = new Room("in the mansion foyer");
        dining = new Room("in a dusty dining room");
        library = new Room("in a dark library filled with books");
        basement = new Room("in a creepy basement");
        lab = new Room("in a hidden laboratory");
        bedroom = new Room("in an abandoned bedroom");
        hallway = new Room("in a long hallway");
        safeRoom = new Room("in a safe room with a typewriter");
        kitchen = new Room("in a slime ridden kitchen");
        // initialise room exits
        foyer.setExit("north", hallway);
        foyer.setExit("east", dining);
        
        dining.setExit("west", foyer);
        dining.setExit("north", kitchen);
        
        hallway.setExit("south", foyer);
        hallway.setExit("east", library);
        hallway.setExit("west", bedroom);
        
        library.setExit("west", hallway);
        library.setExit("down", basement);
        
        basement.setExit("up", library);
        basement.setExit("east", lab);
        
        lab.setExit("west", basement);
        
        bedroom.setExit("east", hallway);
        bedroom.setExit("north", safeRoom);
        
        safeRoom.setExit("south", bedroom);
        
        kitchen.setExit("south",dining);
        
        //Inizialized items in each room
        bedroom.addItem(new Item("key", "A rusty old key", 1));
        
        library.addItem(new Item("books", "a stack of dusty old books", 5));
        library.addItem(new Item("lamp", "an old oil lamp, still slightly warm", 3));
        
        kitchen.addItem(new Item("goo", "an unkown gooey substance... it smells awful", 4));
        
        lab.addItem(new Item("test_tubes", "strange test tubes filled with glowing liquid", 2));
        currentRoom = foyer;  // start game in foyer
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Mansion...");
        System.out.println("Something feels wrong. The air is cold.");
        System.out.println("Find a way out...if you can.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK: 
                look();
                break;
                
            case TAKE:
                takeItem(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You are trapped inside a mansion.");
        System.out.println("Explore rooms and try to survive.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    /**
     * Handles the look command
     * Reprints the current room description
     */
    private void look() 
    {
       System.out.println(currentRoom.getLongDescription()); 
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Allows an item to be taken in a room
     */
    private void takeItem(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = currentRoom.getItem(itemName);
        if(item == null){
            System.out.println("There is no " + itemName + " here.");
        }
        else {
            player.addItem(item);
            currentRoom.removeItem(item);
            System.out.println("You picked up the " + itemName + ".");
        }
    }
    
    /**
     * Main method to run the game outside BlueJ.
     */
    public static void main(String[] args) 
    {
        Game game = new Game();
        game.play();
    }
}
