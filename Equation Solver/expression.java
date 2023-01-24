/**
 * The expression class takes in a string and then splits it into two fractions and an operator
 */
class expression {
    //Initialising the variables
    private Fraction one;
    private Fraction two;
    private String operation;
    private String expression;

    
    /**
     * This is the constructor for the expression class. It takes in a string and then splits it into
     * two fractions and an operator.
     */ 
    public expression(String expr){
        //Creating an accumulator to find where the operation and declaring two different terms
        int operandloc = 0;
        String term1 = "";
        String term2 = "";
        this.expression = expr;

        //For loop going through each letter checking where the operand is and then finding the operand
        for (int i = 0; i < expr.length(); i++){
            String exprind = expr.substring(i, i+1);
            operandloc++;
            if (exprind.equals("+")){
                this.operation = "+";
                break;
            }
            else if (expr.substring(i, i+2).equals("- ")){
                this.operation = "-";
                break;
            }
            else if (expr.substring(i, i+2).equals("/ ")){
                this.operation = "/";
                break;
            }
            else if (exprind.equals("*")){
                this.operation = "*";
                break;
            }

            term1 = expr.substring(0, operandloc-1);
            term2 = expr.substring(operandloc+2);
        }

        //Checks if the first fraction contains a / and if it doesn't then it is just a whole value
        if (!term1.contains("/")){
            int whole1 = Integer.valueOf(term1);
            this.one = new Fraction(whole1);
        }

        //If there is a space in the first term then it is a mixed fraction
        else if (term1.contains(" ")){
            int whole1 = Integer.valueOf(term1.substring(0, term1.indexOf(" ")));
            int num1 = Integer.valueOf(term1.substring(term1.indexOf(" ") + 1, term1.indexOf("/")));
            int den1 = Integer.valueOf(term1.substring(term1.indexOf("/")+1));
            this.one = new Fraction(whole1, num1, den1);
        
        //If there isn't a space but there is a / then this is a improper fraction or a normal fraction
        } else {
            int num1 = Integer.valueOf(term1.substring(0, term1.indexOf("/")));
            int den1 = Integer.valueOf(term1.substring(term1.indexOf("/")+1));
            this.one = new Fraction(num1, den1);
        }

        //Checks if the second fraction contains a / and if it doesn't then it is just a whole value
        if (!term2.contains("/")){
            int whole2 = Integer.valueOf(term2);
            this.two = new Fraction(whole2);
        }

        //If there is a space in the second term then it is a mixed fraction
        else if (term2.contains(" ")){
            int whole2 = Integer.valueOf(term2.substring(0, term2.indexOf(" ")));
            int num2 = Integer.valueOf(term2.substring(term2.indexOf(" ") + 1, term2.indexOf("/")));
            int den2 = Integer.valueOf(term2.substring(term2.indexOf("/")+1));
            this.two = new Fraction(whole2, num2, den2);

        //If there isn't a space but there is a / then this is a improper fraction or a normal fraction
        } else {
            int num2 = Integer.valueOf(term2.substring(0, term2.indexOf("/")));
            int den2 = Integer.valueOf(term2.substring(term2.indexOf("/")+1));
            this.two = new Fraction(num2, den2);
        }
    }

    
    /**
     * This function returns the operator of the expression
     * 
     * @return The operator of the expression
     */
    public String getOp(){
        //Returns the operator of the expression
        return this.operation;
    }

    /**
     * Returns the first fraction
     * 
     * @return The first fraction
     */
    public Fraction getFrac1(){
        //Returns the first fraction
        return this.one;
    }

    
    /**
     * Returns the second fraction
     * 
     * @return The second fraction
     */
    public Fraction getFrac2(){
        //Returns the second fratcion
        return this.two;
    }

    /**
     * This function returns the expression
     * 
     * @return The expression
     */
    public String toString(){
        //Returns the second fratcion
        return this.expression;
    }
}