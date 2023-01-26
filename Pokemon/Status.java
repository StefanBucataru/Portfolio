/**
 * It's a class that holds a move and an effect
 */
class Status {
    private String move;
    private String effect;

    /**
     * Creates the cause move and effect given
     * 
     * @param move cause
     * @param effect effect
     */
    public Status(String move, String effect){
        this.move = move;
        this.effect = effect;
    }

    /**
     * This function returns the move of the player
     * 
     * @return The move variable is being returned.
     */
    public String getMove(){
        return move;
    }

    /**
     * This function sets the move of the player to the move that is passed in
     * 
     * @param move The move that the player is making.
     */
    public void setMove(String move){
        this.move = move;
    }

    /**
     * This function returns the effect of the card
     * 
     * @return The effect of the potion.
     */
    public String getEffect(){
        return effect;
    }

    /**
     * This function sets the effect of the card
     * 
     * @param effect The effect to use.
     */
    public void setEffect(String effect){
        this.effect = effect;
    }


    /**
     * This function returns a string that is the move and the effect of the move
     * 
     * @return The move and the effect of the move.
     */
    @Override
    public String toString(){
        return move + ": " + effect;
    }
}
