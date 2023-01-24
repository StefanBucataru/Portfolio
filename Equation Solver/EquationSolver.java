//STUDENT NAME: Andrew Bucataru
//STUDENT NUMBER: 736947
//PROJECT: Equation Solver
//COURSE CODE: ICS4UR



//Importing required libraries
import java.util.Scanner;
import java.util.ArrayList;


/**
 * It takes in a string, checks if it's a valid equation, and then solves it.
 */
class EquationSolver {
    //Initializing the different different sides
    private Fraction rightside;
    private Side leftside;

    /** 
     * This is checking the position of the unknown and then setting the side with it
     * to the left side.
     * @param Equation The full equation with an unknown
     */ 
    public EquationSolver(String Equation) {
        //Checking which side of the equation has the unknown and setting it to the left side
        //This if statement checks if the unknown is on the right side
        if (Equation.substring(Equation.indexOf("=")+ 1).contains("x")) {
            //Setting the right side to what the equation must equal
            this.rightside = new Fraction(Equation.substring(0, Equation.indexOf("=")-1));
            //Setting the left side to the unknown side
            this.leftside = new Side(Equation.substring( Equation.indexOf("=")+2), this.getRight());
            
        } else {
            //Setting the right side to what the equation must equal
            this.rightside = new Fraction(Equation.substring(Equation.indexOf("=")+2));

            //Setting the left side to the unknown side
            this.leftside = new Side(Equation.substring(0, Equation.indexOf("=")-1), this.getRight());
        }
    }

    /**
     * It takes a linear equation, and solves it.
     * 
     * @param equation The equation to be solved
     * @return The answer to the equation as a Fraction.
     */
    public static Fraction solve(EquationSolver equation) {
        //Getting the slope of the equation
        Fraction slope = equation.getLeft().getSlope().simplify();

        //Getting the constant of the equation
        Fraction constant = equation.getLeft().getConstant().simplify();

        //Getting what the equation must end up equalling, this is important if the x is in the denominator
        //as that means the value the equation must equal can change
        Fraction mustequal = equation.getLeft().getMustEqual().simplify();

        //Getting what the side without the unknown is
        Fraction otherside = equation.getRight();

        //Initializing the answer fraction
        Fraction answer = new Fraction(0);

        //Checking if what the equation must equal changed due to x on den, 
        //if so then the new value must be used
        if(mustequal.divide(otherside) != new Fraction(1)) {
            //Performing the operations needed
            answer = (mustequal.subtract(constant)).divide(slope);
        } else {
            //Performing the operations needed
            answer = (otherside.subtract(constant)).divide(slope);
        }

        //Returning the answer
        return answer; 
        
    }

    /**
     * This is a helper method,
     * it creates an array of strings that are all possible terms in a equation.
     * 
     * @return The possible terms that can be in an equation as an ArrayList<String>.
     */
    private static ArrayList<String> possibleTermsArray(){
        //Creating an array list with all the possible and adding all the terms to it 
        ArrayList<String> possibleterms = new ArrayList<String>();

        String whole = "-?[1-9][0-9]*";
        String improper = "-?[1-9][0-9]*/-?[1-9][0-9]*";
        
        possibleterms.add(String.format("(%s)/\\((%s|%s)\\(-?x (\\+|-) (%s|%s)\\)/(%s|%s)\\)", whole, whole, improper, whole, improper, whole, improper));
        possibleterms.add(String.format("(%s)/\\((%s|%s)\\((%s|%s)x (\\+|-) (%s|%s)\\)/(%s|%s)\\)", whole, whole, improper, whole, improper, whole, improper, whole, improper));
        possibleterms.add(String.format("(%s)/\\((%s|%s|-)?x (\\+|-) (%s|%s)\\)", whole, whole, improper, whole, improper ));
        possibleterms.add(String.format("(%s)/\\((%s|%s)\\((%s|%s|-)?x (\\+|-) (%s|%s)\\)\\)", whole, whole, improper, whole, improper, whole, improper ));
        possibleterms.add("-?[1-9][0-9]*\\((-?[1-9][0-9]*/-?[1-9][0-9]*|-?[1-9][0-9]*|-)x (\\+|-) -?[1-9][0-9]*\\)/-?[1-9][0-9]*");
        possibleterms.add("-?[1-9][0-9]*/-?x");
        possibleterms.add("\\(-?x (\\+|-) -?[1-9][0-9]*\\)/-?[1-9][0-9]*");
        possibleterms.add("-?x");
        possibleterms.add("-?[1-9][0-9]*");
        possibleterms.add("-?[1-9][0-9]*/-?[1-9][0-9]*");
        possibleterms.add("-?[1-9][0-9]*x");
        possibleterms.add("-?x-?[1-9][0-9]");
        possibleterms.add("-?x-?[1-9][0-9]*/-?[1-9][0-9]*");
        possibleterms.add("-?[1-9][0-9]*/-?[1-9][0-9]*x");
        possibleterms.add("-?[1-9][0-9]*x/-?[1-9][0-9]*");
        possibleterms.add("-?[1-9][0-9]*\\((-|-?[1-9][0-9]*)?x (\\+|-) -?[1-9][0-9]*\\)");
        possibleterms.add("-?[1-9][0-9]*\\((-|-?[1-9][0-9]*)?x (\\+|-) -?[1-9][0-9]*\\)/-?[1-9][0-9]*");
        possibleterms.add("-?[1-9][0-9]*\\((-|-?[1-9][0-9]*)?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)");
        possibleterms.add("-?[1-9][0-9]*\\((-|-?[1-9][0-9]*)?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*");
        possibleterms.add("-?[1-9][0-9]*/-?[1-9][0-9]*\\((-|-?[1-9][0-9]*)?x (\\+|-) -?[1-9][0-9]*\\)");
        possibleterms.add("-?[1-9][0-9]*/-?[1-9][0-9]*\\((-|-?[1-9][0-9]*)?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)");
        possibleterms.add("-?[1-9][0-9]*/-?[1-9][0-9]*\\((-|-?[1-9][0-9]*)?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*");
        possibleterms.add("\\(x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*");
        possibleterms.add("-?[1-9][0-9]\\((-|-?[1-9][0-9]*)?x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*");
        possibleterms.add("\\(-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*");
        possibleterms.add("\\(-?[1-9][0-9]*/-?[1-9][0-9]*x (\\+|-) -?[1-9][0-9]*/-?[1-9][0-9]*\\)/-?[1-9][0-9]*");
        possibleterms.add("-?x/-?[1-9][0-9]*");

        return possibleterms;
    }

/**
     * This helper method updates the termsMatched variable for the inputValidator when there is a bracket in 
     * the equation.
     * 
     * @param leftSide The left side of the equation, before the equals sign.
     * @param rightSide The right side of the equation, after the equals sign.
     * @param possibleTerms an array of strings that are possible terms that could be in the equation
     * @return The number of terms that match the possible terms as an int.
     */
    private static int bracketScenario(String leftSide, String rightSide, ArrayList<String> possibleTerms) {
        // Initalizing variable
        int termsMatched = 0;

        // Checking for the side that contains the unknown and counting how many terms match

        // Block for when x is on the left side
        if (leftSide.contains("x")){
            for (int i = 0; i < possibleTerms.size(); i++) {
                if (leftSide.matches(possibleTerms.get(i))) {
                    termsMatched++;
                    break;
                }
            }
        }

        // Block for when x is on the right side
        else {
            for (int i = 0; i < possibleTerms.size(); i++) {
                if (rightSide.matches(possibleTerms.get(i))) {
                    termsMatched++;
                    break;
                }
            }
        }

        // Returning the number of terms that matched
        return termsMatched;
    }

    /**
     * This helper method updates the termsMatched variable for the inputValidator when there is no bracket in 
     * the equation.
     * 
     * @param userInput The user's input.
     * @param possibleTerms An array of strings that are the possible terms that the user can enter.
     * @throws IllegalArgumentException if the users equation is not in the correct form.
     * @return An arraylist consisting the overall amount of terms, and the amount of terms that matched.
     */
    private static int[] noBracketsScenario(String userInput, ArrayList<String> possibleTerms) throws IllegalArgumentException{
        // Initializing variables
        int termsAccumulator = 0;
        int termsMatched = 0;
        
        // Splitting the string at every space and validating every single block
        for (String term : (userInput.replace("=", "+")).split(" ")) {
            // Updating accumulator
            termsAccumulator++;

            // If the accumulator is odd, it means that the current loop term should be a monomial, hence why,
            // we use the possibleTerms Array to verify if the term is valid
            if (termsAccumulator % 2 != 0) {
                // Looping through valid terms
                for (int i = 0; i < possibleTerms.size(); i++) {
                    if (term.matches(possibleTerms.get(i))) {
                        // Updating accumulator as current term is valid
                        termsMatched++;
                        break;
                    }
                }
            }
            // Since accumulator is even, current loop term should be an operator, hence why we check if it is an
            // operator or not
            else if (term.matches("\\+") || term.matches("-")) {
                termsMatched++;
            }
            // Throwing exception if none of the terms match
            else {
                throw new IllegalArgumentException();
            }
        }
        // Initializing array that will be returned
        int returnArray[] = {termsAccumulator, termsMatched};

        // Returning Arrays
        return returnArray;
    }

    /**
     * It checks if the input is in the correct form, and throws an exception if it isn't.
     * 
     * @param userInput The user's input.
     * @throws IllegalArgumentException if the users equation is not in the correct form .
     */
    private static void Checkform(String userInput) throws IllegalArgumentException{
        // Initializing variables
        Boolean containsNumber = false;
        Boolean containsBrackets = false;
        int termsAccumulator = 0;
        int termsMatched = 0;

        // Checks if there is anything other than a 'x' unknown, numbers, and brackets in the input, and throwing an exception if there is
        for (int i = 0; i < userInput.length(); i++) {
            if (!(userInput.substring(i, i + 1).equals("x")) || !(userInput.substring(i, i + 1).equals("(")) || !(userInput.substring(i, i + 1).equals(")"))) {
                if (userInput.substring(i, i + 1).matches("[a-zA-Z]") && !(userInput.substring(i, i + 1).equals("x"))) {
                    throw new IllegalArgumentException();
                }
                else if (userInput.substring(i, i + 1).matches("[0-9]")) {
                    containsNumber = true;
                }
            } 
        }

        // An equation must contain "=", " ", "x" and numbers, and this if statement verifies that the condition is true
        if (userInput.contains("=") && userInput.contains(" ") && userInput.contains("x") && containsNumber == true) {
            // Assigning leftSide and rightSide strings using indexing
            String leftSide = userInput.substring(0, userInput.indexOf("=") - 1);
            String rightSide = userInput.substring(userInput.indexOf("=") + 2);
            
            // Throwing an exception if x is on both sides
            if (leftSide.contains("x") && rightSide.contains("x")) {
                throw new IllegalArgumentException();
            }

            // Getting the array of possible terms
            ArrayList<String> possibleTerms = possibleTermsArray();

            // Block for when there is a bracket in the equation
            if (userInput.contains("(")) {
                containsBrackets = true;
                termsMatched = bracketScenario(leftSide, rightSide, possibleTerms);
            }

            // Block for when there is no bracket in the equation
            else {
                // Updating termsAccumulator and termsMatched values as assigned in the 'noBracketsScenario()' method
                int returnArray[] = noBracketsScenario(userInput, possibleTerms);
                termsAccumulator = returnArray[0];
                termsMatched = returnArray[1];
            }
        }
        // Throwing an exception
        else {
            throw new IllegalArgumentException();
        }

        // If it contains brackets, there is only acceptable term, hence why only one term should match. If that isn't the case
        // an exception is thrown
        if (containsBrackets == true) {
            if (termsMatched != 1) {
                throw new IllegalArgumentException();
            }
        }
        // Throwing an exception if total terms is not equal to correct terms
        else if (termsAccumulator != termsMatched) {
            throw new IllegalArgumentException();
        }
    }
    /**
     * This method returns the right side of the current equation.
     * 
     * @return The right side of the equation as a Fraction.
     */
    public Fraction getRight() {
        //Returning right side
        return this.rightside;
    }
    
    /**
     * This method returns the left side of the current equation.
     * 
     * @return The left side of the equation as a Side object.
     */
    public Side getLeft() {
        //Returning left side
        return this.leftside;
    }

    /**
     * Gives the user a option if they want to solve an equation, or do a quiz.
     * 
     * If the user picks the equation solver it then takes in a string, checks if it's in the correct format, and if it is, it solves it.
     * 
     * If the user picks the quiz the Quiz module gets started.
     * 
     */
    public static void main(String[] args) {
        //Initializing scanner
        Scanner input = new Scanner(System.in);
        
        //Printing out main menu and having user select an option
        System.out.println("\033[0;34mWelcome to my Equation Solver, select an option below by picking the corresponding value\n(Type QUIT at any time to exit!):\033[0m\n\033[0;32m1.Solver\033[0m\n\033[0;36m\033[0;36m2.Quiz\033[0m\n\033[0;31m3.Quit\033[0m");
        String Option = input.nextLine();
        
        //Checking if the user picked a valid option if not ask for them to select again
        while(!Option.matches("[1-3]") && !Option.equals("QUIT")){
            System.out.println("\033[0;31mEnter a correct option!\033[0m");
            Option = input.nextLine();
        }

        //Checks what option the user picked
        //If the option picked is 1 then the solver is picked
        if (Option.equals("1")) {
            //Asking them to type an equation and getting input
            System.out.println("\033[0;33mEnter an equation with only \033[0m\033[4;33mone\033[0m \033[0;33mx to solve: \033[0m");
            String equation = input.nextLine();
            
            //Checking if the equation is typed in a valid form
            while (true){
                //Trying to run code and catching exception
                try{
                    //Checking if input is in correct form and qutting if the user types QUIT
                    if (equation.matches("QUIT")){
                        //Breaking out of the loop
                        break;
                    }
                    else {
                        //Checking form
                        Checkform(equation);
                    }
                    
                    //Making a new equation object with the given equation
                    EquationSolver eq = new EquationSolver(equation);
                    
                    //Printing out the answer of the unknown
                    System.out.println(solve(eq));

                    //Stopping the while loop
                    break;
                
                //Catching IllegalArgumentExceptions and then asking them to write input in proper form again
                }catch(IllegalArgumentException e){
                    System.out.println("\033[0;31mPlease Enter in Correct format!\033[0m");
                    equation = input.nextLine();
                }
            }
        }
        //If the option chosen is 2 then the quiz is picked
        else if (Option.equals("2")) {
            //Starting a new timer
            Stopwatch timer = new Stopwatch();

            //Starting the new timer on a new thread so that the quiz can run in parallel
            timer.start();

            //Starting a new Quiz object
            new Quiz(timer);

            //Terminating the thread that is still running
            timer.stop();
        }
        //Closing input to prevent memory leak
        input.close();
    }
}