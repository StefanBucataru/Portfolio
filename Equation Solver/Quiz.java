//Importing required libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * It's a class that generates random linear equations and asks the user to solve them.
 */
class Quiz {

    //Initializing the input scanner, the amount of questions they did and how many they got correct
    private static int total = 0;
    private static int totalcorrect = 0;
    private static Scanner input = new Scanner(System.in);
    private static int lastmin = 0;
    private static int lastsec = 0;

    /** 
     * This starts the Quiz and asks the user to pick a type of question,
     * it also verifies the input and then gives them their score.
     * @param timer Takes in a stopwatch.
     */
    public Quiz(Stopwatch timer){
        //Initalizing the equation variable
        String equation = "";

        //Haveing the user select a type of quesiton and then using a method to check their input is a valid option
        System.out.println("\033[0;33mSelect a option using the according number, keep answer as impropper(enter \033[0;36mQUIT\033[0m \033[0;33mto exit the program):\n\033[0;35m1.\033[0m\033[0;33mOne Step\n\033[0;35m2.\033[0m\033[0;33mTwo Step\n\033[0;35m3.\033[0m\033[0;33mMulti-Step\033[0m");
        String option = validoptioninput(timer);

        //Starting a loop so that they are able to play the game multiple times until
        //they wish to quit
        while(!option.equals("QUIT")) {
            //Incrementing the questions they did
            total++;

            //if the user picked the first option then a one step equation is displayed
            if (option.matches("1")) {

                //Generating the one step equation
                equation = RandomOneStep();

                //Printing out the equation
                System.out.println("\033[0;36m" + equation + "\033[0m");
                System.out.println("\033[0;36mx = \033[0m");

                //Checking if the user input is valid
                option = validanswerinput();
            }
            else if (option.matches("2")) {

                //Generating a two step eqation
                equation = RandomTwoStep();

                //Printing out the equation
                System.out.println("\033[0;36m" + equation + "\033[0m");
                System.out.println("\033[0;36mx = \033[0m");

                //Checking if the user input is valid
                option = validanswerinput();

                
            }
            else if (option.matches("3")) {

                //Generating the multi step equation
                equation = RandomMultiStep();

                //Printing out the equation
                System.out.println("\033[0;36m" + equation + "\033[0m");
                System.out.println("\033[0;36mx = \033[0m");

                //Checking if the user input is valid
                option = validanswerinput();
            }

            //Checking if the answer is correct
            if (answerChecker(option, equation, timer)) {
                //Increasing the amount of correct answers
                totalcorrect++;
            } 

            //Printing more options for the user after they have answered
            System.out.println("\033[0;33mSelect a option to continue or enter \033[0;36mQUIT\033[0m \033[0;33mto see your result:\n\033[0;35m1.\033[0m\033[0;33mOne Step\n\033[0;35m2.\033[0m\033[0;33mTwo Step\n\033[0;35m3.\033[0m\033[0;33mMulti-Step\033[0m");
            
            //Validating the next set of input
            option = validoptioninput(timer);
        }
        //Printing out the users result
        System.out.printf("\033[0;33mYou got \033[0;32m%d/%d\033[0m \033[0;33mcorrect and spent a total of %d minute(s) and %d second(s).\033[0m", totalcorrect, total, lastmin, lastsec);
    }

    /**
     * Initializing input so that it can be used for other methods.
     * 
     * @return The input from the user a string.
     */
    private static String input() {
        //Getting the user input
        String userinput = input.nextLine();

        //Returnign the user input
        return userinput;
    }

    /**
     * This helper method takes a string input from the user, and if the input is not a number between 1 and 3, or the
     * word "QUIT", it will ask the user to enter a valid input.
     * 
     * @return The user input a string is being returned.
     */
    private static String validoptioninput(Stopwatch timer){
        //Initializing the answer string
        String answer = "";

        //Validating input and asking them for a correct input
        while (!answer.matches("[1-3]") && !answer.matches("QUIT")) {

            //Getting input
            answer = input();

            //Asking user for correct input
            if (!answer.matches("[1-3]") && !answer.matches("QUIT")) {
                System.out.println("\033[0;31mEnter a valid input!\033[0m");
            }
        }
        //Setting the last second and imute to when option is picked
        lastsec = timer.getSecond();
        lastmin = timer.getMinute();

        //Returning the users answer
        return answer;
    }

    /**
     * This helper method takes a string input from the user, and if the input is not a valid answer, it will ask the
     * user to input a valid answer.
     * 
     * @return The user input a string is being returned.
     */
    private static String validanswerinput(){
        //Initializing the answer string
        String answer = "";

        //Validating that their input is a number or fraction and asking them for a correct input if it isn't
        while (!answer.matches("-?[1-9][0-9]*") && !answer.matches("-?[1-9][0-9]*/-?[1-9][0-9]*")){
            //Getting input
            answer = input();

            //Checking if answer is QUIT and then quitting the program
            if (answer.equals("QUIT")){
                System.out.printf("\033[0;33mYou got \033[0;32m%d/%d\033[0m \033[0;33mcorrect and spent a total of %d minute(s) and %d second(s).\033[0m", totalcorrect, total, lastmin, lastsec);
                System.exit(0);
            }
            //Asking user for correct input
            else if (!answer.matches("-?[1-9][0-9]*") && !answer.matches("-?[1-9][0-9]*/-?[1-9][0-9]*")) {
                System.out.println("\033[0;31mEnter a valid input!\033[0m");
            }
        }
        //Returning the users answer
        return answer;
    }

    /**
     * This helper method generates a random number between -20 and 20.
     * 
     * @return A random int between -20 and 20 excluding 0.
     */
    private static int randomNumberGenerator() {
        //Initializing a new random object
        Random rand = new Random();
        
        //Generating a random number between 0 and 1
        int numberType = rand.nextInt(2);
        
        //If the number previously generated is 0 then generating another number between -99 and -1 and returning it
        if (numberType == 0) {
            return rand.nextInt(-20, 0);
        }

        //If the number previously generated is 1 then generating another number between 1 and 99 and returning it
        else {
            return rand.nextInt(1, 21);
        }
    }

    /**
     * This helper method generates a random sign (+ or -) and returns it.
     * 
     * @return A random string (+ or -)
     */
    private static String RandomSignGenerator() {
        //Initializing a new random object
        Random rand = new Random();

        //Generating a random number between 0 and 1
        int numberType = rand.nextInt(2);

        //If the number previously generated is 0 then returning +
        if (numberType == 0) {
            return "+";
        }
        //If the number previously generated is 1 then returning +
        else {
            return "-";
        }
    }

    /**
     * This helper method takes a user's answer and the equation that the user is solving, and returns true if the
     * user's answer is correct, and false if the user's answer is incorrect.
     * 
     * @param useranswer The user's answer
     * @param equation The equation to be solved
     * @return A boolean value, if the user is correct or not.
     */
    private static Boolean answerChecker(String useranswer, String equation, Stopwatch timer){
        //Initializing the fraction variable
        Fraction answer = new Fraction(0);

        //Checking if the user answer is a fraction and calling appropriate fraction constructor
        if (useranswer.contains("/")){
            answer = new Fraction(Integer.valueOf(useranswer.substring(0, useranswer.indexOf("/"))), Integer.valueOf(useranswer.substring(useranswer.indexOf("/")+1)));
        }
        else {
            answer = new Fraction(Integer.valueOf(useranswer));
        }
        

        //Getting the correct answer using the equation solver
        Fraction correctanswer = EquationSolver.solve(new EquationSolver(equation));

        //If the answer is correct then returning true
        if (answer.equals(correctanswer)){

            //Checking if the second displayed would be negative and then fixing it if it would be
            if (timer.getSecond() - lastsec < 0){
                lastsec = (lastsec - 60);
            }
            System.out.printf("\n\033[0;32mCorrect! You spent %d minute(s) and %d second(s) on that question!\033[0m\n", timer.getMinute() - lastmin, timer.getSecond() - lastsec);
            
            //Setting the last time the timer and the second were used
            lastmin = timer.getMinute();
            lastsec = timer.getSecond();
            return true;
        }
        //3-54
        //If the answer is incorrect then returning true
        else {
            //Checking if the second displayed would be negative and then fixing it if it would be
            if (timer.getSecond() - lastsec < 0){
                lastsec = (lastsec - 60);
            }

            System.out.printf("\n\033[0;31mIncorrect! The correct answer is %s. You spent %d minute(s) and %d second(s) on that question!\033[0m\n", correctanswer.unsimplify().toString(), timer.getMinute() - lastmin, timer.getSecond() - lastsec);
            
            //Setting the last time the timer and the second were used
            lastmin = timer.getMinute();
            lastsec = timer.getSecond();
            return false;
        }
    }

    /**
     * It makes random one step questions, shuffles them, and returns the first one.
     * 
     * @return The first question from the ArrayList a string.
     */
    public static String RandomOneStep(){

        //Initializing the arraylist
        ArrayList<String> possiblequestions = new ArrayList<String>();

        //Making 3 different possible one step questions
        possiblequestions.add(String.format("x %s %d = %d", RandomSignGenerator(), randomNumberGenerator(), randomNumberGenerator()));
        possiblequestions.add(String.format("%dx = %d", randomNumberGenerator(), randomNumberGenerator()));
        possiblequestions.add(String.format("x/%d = %d", randomNumberGenerator(), randomNumberGenerator()));

        //Shuffling them to make then random
        Collections.shuffle(possiblequestions);

        //The first question from the arraylist
        return possiblequestions.get(0);
    }
    
    /**
     * It makes random two step question, shuffles them, and returns the first one.
     * 
     * @return The first question from the ArrayList.
     */
    public static String RandomTwoStep(){
        //Initializing the arraylist
        ArrayList<String> possiblequestions = new ArrayList<String>();

        //Making 5 different possible two step questions
        possiblequestions.add(String.format("%dx %s %d = %d", randomNumberGenerator(), RandomSignGenerator(), randomNumberGenerator(), randomNumberGenerator()));
        possiblequestions.add(String.format("%d/x %s %d = %d", randomNumberGenerator(), RandomSignGenerator(), randomNumberGenerator(), randomNumberGenerator()));
        possiblequestions.add(String.format("(x %s %d)/%d = %d", RandomSignGenerator(), randomNumberGenerator(), randomNumberGenerator(), randomNumberGenerator()));
        possiblequestions.add(String.format("%d(x %s %d) = %d", randomNumberGenerator(), RandomSignGenerator(), randomNumberGenerator(), randomNumberGenerator()));
        possiblequestions.add(String.format("%d/x = %d", randomNumberGenerator(), randomNumberGenerator(), randomNumberGenerator()));

        //Shuffling them to make then random
        Collections.shuffle(possiblequestions);
        
        //The first question from the arraylist
        return possiblequestions.get(0);
    }

    /**
     * It makes random multi step question, shuffles them, and returns the first one.
     * 
     * @return The first question from the ArrayList.
     */
    public static String RandomMultiStep(){
        //Initializing the arraylist
        ArrayList<String> possiblequestions = new ArrayList<String>();
        
        //Making 4 different possible multi step questions
        possiblequestions.add(String.format("%dx/%d %s %d = %d", randomNumberGenerator(), randomNumberGenerator(), RandomSignGenerator(), randomNumberGenerator(), randomNumberGenerator()));
        possiblequestions.add(String.format("%d(x %s %d)/%d = %d", randomNumberGenerator(), RandomSignGenerator(), randomNumberGenerator(), randomNumberGenerator(), randomNumberGenerator()));
        possiblequestions.add(String.format("%d/x = %d/%d", randomNumberGenerator(), randomNumberGenerator(), randomNumberGenerator()));
        possiblequestions.add(String.format("%dx/%d %s %d/%d = %d/%d", randomNumberGenerator(), randomNumberGenerator(), RandomSignGenerator(), randomNumberGenerator(), randomNumberGenerator(), randomNumberGenerator(), randomNumberGenerator()));

        //Shuffling them to make then random
        Collections.shuffle(possiblequestions);
        
        //The first question from the arraylist
        return possiblequestions.get(0);
    }
}
