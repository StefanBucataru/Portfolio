import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;

/**
 * Get all the information needed for the game and starts it.
 */
class PokemonGame {

    public static Player[] players;
    public static ArrayList<Move> moves;
    public static ArrayList<Item> items;
    public static ArrayList<Pokemon> pokemons;
    public static HashMap<String, HashMap<String, Double>> multi;
    public static ArrayList<String> types;
    public static ArrayList<Status> statuses;
    public static Pokemon p1_pokemon_out;
    public static Pokemon p2_pokemon_out;
    public static GUI window;

    /**
     * This function reads the pokemon.csv file and creates a new Pokemon for pokemon in the
     * file
     * 
     * @return An ArrayList of Pokemon objects.
     */
    private static ArrayList<Pokemon> getPokemons(){
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        try {
            //Looping through file and reading each line
            Scanner filein = new Scanner(new File("pokemon.csv"));
            while(filein.hasNextLine()){
                String[] poke = filein.nextLine().split(",");
                int pokenum = Integer.valueOf(poke[0]);
                String name  = poke[1];
                String type = poke[2];
                int hp = Integer.valueOf(poke[3]);
                int atk = Integer.valueOf(poke[4]);
                int def = Integer.valueOf(poke[5]);

                pokemons.add(new Pokemon(pokenum, name, type, hp, atk, def));

            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!");
        }

        return pokemons;
    }

    /**
     * Reads a csv file and creates an arraylist of statuses.
     * 
     * @return An ArrayList of Status objects.
     */
    private static ArrayList<Status> getStatuses(){
        ArrayList<Status> statuses = new ArrayList<>();

        try {
            //Looping through file and reading each line
            Scanner filein = new Scanner(new File("special_status_pokemon.csv"));
            while(filein.hasNextLine()){
                String[] Status = filein.nextLine().split(",");
                String move = Status[0];
                String effect  = Status[1];

                statuses.add(new Status(move, effect));

            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!");
        }

        return statuses;
    }

    /**
     * It reads a CSV file and creates a new Move for each move in the file.
     * 
     * @return An ArrayList of Move objects.
     */
    private static ArrayList<Move> getMoves(){
        ArrayList<Move> moves = new ArrayList<>();

        try {
            //Looping through file and reading each line
            Scanner filein = new Scanner(new File("moves.csv"));
            while(filein.hasNextLine()){
                String[] move = filein.nextLine().split(",");
                String name = move[0];
                String type  = move[1];
                int power = Integer.valueOf(move[2]);

                moves.add(new Move(name, type, power));

            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!");
        }
        
        return moves;
    }

    /**
     * It returns an ArrayList of Strings that contains all the types of Pokemon
     * 
     * @return An ArrayList of Strings
     */
    private static ArrayList<String> getTypes(){
        ArrayList<String> types = new ArrayList<>();

        types.add("Normal");
        types.add("Fighting");
        types.add("Flying");
        types.add("Poison");
        types.add("Ground");
        types.add("Rock");
        types.add("Bug");
        types.add("Ghost");
        types.add("Steel");
        types.add("Fire");
        types.add("Water");
        types.add("Grass");
        types.add("Electric");
        types.add("Psychic");
        types.add("Ice");
        types.add("Dragon");
        types.add("Dark");
        types.add("Fairy");
        
        return types;
    }

    /**
     * Reads a csv file and creates Hashmap of the different attack multipliers
     * 
     * @return An ArrayList of Multiplier objects.
     */
    private static HashMap<String, HashMap<String, Double>> getMultis(){
        HashMap<String, HashMap<String, Double>> multipliers = new HashMap<>();
        HashMap<String, Double> multi = new HashMap<>();
        ArrayList<String> types = getTypes();

        try {
            //Looping through file and reading each line
            Scanner filein = new Scanner(new File("multiplier.csv"));
            while(filein.hasNextLine()){
                String[] multies = filein.nextLine().split(",");
                String attacktype = multies[0];
                for(int x = 1; x < 19; x++){
                    multi.put(types.get(x-1), Double.valueOf(multies[x]));
                }

                multipliers.put(attacktype, (HashMap<String, Double>)multi.clone());
                multi.clear();
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!");
        }

        return multipliers;
    }

    /**
     * Reads a csv file and creates an arraylist of items
     * 
     * @return An ArrayList of Item objects.
     */
    private static ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();

        try {
            //Looping through file and reading each line
            Scanner filein = new Scanner(new File("items.csv"));
            while(filein.hasNextLine()){
                String[] move = filein.nextLine().split(",");
                String name = move[0];
                String effect  = move[1];

                items.add(new Item(name, effect));

            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!");
        }
        
        return items;
    }

    /**
     * It creates two players, each with a bag and pokemons
     * 
     * @param pokemons ArrayList of Pokemon objects
     * @return The players array.
     */
    private static Player[] genPlayers(ArrayList<Pokemon> pokemons){
        players = new Player[2];

        players[0] = new Player("P1");
        players[1] = new Player("P2");

        //Generating the bag and pokemon for each player
        Arrays.asList(players).forEach((player) -> {player.genBag(); player.genPokemon();});

        return players;
    }

    /**
     * It calculates the damage of an attack based on the attack's power, the attacker's attack level,
     * the defender's defense level, the attack's type, and the defender's type
     * 
     * @param attack Move object
     * @param Attacker Pokemon object
     * @param Defender The Pokemon that is being attacked
     * @return The damage that the attack will do to the defender.
     */
    private static int damagecalc(Move attack, Pokemon Attacker, Pokemon Defender){
        Double damagecalc;
        long damage_rounded;

        damagecalc = (2 * attack.getPower() * (((Double.valueOf(Attacker.getAttacklvl())/Double.valueOf(Defender.getDefenselvl()))/50.0)) + 2) * multi.get(attack.getType()).get(Defender.getType()) * 5;
        damage_rounded = Math.round(damagecalc);

        return (int) damage_rounded;
    }

    /**
     * It sets the status of the defender to the status of the move used by the attacker
     * if the move has a status effect
     *   
     * @param attacker The Pokemon that is attacking
     * @param defender The Pokemon that is being attacked
     * @param move the move that the attacker is using
     */
    private static void setStatus(Pokemon attacker, Pokemon defender, int move){
        if (attacker.getMoves()[move].getName().matches("Confusion|Confuse Ray|Dizzy Punch|Psybeam|Supersonic")){
            defender.setStatus("Confusion");
        }
        else if (attacker.getMoves()[move].getName().matches("Poisoned Gas|Poisoned Powder|Poisoned Sting")){
            defender.setStatus("Poison");
        }
        else if (attacker.getMoves()[move].getName().matches("Sleeping Powder|Spore|Rest")){
            defender.setStatus("Sleep");
        }
        else if (attacker.getMoves()[move].getName().matches("Ember|Fire Blast|Fire Punch|Flamethrower")){
            defender.setStatus("Burn");
        }
        else if (attacker.getMoves()[move].getName().matches("Blizzard|Ice Beam")){
            defender.setStatus("Frozen");
        }
        else if (attacker.getMoves()[move].getName().matches("Thunder Shock|Body Slam")){
            defender.setStatus("Paralysis");
        }
    }

    /**
     * It calculates the damage of a attack and then attacks the pokemon
     * 
     * @param attacker Pokemon attacking
     * @param defender The pokemon that is being attacked
     * @param move the move that the pokemon is using
     * @param player 0 for player 1, 1 for player 2
     */
    public static void attack(Pokemon attacker, Pokemon defender, int move, int player){
        int damage = damagecalc(attacker.getMoves()[move], attacker, defender);

        //Checking if pokemon has died
        if (defender.getHp() - damage <= 0){

            //If the attacking pokemon is the players pokemon checks if game has ended, it not forces ai switch
            if (attacker.equals(p1_pokemon_out)){
                p2_pokemon_out.setHp(0);
                Pokemon temp = p2_pokemon_out;
                try{
                p2_pokemon_out = players[1].getPokemons().get(0);
                players[1].getPokemons().remove(0);
                } catch(IndexOutOfBoundsException e) {
                    PokemonGame.end();
                }
                PokemonGame.end();
                players[1].setPokemonOut(p2_pokemon_out);

                ComPanel ComMenu = new ComPanel();
                ComMenu.setup();
                ComMenu.setMessageDeath(temp, p2_pokemon_out);
                GUI.panels.add(ComMenu, "ComMenu");
                GUI.c.show(GUI.panels, "ComMenu");
            }
            //If the attacking pokemon is the ai's checking if game is ended else forcing a poke switch
            else if (attacker.equals(p2_pokemon_out)){
                p1_pokemon_out.setHp(0);
                ComPanel ComMenu = new ComPanel();
                ComMenu.setup();
                ComMenu.setMessage(attacker.getMoves()[move], attacker, damage);
                GUI.panels.add(ComMenu, "ComMenu");
                GUI.c.show(GUI.panels, "ComMenu");
                PokemonGame.end();
            }
        }
        else{
            //setting the status depending on the attack
            setStatus(attacker, defender, move);

            //setting Hp
            defender.setHp(defender.getHp() - damage);

            //Displaying menu
            ComPanel ComMenu = new ComPanel();
            ComMenu.setup();
            ComMenu.setMessage(attacker.getMoves()[move], attacker, damage);
            GUI.panels.add(ComMenu, "ComMenu");
            GUI.c.show(GUI.panels, "ComMenu");
        }
    }

    /**
     * It swaps the Pokemon in the player's party with the Pokemon in the player's hand
     * 
     * @param PokemonIn The index of the Pokemon in the player's Pokemon arraylist
     */
    public static void swapPokePlayer(int PokemonIn){
        //swapping the pokemon
        Pokemon temp = p1_pokemon_out;
        p1_pokemon_out = players[0].getPokemons().get(PokemonIn);
        players[0].getPokemons().set(PokemonIn, temp);

        //Displaying menu
        ComPanel ComMenu = new ComPanel();
        ComMenu.setup();
        ComMenu.setMessage(temp, players[0].getPokemons().get(PokemonIn));
        GUI.panels.add(ComMenu, "ComMenu");
        GUI.c.show(GUI.panels, "ComMenu");
    }

    /**
     * Uses the item a player selects
     * 
     * @param itemnum the index of the item in the bag
     */
    public static void useItem(int itemnum){
        //Using the button
        players[0].getBag()[itemnum].setUsed(true);
        p1_pokemon_out.setStatus(null);

        //Showing menu
        ComPanel ComMenu = new ComPanel();
        ComMenu.setup();
        ComMenu.setMessage(players[0].getBag()[itemnum], p1_pokemon_out);
        GUI.panels.add(ComMenu, "ComMenu");
        GUI.c.show(GUI.panels, "ComMenu");
    }

    /**
     * Making the Ai take turn
     */
    public static void AiTurn(){
        boolean check = false;
        int x = 0;
        Item Item = null;

        //Checing if ai can use an item
        for (Item item : Arrays.asList(players[1].getBag())){
            if (item != null && item.getEffect() != null){
                if (item.getEffect().equals(p2_pokemon_out.getStatus()) || (item.getEffect().equals("All") && !p2_pokemon_out.getStatus().equals("Normal Status"))){
                    check = true;
                    Item = item;
                    players[1].getBag()[x] = new Item(null, null);
                    break;
                }
                x++;
            }
        }

        //If ai can use an item, using the item
        if (check){
            p2_pokemon_out.setStatus(null);
            ComPanel ComMenu = new ComPanel();
            ComMenu.setup(); ComMenu.setMessage(Item.getName(), p2_pokemon_out);
            GUI.panels.add(ComMenu, "ComMenu"); GUI.c.show(GUI.panels, "ComMenu");
        }

        //Checking if ai needs to pass and passing if needed
        else if (!check && !p2_pokemon_out.getStatus().equals("Normal Status")){
            ComPanel ComMenu = new ComPanel();
            ComMenu.setup(); ComMenu.setPassMessage("Computer");
            GUI.panels.add(ComMenu, "ComMenu"); GUI.c.show(GUI.panels, "ComMenu");

            //Adding to the amount of turns passed
            if (p2_pokemon_out.getStatuswait() + 1 >= 3){
                p2_pokemon_out.setStatus(null);
                p2_pokemon_out.setStatuswait(0);
            }
            else{
                p2_pokemon_out.setStatuswait(p2_pokemon_out.getStatuswait()+1);
            }
        }
        else{
            //Using a random attack
            int ran = ThreadLocalRandom.current().nextInt(0, 3);
            attack(p2_pokemon_out, p1_pokemon_out, ran, 1);
        }
    }

    /**
     * If the player 2's pokemon is dead and player 2 has no more pokemon, then player 1 wins. If
     * player 1's pokemon is dead and the player 1 has no more pokemon, then player 2 wins
     * 
     * @return The winner of the game.
     */
    private static String checkend(){
        if (Arrays.asList(players[1].getPokemons()).size()-1 == 0 && p2_pokemon_out.getHp() == 0){
            return "p1";
        }
        for (Pokemon poke : players[0].getPokemons()) {
            if (poke.getHp() != 0){
                return "";
            }
      }

        return "p2";
    }

    /**
     * It checks for the winner and then displays the end.
     */
    public static void end(){
        String winner = checkend();

        if (winner.equals("p1")){
            JPanel EndPanel = new EndPanel("You");
            GUI.panels.add(EndPanel, "EndMenu");
            GUI.c.show(GUI.panels, "EndMenu");
        }
        else if (winner.equals("p2")){
            JPanel EndPanel = new EndPanel("Computer");
            GUI.panels.add(EndPanel, "EndMenu");
            GUI.c.show(GUI.panels, "EndMenu");
        }
    }

    /**
     * Starts the game
     */
    public static void main(String[] args){
        multi = getMultis();
        types = getTypes();
        moves = getMoves();
        pokemons = getPokemons();
        statuses = getStatuses();
        items = getItems();
        players = genPlayers(pokemons);

        p1_pokemon_out = players[0].getPokemonOut();
        p2_pokemon_out = players[1].getPokemonOut();

        window = new GUI();
    }
}