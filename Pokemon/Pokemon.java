import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

/**
 * It's a class that represents a Pokemon
 */
class Pokemon{
    private int pokenum;
    private String name;
    private String type; 
    private int hp;
    private int originalhp;
    private int attacklvl;
    private int defenselvl;
    private String status;
    private Move[] moves;
    private Path frontImagePath;
    private Path backImagePath;
    private int statuswait;

    /**
     * Setting all the values of a pokemon and setting the image paths.
     * 
     * @param pokenum poke number
     * @param name poke name
     * @param type poke type
     * @param hp poke hp
     * @param attacklvl poke attackk
     * @param defenselvl poke defense
     */
    public Pokemon(int pokenum, String name, String type, int hp, int attacklvl, int defenselvl){
        this.pokenum = pokenum;
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.originalhp = hp;
        this.attacklvl = attacklvl;
        this.defenselvl = defenselvl;
        this.status = null;
        this.moves = new Move[3];
        frontImagePath = Paths.get("front", (String.valueOf(this.pokenum) + ".png"));
        backImagePath = Paths.get("back", (String.valueOf(this.pokenum) + ".png"));
        this.statuswait = 0;
    }

    
    /**
     * The clone() method creates a new object with the same values as an existing object
     * 
     * @return A new Pokemon object with the same values as the original Pokemon object.
     */
    @Override
    public Pokemon clone(){
        return new Pokemon(pokenum, name, type, hp, attacklvl, defenselvl);
    }

    /**
     * The toString() method returns the name of the object
     * 
     * @return The name of the object.
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * It returns a string that displays the name of the pokemon, its hp, and its status
     * 
     * @return The full status of the pokemon.
     */
    public String fullStatus(){
        if (status == null){
            return String.format("<html>Pokemon: %s <br> Hp: <br> Status: %s <br> <img src=\"file:"+Paths.get("Types", type + ".png").toString()+"\" width=25 height=25><html>", name, "Normal");
        }
        else if (status.equals("Poison")){
            return String.format("<html>Pokemon: %s <br> Hp: <br> Status: <font color='green'>%s</font> <br> <img src=\"file:"+Paths.get("Types", type + ".png").toString()+"\" width=25 height=25><html>", name, status);
        }
        else if (status.equals("Confusion")){
            return String.format("<html>Pokemon: %s <br> Hp: <br> Status: <font color='purple'>%s</font> <br> <img src=\"file:"+Paths.get("Types", type + ".png").toString()+"\" width=25 height=25><html>", name, status);
        }
        else if (status.equals("Sleep")){
            return String.format("<html>Pokemon: %s <br> Hp: <br> Status: <font color='#FF00FF'>%s</font> <br> <img src=\"file:"+Paths.get("Types", type + ".png").toString()+"\" width=25 height=25><html>", name, status);
        }
        else if (status.equals("Burn")){
            return String.format("<html>Pokemon: %s <br> Hp: <br> Status: <font color='#FF5732'>%s</font> <br> <img src=\"file:"+Paths.get("Types", type + ".png").toString()+"\" width=25 height=25><html>", name, status);
        }
        else if (status.equals("Frozen")){
            return String.format("<html>Pokemon: %s <br> Hp: <br> Status: <font color='#00FFFF'>%s</font> <br> <img src=\"file:"+Paths.get("Types", type + ".png").toString()+"\" width=25 height=25><html>", name, status);
        }
        else if (status.equals("Paralysis")){
            return String.format("<html>Pokemon: %s <br> Hp: <br> Status: <font color='#D7D733'>%s</font> <br> <img src=\"file:"+Paths.get("Types", type + ".png").toString()+"\" width=25 height=25><html>", name, status);
        }
        else{
            return String.format("<html>Pokemon: %s <br> Hp: <br> Status: %s <br> <img src=\"file:"+Paths.get("Types", type + ".png").toString()+"\" width=25 height=25><html>", name, status);
        }
    }

    /**
     * It generates three random moves for the Pokemon to use
     */
    public void genMoves(){
        for(int x = 0; x < 3; x++){
            int randomNum = ThreadLocalRandom.current().nextInt(0, 165);
            moves[x] = PokemonGame.moves.get(randomNum);
        }
    }

    /**
     * This function returns the pokenum of the Pokemon object
     * 
     * @return The pokenum is being returned.
     */
    public int getPokenum(){
        return pokenum;
    }

    /**
     * This function sets the pokenum variable to the value of the pokenum parameter
     * 
     * @param pokenum The number of the Pokemon in the Pokedex.
     */
    public void setPokenum(int pokenum){
        this.pokenum = pokenum;
    }

    /**
     * This function returns the name of the person
     * 
     * @return The name of the person
     */
    public String getName(){
        return name;
    }

    /**
     * This function sets the name of the object to the name passed in as a parameter
     * 
     * @param name The name of the parameter.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * This function returns the type of the object
     * 
     * @return The type of the object.
     */
    public String getType(){
        return type;
    }

    /**
     * This function sets the type of the object to the type passed in as a parameter
     * 
     * @param type The type of the object.
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * This function returns the value of the variable hp
     * 
     * @return The hp variable is being returned.
     */
    public int getHp(){
        return hp;
    }

    /**
     * This function sets the hp of the player to the hp passed in
     * 
     * @param hp The amount of health the player has.
     */
    public void setHp(int hp){
        this.hp = hp;
    }

    /**
     * This function returns the attacklvl variable
     * 
     * @return The attacklvl
     */
    public int getAttacklvl(){
        return attacklvl;
    }

    /**
     * This function sets the attack level of the player
     * 
     * @param attack The attack level of the player
     */
    public void setAttacklvl(int attack){
        this.attacklvl = attack;
    }

    /**
     * This function returns the value of the variable defenselvl
     * 
     * @return The value of the variable defenselvl.
     */
    public int getDefenselvl(){
        return defenselvl;
    }

    /**
     * This function sets the defense level of the player
     * 
     * @param defense The defense level of the player
     */
    public void setDefenselvl(int defense){
        this.defenselvl = defense;
    }

    /**
     * If the status is not null, return the status, otherwise return "Normal Status"
     * 
     * @return The status of the object.
     */
    public String getStatus(){
        if (status != null){
            return status;
        }
        else{
            return "Normal Status";
        }
    }

    /**
     * This function sets the status of the current object to the status passed in as a parameter
     * 
     * @param status The status of the job.
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * This function returns the moves array
     * 
     * @return The moves array.
     */
    public Move[] getMoves() {
        return moves;
    }

    /**
     * This function sets the moves of the Pokemon object to the moves passed in as a parameter
     * 
     * @param moves The moves that the Pokemon can learn.
     */
    public void setMoves(Move[] moves) {
        this.moves = moves;
    }

    /**
     * This function returns the path of the front image
     * 
     * @return The path of the front image.
     */
    public Path getFrontImagePath() {
        return frontImagePath;
    }

    /**
     * This function sets the frontImagePath variable to the value of the frontImagePath parameter
     * 
     * @param frontImagePath The path to the front image
     */
    public void setFrontImagePath(Path frontImagePath) {
        this.frontImagePath = frontImagePath;
    }

    /**
     * This function returns the path of the back image
     * 
     * @return The path of the back image.
     */
    public Path getBackImagePath() {
        return backImagePath;
    }

    /**
     * This function sets the backImagePath to the path of the image that the user wants to use as the
     * back of the card
     * 
     * @param backImagePath The path to the image that will be used as the background.
     */
    public void setBackImagePath(Path backImagePath) {
        this.backImagePath = backImagePath;
    }

    /**
     * This function returns the originalhp variable
     * 
     * @return The originalhp variable is being returned.
     */
    public int getOriginalhp() {
        return originalhp;
    }

    /**
     * This function sets the originalhp variable to the value of the parameter originalhp
     * 
     * @param originalhp The original health of the player.
     */
    public void setOriginalhp(int originalhp) {
        this.originalhp = originalhp;
    }

    /**
     * This function returns the statuswait variable
     * 
     * @return The statuswait variable is being returned.
     */
    public int getStatuswait() {
        return statuswait;
    }

    /**
     * This function sets the statuswait variable to the value of the parameter statuswait
     * 
     * @param statuswait 0 = not waiting, 1 = waiting
     */
    public void setStatuswait(int statuswait) {
        this.statuswait = statuswait;
    }
}