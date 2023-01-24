/**
 * It's a class that inherits the Thread class and has a run function that loops through minutes and
 * seconds and increases the seconds by 1 every 1000 miliseconds.
 */
class Stopwatch extends Thread{
    //Initializing the seconds and minutes variables
    private int seconds = 0;
    private int minutes = 0;


    @Override
    /**
     * It's a function that is called when the thread is started.
     */
    public void run(){
        this.seconds = 0;
        this.minutes = 0;

        //Looping through min and then seconds
        for (int minute = 0; minute <= 60; minute++){
            for (int second = 0; second <= 60; second++){
                //Increasing seconds by ever 1000 miliseconds
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                //Setting the seconds class property to the seconds
                seconds = second;
            }
            //Setting the minutes class property to the minute
            minutes = minute;
        }
            
    }

    /**
     * This function returns the seconds of the time object
     * 
     * @return The seconds as an int
     */
    public int getSecond(){
        //Returning the seconds
        return this.seconds;
    }

    /**
     * This function returns the minutes of the time object
     * 
     * @return The minutes as an int
     */
    public int getMinute(){
        //Returning the minutes
        return this.minutes;
    }

}
