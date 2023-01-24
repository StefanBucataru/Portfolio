//Importing required libraries
import java.util.ArrayList;

/**
 * It takes a string, and turns it into a slope and a constant
 */
class Side {
    //Initializing the different different sides
    private ArrayList<String> orderedterms = new ArrayList<String>();
    private Fraction slope;
    private Fraction constant;
    private Fraction mustequal;

    /** 
     * The constructor for the Side class. It takes a string, and turns it into a slope and a constant.
     */ 
    public Side(String side, Fraction answerval){
        //Setting what the equation must equal
        this.mustequal = answerval;

        //Checking if the side contains brackets, if it does then checking if there are brackets in the
        //Denominator and then performing the correct operations
        if (side.contains("(")){
            //Checking if there are brackets in the den
            if (side.contains("/") && side.substring(side.indexOf("/"), side.indexOf("/")+2).contains("(")){
                //Performing the correct steps if there are brackets in the den
                InDenWithBrackets(side);
            } else {
                //Performing the correct steps if there are not brackets in the den
                ContainsBrackets(side);
            }
        }
        //Checking if there is only x as a denominator
        else if (side.matches("-*[1-9][0-9]*/-?x") || side.matches("-*[1-9][0-9]*/-?x (\\+|-) -*[1-9][0-9]*") || side.matches("-*[1-9][0-9]*/-?x (\\+|-) -*[1-9][0-9]*/-*[1-9][0-9]*")){
            //Performing the correct steps if there is a term with an x in the denominator
            XasDen(side);
        } else {
            //If there are no special aspect to the equation solving it normally
            NormalEquation(side);
        }
    }

    /**
     * It takes an ArrayList of Strings, gathers all like terms and returns an ArrayList of Strings
     * 
     * @param termlist ArrayList<String> with the terms of the side
     * @return The method is returning an ArrayList of Strings.
     */
    public static ArrayList<String> gatherTerms(ArrayList<String> termlist){
        //Creating the values for the constant term and the unknown term
        Fraction ConstantTerm = new Fraction(0);
        Fraction UnknowntermCoeff = new Fraction(0);

        //Creating a arraylist of strings to store the terms
        ArrayList<String> simplifiedterms = new ArrayList<String>();

        //Looping through ever term in the list
        for (String term : termlist){

            //If the term does not contain an x then checking if its a fraction
            if (!term.contains("x")){
                if (term.matches("-*[1-9][0-9]*/-*[1-9][0-9]*")){
                    //Getting the numerator of the fraction and the denominator of the fraction and then adding it to a list
                    int num = Integer.valueOf(term.substring(0, term.indexOf("/")));
                    int den = Integer.valueOf(term.substring(term.indexOf("/")+1));
                    ConstantTerm = ConstantTerm.add(new Fraction(num, den));
                } else {
                    //If the number is a whole number then no need to get num or den
                    ConstantTerm = ConstantTerm.add(new Fraction(term));
                }

            } else {
                //If it is just x then I set the unknown term coefficient to 1 and add it to the list
                if(term.matches("x")){
                    UnknowntermCoeff = UnknowntermCoeff.add(new Fraction(1));
                }
                //If the x coefficient is a fraction I just need to get the whole fraction and add it to the list
                else if (term.matches("-*[1-9][0-9]*/-*[1-9][0-9]*x")){
                    UnknowntermCoeff = UnknowntermCoeff.add(new Fraction(term.substring(0, term.indexOf("x"))));
                }
                //If there is a whole number in front of the x getting that and then adding it to the list
                else{
                    UnknowntermCoeff = UnknowntermCoeff.add(new Fraction(term.substring(0, term.indexOf("x"))));
                }
            }
        }
        //After adding the like terms we then add the mx + b into another list and then returning it
        simplifiedterms.add(UnknowntermCoeff.toString() + "x");
        simplifiedterms.add(ConstantTerm.toString());

        //Returning the list
        return simplifiedterms;
    }

    /**
     * It takes a string, and orders the terms in the form mx + b
     * 
     * @param side The equation that contains the x variable
     */
    public void Orderterms(String side){
        //Initializing the negative boolean
        boolean negative = false;

        //looking through every term split by a space
        for (String term : side.split(" ")){
            
            //If the term is not a sign then checking if the sign prior to it is negative
            if (!term.matches("(-|\\+)")){
                if (negative == true){
                    //If it is then I must flip the signs of the number of of the x value
                    if (term.contains("-") && term.contains("x")){
                        //Flipping the sign
                        this.orderedterms.add(0, term.replace("x", "").replace("-", "") + "x");
                    }
                    else if (term.contains("x")){
                        //Flipping the sign
                            this.orderedterms.add(0, "-" + term.replace("x", "") + "x");
                    } else {
                        //Flipping the sign if the term is just a number
                        if (term.contains("-")){
                            //Flipping the sign
                            this.orderedterms.add(term.replace("-", ""));
                        } else {
                            //Flipping the sign
                            this.orderedterms.add("-" + term);
                        }
                    }
                    //Changing negative back to false so it can be used again later
                    negative = false;
                } else {
                    //If the term is not negative than adding it to the list
                    if (term.contains("x")){
                        //Moving x to the back of the fraction
                        if (term.matches("-x/-*[1-9][0-9]*") || term.matches("-*[1-9][0-9]*/-x")){
                            this.orderedterms.add(0, term.replace("-x", "-1") + "x");
                        }
                        //Moving x to the back of the fraction
                        else if ((term.matches("x/-*[1-9][0-9]*") || term.matches("-*[1-9][0-9]*/x"))){
                            this.orderedterms.add(0, term.replace("x", "1") + "x");
                        }
                        //Adding -1 in front of the x if it is negaitve
                        else if (term.matches("-x")){
                            this.orderedterms.add(0, term.replace("-x", "-1") + "x");
                        }
                        else {
                            //Moving x to the back of the fraction
                            this.orderedterms.add(0, term.replace("x", "") + "x");
                        }
                    } else {
                        //If it does not contain x just adding the number or fraction to the list
                        this.orderedterms.add(term);
                    }
                    }
                }
            //If the term is the negative sign setting negative to true
            else if (term.matches("-")){
                negative = true;
            }
        }
    }
    
    /**
     * It takes a string of the form "a(bx (+ or -) cd)/e" or "a(bx (+ or -) d)/e" and returns a string of the form "((a/e)*b)x (+ or -) ((a/e)*c)d"
     * 
     * @param side The side of the equation that contains the x variable
     * @return The expanded string.
     */
    public static String Expand(String side){
        //Initalizing the slope and the expanded variables
        Fraction slope;
        String expanded = "";

        //Getting the index value of the unknown
        int unknownind = side.indexOf("x");

        //Getting the index value of the first opening bracket
        int openingbracket = side.indexOf("(");

        //Getting the index value of the first closing bracket
        int closingbracket = side.indexOf(")");

        //Testing if there is a number in front of the opening bracket
        try{
            //Setting the number if there is one
            slope = new Fraction(side.substring(0, openingbracket));
        
        //Catching the exception if there is nothing between index 0 and the opening bracket
        } catch(Exception ee){
            //If there isn't a fraction or a number checking if there is a negative in front of it
            if (side.substring(0, openingbracket).matches("-")){

                //Setting slope as -1 if there is a negative
                slope = new Fraction(-1);
            }
            else {
                //Setting slope as 1 if there isn't
                slope = new Fraction(1);
            }
        }

        //Checking if there is a divisor after the closing brackets
        if (side.length()-1 > closingbracket + 1){
            //If there is including that in the slope
            slope = slope.divide(new Fraction(Integer.valueOf(side.substring(closingbracket+2))));

            //Changing the length of the side and getting rid of the divisor at the end
            side = side.substring(0, closingbracket+1);
        }
        
        //Making a patter to check if the equation matches a certain type
        String whole = "-*[0-9]*";
        String improper = "-*[0-9][0-9]*/-*[1-9][0-9]*";
        String pattern = String.format("(%s|%s)\\(-?x (\\+|-) (%s|%s)\\)/*(%s|%s)*", whole, improper, whole, improper, whole, improper);

        //If the equation matches the pattern then that means that it must be expanded
        if (side.substring(0).matches(pattern)){
            
            //Checking if the betwen the openning and the closing brackets is - or not
            if (side.substring(openingbracket+1,openingbracket+3).matches("-x")){

                //If it is multiplying in the slope with -1
                expanded = expanded + (new Fraction(-1).multiply(slope)).unsimplify()+ "x";
            }
            else {
                //If it isn't multiplying in the slope normally
                expanded = expanded + (new Fraction(1).multiply(slope)).unsimplify()+ "x";
            }
        //If the equation does not match the pattern
        } else {
            //Trying to see if there is a coefficient to the x originally
            try {
                expanded = expanded + (new Fraction(side.substring(openingbracket+1, unknownind)).multiply(slope)).unsimplify() + "x";
            //If that doesn't work that means there is no number in front
            } catch (Exception e) {
                //Expanding the first term
                expanded = expanded + (new Fraction(1).multiply(slope)).unsimplify() + "x";
            }
        }

        //Checking if there is a minus sign, if there is then setting the number next to it to a negative and changing the sign to a plus
        if (side.substring(side.indexOf(" ")+1, side.indexOf(" ")+2).equals("-")){
            //Adding the constant to the expanded form and setting the sign
            expanded = expanded + " + " + ((slope.multiply(new Fraction(side.substring(side.indexOf(" ")+3, side.indexOf(")")))))).multiply(new Fraction(-1)).unsimplify();
        } else {
            //Adding the constant to the expanded form if there is no - sign
            expanded = expanded + " + " + (slope.multiply(new Fraction(side.substring(side.indexOf(" ")+3, side.indexOf(")"))))).unsimplify();
        }

        //Returning the expanded form of the equation which is a string
        return expanded;
    }

    /**
     * It takes a string, with an x in the denominator, it will then expand if needed and then move over the denominator to the otehr side,
     * expand and then sets the slope and the constant value
     * 
     * @param side The equation that contains the x variable
     */
    private void XasDen(String side){
        //Initializing the variable
        String otherside = "";
        Fraction sign = new Fraction(1);

        
        //If the x is negative making other side negative
        if (side.substring(side.indexOf("/"), side.indexOf("x")).contains("-")){
            sign = new Fraction(-1);
        }

        //Trying to index to see if there is a sign, if there is no index then catching the error
        try {
            //if there is a plus then moving the number with it over to the other side and then moving the x over
            if (side.contains("+")) {
                otherside = this.mustequal.subtract(new Fraction(side.substring(side.indexOf("+")+2))).multiply(sign).toString() + "x";
            }
            //if there is a minus then moving the number with it over to the other side and then moving the x over
            else if (side.substring(side.indexOf("x")+2, side.indexOf("x")+3).matches("-")) {
                otherside = this.mustequal.add(new Fraction (side.substring(side.indexOf("x")+4))).multiply(sign).toString() + "x";
            }
            //If ther is no sign then just moving x over
            else {
                otherside = this.mustequal + "x";
            }
            
        //If ther is no sign then just moving x over
        } catch (Exception e) {
            //Checking if the x is negative
            if(side.substring(side.indexOf("/")+1).contains("-")){
                otherside = this.mustequal.multiply(new Fraction(-1)) + "x";
            }
            else{
                otherside = this.mustequal + "x";
            }
        }

        //Setting what the new equation must equal after moving everything to the other side
        this.mustequal = new Fraction(Integer.valueOf(side.substring(0, side.indexOf("/"))));

        //Setting the sloep and constant of the new equation
        this.slope = new Fraction(otherside.substring(0,otherside.indexOf("x")));
        this.constant = new Fraction(0);
    }

    /**
     * It takes a string, and then it orders the terms in the string, and then it gathers the terms in
     * the string, and then it sets the slope and constant of the equation
     * 
     * @param side The equation that contains the x variable
     */
    private void NormalEquation(String side){
        //Ordering the terms to have the x values first
        this.Orderterms(side);

        //Gathering all the like terms
        this.orderedterms = gatherTerms(orderedterms);

        //Trying to index the slope in front of the x if there is one, if there isn't then setting it then catching the error
        try{
            //Setting the slope in front of the x
            this.slope = new Fraction(orderedterms.get(0).substring(0,orderedterms.get(0).indexOf("x")));
        } catch (Exception ee){
            //If there is a - in front of the x setting the slope to -1
            if (orderedterms.get(0).substring(0, orderedterms.get(0).indexOf("x")).equals("-")){
                this.slope = new Fraction(-1);
            
            //If there is nothing in front of the x setting the slope to 1
            } else {
                this.slope = new Fraction(1);
            }
        }

        //Trying the index the constant out of the arraylist, if there isn't one then catching the error given
        try {
            //Setting the constant to the constant index in the ordererd terms array
            this.constant = new Fraction(orderedterms.get(1));
        } catch (Exception e) {
            //Setting the constant to 0 if there is no contants in the array
            this.constant = new Fraction(0);
        }
    }

    /**
     * It takes a string that contains brackets and expands the brackets and
     * then orders the terms and sets the slope and constant values
     * 
     * @param side The equation that is contains the x
     */
    private void ContainsBrackets(String side){
        //Initializing the variable
        String expanded = "";

        //Checking if the there is a divisor at teh end of the brackets
        if (side.length()-1 > side.indexOf(")") && side.substring(side.indexOf(")")+1, side.indexOf(")")+2).contains("/")){
            //If there is then expanding it with the divisor
            expanded = Expand(side);
        } else {
            //If there isn't then expanding it wihtout the divisor
            expanded = Expand(side.substring(0, side.indexOf(")") + 1)) + side.substring(side.indexOf(")")+1);
        }
        //Ordering the terms of the expanded equation
        this.Orderterms(expanded);

        //Gethering the like terms of the expanded equation
        this.orderedterms = gatherTerms(orderedterms);

        //Setting the slope and constant of the expanded equation
        this.slope = new Fraction(orderedterms.get(0).substring(0,orderedterms.get(0).indexOf("x")));
        this.constant = new Fraction(orderedterms.get(1));
    }

    /**
     * If the denominator contains brackets it expands them if needed and then moves the denominator over to the other side,
     *  and sets the slope and constant values
     * 
     * @param side The side of the equation that contains the x variable
     */
    private void InDenWithBrackets(String side){
        //Initializing the variable
        String expanded = "";

        //Checking if the equation contains a divisor in the denominator brackets
        try {
            //Expanding it out
            expanded = Expand(side.substring(side.indexOf("/")+2, side.indexOf(")", side.indexOf(")")+1)));
        } catch (Exception e) {
            //Expanding it out
            expanded = Expand(side.substring(side.indexOf("/")+2, side.indexOf(")")+1));
        }
        
        //Moving the equation over to the other side
        expanded = this.mustequal.toString() + "("+ expanded + ")";

        //Setting what the new equation must equal after moving it over to the other side
        this.mustequal = new Fraction(Integer.valueOf(side.substring(0, side.indexOf("/"))));

        //Expanding out the equation after moving it over to the other side
        expanded = Expand(expanded);

        //Ordering the terms of the expanded equation
        this.Orderterms(expanded);

        //Setting the slope and constant of the new equation
        this.slope = new Fraction(orderedterms.get(0).substring(0,orderedterms.get(0).indexOf("x")));
        this.constant = new Fraction(orderedterms.get(1));
    }

    /**
     * This function returns the slope of the line
     * 
     * @return The slope of the line as a fraction.
     */
    public Fraction getSlope(){
        //Returning the slope
        return slope;
    }

    /**
     * This function returns the constant of the equation
     * 
     * @return The constant is being returned as a fraction.
     */
    public Fraction getConstant(){
        //Returning the constant value
        return constant;
    }

    /**
     * This function returns the value the equation must equal
     * 
     * @return The mustequal variable is being returned as a fraction.
     */
    public Fraction getMustEqual(){
        //Returning the mustequal value
        return mustequal;
    }

    /**
     * This function returns an ArrayList of Strings that contains the terms in order
     * 
     * @return The ordered terms are being returnedas a arraylist<string>.
     */
    public ArrayList<String> getTerms(){
        //Returning the ordered terms list
        return orderedterms;
    }
}

