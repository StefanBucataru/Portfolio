import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Player class is used to create a player object that contains the player's name, a list of
 * Pokemon, an array of items, and the Pokemon that is currently out
 */
class Player {
    private String name;
    private ArrayList<Pokemon> pokemons;
    private Item[] bag;
    private Pokemon pokemonOut;

    /**
     * Creating a new player object with a name, an empty list of pokemons, and an empty bag.
     * 
     * @param name player name
     */
    public Player(String name){
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.bag = new Item[3];
    }

    /**
     * It generates 3 random items from the items list and puts them in the bag
     */
    public void genBag(){
        for(int x = 0; x < 3; x++){
            int randomNum = ThreadLocalRandom.current().nextInt(1, 7);
            Item item = PokemonGame.items.get(randomNum);
            bag[x] = item.clone();
        }
    }

    /**
     * It generates 4 random pokemon from the list of 151 pokemon, and then generates 3 random moves
     * for each pokemon
     */
    public void genPokemon(){
        boolean check = false;

        //Starting for look of 4
        for(int i = 0; i < 4; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(1, 152);
            
            //Checking if player already has the pokemon generated
            for(Pokemon poke : pokemons){
                if(poke != null){
                    //If player already has the pokemon generating another one
                    if (poke.getPokenum() == randomNum){
                        i--;
                        check = true;
                        break;
                    }
                }
            }

            //Adding pokemon to players pokemon if player doesn't have pokemon
            if (check == false){
                for(Pokemon poke : PokemonGame.pokemons){
                    if (poke.getPokenum() == randomNum){
                       pokemons.add(poke.clone());
                        break;
                    }
                }
            }
            else{
                check = false;
            }
        }

        //Generating moves for each pokemon
        pokemons.forEach((pokemon) -> {pokemon.genMoves();});
        this.pokemonOut = pokemons.get(0);
        pokemons.remove(pokemonOut);
    }

    /**
     * This function returns the name of the person
     * 
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * This function sets the name of the object to the name passed in as a parameter
     * 
     * @param name The name of the parameter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function returns the bag array
     * 
     * @return The bag array.
     */
    public Item[] getBag() {
        return bag;
    }

    /**
     * This function sets the bag of the player to the bag that is passed in
     * 
     * @param bag The bag of the player.
     */
    public void setBag(Item[] bag) {
        this.bag = bag;
    }

    /**
     * Returning ArrayList of pokemon
     * 
     * @return The ArrayList pokemons is being returned.
     */
    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * This function sets the pokemons variable to the value of the pokemons parameter
     * 
     * @param pokemons The list of Pokemon objects that the user has.
     */
    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    /**
     * This function returns the pokemonOut variable
     * 
     * @return The pokemonOut variable is being returned.
     */
    public Pokemon getPokemonOut() {
        return pokemonOut;
    }

    /**
     * This function sets the pokemonOut variable to the pokemonOut parameter
     * 
     * @param pokemonOut The Pokemon that is being sent out.
     */
    public void setPokemonOut(Pokemon pokemonOut) {
        this.pokemonOut = pokemonOut;
    }
}
