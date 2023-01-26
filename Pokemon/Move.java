/**
 * A move.
 */
class Move {

    private String name;
    private String type;
    private int Power;

    /**
     * Creates a move and sets the move name, type and power
     * 
     * @param name move name
     * @param type move type
     * @param Power move power
     */
    public Move(String name, String type, int Power){
        this.name = name;
        this.type = type;
        this.Power = Power;
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
     * This function returns the type of the current object
     * 
     * @return The type of the object.
     */
    public String getType(){
        return type;
    }

    /**
     * This function sets the type of the object
     * 
     * @param type The type of the object.
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * This function returns the value of the variable Power
     * 
     * @return The power of the car.
     */
    public int getPower(){
        return Power;
    }

    /**
     * This function sets the power of the car
     * 
     * @param power The power of the engine.
     */
    public void setPower(int power){
        Power = power;
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
}
