import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * An item in the game
 */
class Item {
    private String name;
    private String effect;
    private boolean used;
    private Path image;
    
    /**
     * Takes in two strings, one for the name of the item and one for the effect of the item.
     * 
     * @param name move name
     * @param effect move effect
     */
    public Item(String name, String effect){
        this.name = name;
        this.effect = effect;
        this.used = false;
        this.image = Paths.get("Item Icons", name + ".png");
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
     * This function returns the effect of the card
     * 
     * @return The effect of the potion.
     */
    public String getEffect() {
        return effect;
    }

    /**
     * This function sets the effect of the card
     * 
     * @param effect The effect to use. Currently only "fade" is supported.
     */
    public void setEffect(String effect) {
        this.effect = effect;
    }

    /**
     * This function returns a boolean value that indicates whether the object is used or not
     * 
     * @return The boolean value of used.
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * This function sets the value of the used variable to the value of the used parameter
     * 
     * @param used This is a boolean value that indicates whether the object is in use or not.
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    
    /**
     * This function returns a new Item object with the same name and effect as the Item object that
     * called the function.
     * 
     * @return A new Item object with the same name and effect as the original Item object.
     */
    @Override
    public Item clone(){
        return new Item(name, effect);
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
     * This function returns the image of the card
     * 
     * @return The image path.
     */
    public Path getImage() {
        return image;
    }

    /**
     * This function sets the image of the current object to the image passed in as a parameter
     * 
     * @param image The path to the image file.
     */
    public void setImage(Path image) {
        this.image = image;
    }
}
