//STUDENT NAME: Andrew Bucataru
//STUDENT NUMBER: 736947
//PROJECT: Fractions Calculator
//COURSE CODE: ICS4UR

import java.util.Scanner;

/**
 * This class is used to create a fraction object that can be used to perform operations on fractions.
 */
class Fraction {
    //Initialising the variables
    private int whole = 0;
    private int num;
    private int den;

    /**  
     * This is a constructor that takes in a whole value and sets the whole value to 
     * the whole variable.
     * @param wholevalue This is a whole integer
     */
    public Fraction(int wholeValue) {
        //Whole value when only one number is inputted
        whole = wholeValue;
        num = 0;
        den = 1;
    }

    /**
     * This is taking in two integers and assigning them to the numerator and denominator. It is also checking if the fraction is improper
     * and converting it to a mixed number. It is also reducing the fraction.
     * @param numVal this is a whole integer in the numerator
     * @param denVal this is a whole integer in the denominator
     */
    public Fraction(int numVal, int denVal) {
        //Assigning numerator and denominator with no whole value
        whole = 0;
        num = numVal;
        den = denVal;

        //Getting the sign of the fraction
        int fractionSign = getSign(whole, num, den);

        //Converting improper fractions to mixed
        if (Math.abs(num) > Math.abs(den)) {
            whole = Math.abs(num) / Math.abs(den);
            num = Math.abs(num) % Math.abs(den);
        }

        if (whole != 0) {
            whole = fractionSign * whole;
            num = Math.abs(num);
            den = Math.abs(den);
        }

        //Correcting the signs on the fraction if not improper
        else {
            num = fractionSign * Math.abs(num);
            den = Math.abs(den);
        }

        int greatestCommonFactor = gcf(num, den);
        //Reducing the fractions
        num = num / greatestCommonFactor;
        den = den / greatestCommonFactor;

        //Cheching if the fracion is 1/1 so that when printing out result it is a whole number
        if ((num == 1 || num == -1) && den == 1) {
            whole = num * 1;
            num = 0;
        }

    }

    /** 
     * This takes in a mixed fraction with a whole numerator and denominator,
     * and simplifies it if necessary.
     * @param wholeval A whole integer or the whole part of a fraction
     * @param numval A whole integer or the num part of a fraction
     * @param denval A whole integer or the den part of a fraction
     */
    public Fraction(int wholeVal, int numVal, int denVal) {
        //Calls the previous constructor for numerator and denominator
        this(numVal, denVal); 

        //Getting the sign of the fraction
        int fractionSign = getSign(wholeVal, numVal, denVal);
        //Correcting the whole sign and adding up the 2 wholes
        whole = fractionSign * (Math.abs(wholeVal) + Math.abs(whole));
    }

    /**
     * Takes in a string and then makes it into a fraction object
     * @param frac This is a string in the form of a fraction
     */
    public Fraction(String frac){
        //Checking if the fraction contains a whole or not
        if (frac.matches("-*[1-9][0-9]*/-*[1-9][0-9]*")){
            
            //Checking if the denominator is a 1 or -1
            if (frac.matches("-*[1-9][0-9]*/-*1")){
                //Setting the whole
                whole = Integer.valueOf(frac.substring(0, frac.indexOf("/")));

                //Setting the denominator
                den = 1;
            } else {
                //Setting the numerator
                num = Integer.valueOf(frac.substring(0, frac.indexOf("/")));

                //Setting the denominator
                den = Integer.valueOf(frac.substring(frac.indexOf("/")+1));
            }
        }
        //If the fraction is a mixed fraction
        else if (frac.matches("-*[0-9][0-9]* [1-9][0-9]*/[1-9][0-9]*")){
            //Setting the whole to the number before the space
            whole = Integer.valueOf(frac.substring(0, frac.indexOf(" ")));
            
            //Setting the numerator to the number between the space and /
            num = Integer.valueOf(frac.substring(frac.indexOf(" ")+1, frac.indexOf("/")));

            //Setting the numerator to the number after /
            den = Integer.valueOf(frac.substring(frac.indexOf("/")+1));

        //If the fraction is just a whole
        } else {
            //Setting the whole value if the fraction is just
            whole = Integer.valueOf(frac);
            //Setting the den
            den = 1;
        }
    }
    

    /**
     * This function checks how many parts of a fraction are negative and returns a -1 if the number of
     * negatives is odd and a 1 if the number of negatives is even
     * 
     * @param wholeVal The whole value of the fraction
     * @param numVal The numerator value of the fraction
     * @param denVal The denominator value of the fraction
     * @return The sign of the fraction is being returned.
     */
    public static int getSign(int wholeVal, int numVal, int denVal) {
        //Checking how many parts of a fraction are negative
        int negativeChecker = 0;

        //Checking if the whole value is negative
        if (wholeVal < 0) {
            negativeChecker++;
        }

        //Checking if the numerator value is negative
        if (numVal < 0) {
            negativeChecker++;
        }

        //Checking if the denominator value is negative
        if (denVal < 0) {
            negativeChecker++;
        }
        
        negativeChecker %= 2;

        //If number of negatives is odd then fraction is negative
        if (negativeChecker == 1) {
            return -1;
        }

        //If number of negatives is even then fraction is positive
        else {
            return 1;
        }
    }

    

    /**
     * The function takes in two integers, and returns the greatest common factor of the two integers
     * 
     * @param numVal The numerator of the fraction
     * @param denVal The denominator of the fraction
     * @return The greatest common factor of the numerator and denominator
     */
    public int gcf(int numVal, int denVal) {
        //Checking if numerator or denominator is 1 or 0
        if (numVal == 0 || numVal == 1 || denVal == 0 || denVal == 1) {
            return 1;
        }

        //Counter used to find the factor
        
        int factor = Math.min(Math.abs(numVal), Math.abs(denVal));

        //Checking if the factor evenly divides into the numerator and the denominator
        while (true) {
            if (numVal % factor == 0 && denVal % factor == 0) {
                return factor;
            }
            factor--;
        }
    }

    

    
    /**
     * This function returns the whole value of the fraction
     * 
     * @return The whole value of the fraction as an int
     */
    public int getWhole() {
        //Returning the whole value of fraction
        return whole;
    }

    

    
    /**
     * This function returns the numerator value of the fraction
     * 
     * @return The numerator value of the fraction as an int
     */
    public int getNum() {
        //Getting the numerator value of fraction
        return num;
    }

    

    
    /**
     * This function returns the denominator value of the fraction
     * 
     * @return The denominator value of the fraction as an int
     */
    public int getDen() {
        //Getting the denominator value of fraction
        return den;
    }

    

    
    /**
     * This function sets the whole value of a fraction.
     * 
     * @param newWholeVal The new whole value of the fraction
     */
    public void setWhole(int newWholeVal) {
        //Setting the whole value of a fraction
        whole = newWholeVal;
    }

    

    
    /**
     * This function sets the numerator value of a fraction.
     * 
     * @param newNumVal The new numerator value of the fraction
     */
    public void setNum(int newNumVal) {
        //Setting the numerator value of a fraction
        num = newNumVal;
    }

    

    
    /**
     * > This function sets the denominator value of a fraction
     * 
     * @param newDenVal The new denominator value
     */
    public void setDen(int newDenVal) {
        //Setting the denominator value of a fraction
        den = newDenVal;
    }

    

    /**
     * This function takes the whole, numerator, and denominator values and formats them into a string
     * 
     * @return The string fraction is being returned.
     */
    public String toString() {
        //Making a variable to hold the string
        String returnString = "";
        
        //Checking if the num is 0 as this is invalid and only has a whole
        if (num == 0) {
            return Integer.toString(whole); 
        }

        //Formatting the string without the whole value
        else if (whole == 0) {
            returnString = String.format("%d/%d", num, den);
        }

        //Formatting the string with a whole value
        else if (whole != 0) {
            returnString = String.format("%d %d/%d", whole, num, den);
        }

        return returnString;
    }

    /**
     * It creates a new instance of a fraction.
     * 
     * @return A new instance of a fraction
     */
    public Fraction clone() {
        //Creating a new instance of a fraction
        return new Fraction(whole, num, den);
    }

    
    /**
     * Turns a mixed fraction into a improper fraction.
     * 
     * @param wholeVal The whole number value of the mixed fraction
     * @param numVal Numerator
     * @param denVal Denominator value
     * @return The numerator of the improper fraction
     */
    public int ImproperFractions(int wholeVal, int numVal, int denVal) {
        //Converting a mixed fraction to a improper fraction
        if (wholeVal * denVal < 0) {
            return ((wholeVal * denVal) - numVal);
        } else {
            return ((wholeVal * denVal) + numVal);
        }
    }

    
    /**
     * This function takes in a fraction and adds it to the current fraction.
     * 
     * @param other The other fraction that is being added to the current fraction
     * @return The numerator and denominator of the new fraction
     */
    public Fraction add(Fraction other) {
        //Making a mixed fraction a improper fraction and setting the numerator
        int numeratorOriginal = ImproperFractions(whole, num, den);
        int numeratorOther = ImproperFractions(other.getWhole(), other.getNum(), other.getDen());
        
        //Making the denominators be the same
        int numerator = (numeratorOriginal * other.getDen()) + (numeratorOther * den);
        int denominator = den * other.getDen();
        
        return new Fraction(numerator, denominator);
    }

    
    /**
     * We create a clone of the other fraction, switch the sign of the numerator and whole number, and
     * then add the two fractions together
     * 
     * @param other The fraction that is being subtracted from the current fraction
     * @return The result of the subtraction
     */
    public Fraction subtract(Fraction other) {
        //Subraction is like adding a positive fraction with a negative fraction so we switch the symbol
        Fraction switchedSigns = other.clone();

        if (other.getWhole() ==  0) {
            switchedSigns.setNum(-1 * switchedSigns.getNum());
        } else {
            switchedSigns.setWhole(-1 * other.getWhole());
        }

        return add(switchedSigns);
    }

    

    /**
     * This function takes in a fraction and multiplies it with the current fraction
     * 
     * @param other The fraction that is being multiplied by the current fraction
     * @return The numerator and denominator of the new fraction
     */
    public Fraction multiply(Fraction other) {
        //Making a mixed fraction a improper fraction and setting the numerator
        int numeratorOriginal = ImproperFractions(whole, num, den);
        int numeratorOther = ImproperFractions(other.getWhole(), other.getNum(), other.getDen());

        //Preforming the multiplication
        int numerator = numeratorOriginal * numeratorOther;
        int denominator = den * other.getDen();


        return new Fraction(numerator, denominator);
    }

    

    /**
     * It divides the fraction by the other fraction by getting the recipical of the other one.
     * 
     * @param other The fraction that is being divided by
     * @return The result of the division of the two fractions
     */
    public Fraction divide(Fraction other) {
        //Getting the inverse of the second fraction and then multiplying it
        int numerator = other.getDen();
        int denominator = ImproperFractions(other.getWhole(), other.getNum(), other.getDen());

        Fraction reciprocal = new Fraction(numerator, denominator);

        return multiply(reciprocal);
    }

    /**
     * This function takes a fraction and returns the fraction in its unsimplified form
     * 
     * @return The method is returning a string that is the fraction in the form of a string.
     */
    public String unsimplify(){
        //Initializing variable
        int numer = num;
        int denom = den;
        String fraction = "";

        //Checking if there is a num or whole and then simplifiyng if there is both
        if (num != 0){
            if (whole != 0){
                if (getSign(whole, num, den) == -1){
                    numer = (this.whole * this.den - this.num);
                }
                else {
                    numer = this.whole * this.den + this.num;
                }
                denom = this.den;
            }
        }
        
        //Checking if there is a numerator so that I can pick which to return
        if (numer != 0){
            fraction = String.valueOf(numer) + "/" + String.valueOf(denom);
        }
        else{
            fraction = String.valueOf(whole);
        }

        //Return String
        return fraction;
    }
    
    /**
     * This function simplifies the fraction by creating a new fraction with the whole number,
     * numerator, and denominator
     * 
     * @return A new fraction that is simplified
     */
    public Fraction simplify(){
        //Creating a new fraction to auto make it into a mixed fraction
        return new Fraction(this.whole, this.num, this.den);
    }

    

    /**
     * This function takes in an expression object and then performs the operation given by the user
     * 
     * @param expr The expression that the user inputted
     * @return The final fraction is being returned
     * @throws IllegalArgumentException If the user input is not correct
     */
    public static Fraction createfinal(expression expr) throws IllegalArgumentException{
        //Checks the operation given by user and then performs it
        if (expr.getOp().equals("+")){
            return(expr.getFrac1().add(expr.getFrac2()));
        }
        else if (expr.getOp().equals("-")){
            return(expr.getFrac1().subtract(expr.getFrac2()));
        }
        else if (expr.getOp().equals("*")){
            return(expr.getFrac1().multiply(expr.getFrac2()));
        } else {
            //Checking if a whole is being divided by 0
            if (expr.getFrac2().getNum() == 0 && expr.getFrac2().getWhole() == 0) {
                throw new IllegalArgumentException("DIVIDING BY ZERO");
            }

            return expr.getFrac1().divide(expr.getFrac2());
        }
    }

    /**
     * It checks if the expression given is in the correct format
     * 
     * @param expr The expression that is being checked
     */
    public static void checkexp(String expr) throws IllegalArgumentException{
        //Stating all the possible patterns
        String whole = "-*[0-9]*";
        String improper = "-*[0-9][0-9]*/-*[1-9][0-9]*";
        String mixed = "-*[0-9][0-9]* [1-9][0-9]*/[1-9][0-9]*";

        //Stating the format of a expression
        String pattern = String.format("(%s|%s|%s) (\\+|-|\\*|/) (%s|%s|%s)", whole, improper, mixed, whole, improper, mixed);
        
        //Checks if the expression given matches the correct format, if not throws a exception
        if (!expr.matches(pattern)){
            throw new IllegalArgumentException("Enter in the correct format!");
        }
    }

    /**
     * This function checks if the fraction is equal to another fraction
     * 
     * @param otherfac The fraction that is being compared to the current fraction.
     * @return The method is returning a boolean value.
     */
    public boolean equals(Fraction otherfac) {
        //Checking if the fractions only have wholes and then checking if they are equal
        if(this.num == 0 && otherfac.getNum() == 0){
            if (this.whole == otherfac.getWhole()){
                return true;
            }
            else {
                return false;
            }
        }
        //Checking if the fractions are equal
        else {
            if (this.whole == otherfac.clone().getWhole() && this.num == otherfac.clone().getNum() && this.den == otherfac.clone().getDen()) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    /**
     * Asked the user to type in a fraction equation
     * 
     * checks that the user input is valid
     * 
     * performs the appropriate operation
     * 
     * prints out the resulting fraction
     * 
     */
    public static void main(String[] args) {
        //Asking for a fraction
        System.out.println("Enter a fraction equation: ");
        Scanner input = new Scanner(System.in);
        
        while (true){
            //Running a try and catch statement to catch if an exception happens
            try{
                //Getting the input
                String expr = input.nextLine();

                //First checking the expression
                checkexp(expr);

                //Creating the string given into an expression object
                expression big = new expression(expr);

                //Printing out the final answer as a fraction object
                System.out.println(createfinal(big));

                //Breaking out of the loop if there are no exceptions
                break;

            } catch (IllegalArgumentException e){
                //If an argument is caught, print out the error
                System.out.println("Please enter a valid equation!");
                System.out.println("Enter a fraction equation: ");
            }
        }

        input.close();
    }

    
    
}