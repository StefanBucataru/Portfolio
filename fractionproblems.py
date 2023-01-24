import fractions
import doctest

def get_option():
    '''() -> int
    Prints menu and returns user's option.
    '''
    print("Welcome to our fractions program!")
    print("Select from the following options:")
    print("1. Add two fractions.")
    print("2. Subtract two fractions.")
    print("3. Multiply two fractions.")
    print("4. Divide two fractions.")
    print("5. Quit")

    while True:
        try:
            answer = int(input("Please select a option: "))
            break
        except:
            print("Please select a valid option!")

    return answer

def get_input():
    '''() -> (int, int, int, int)
    Get, error check and return input from user that represents coordinates of\
    two points.
    '''
    while True:
        try:
            a = int(input("Enter the numerator of the first fraction: "))
            b = int(input("Enter the denomunator of the first fraction: "))
            c = int(input("Enter the numerator of the second fraction: "))
            d = int(input("Enter the denomunator of the second fraction: "))
            break
        except:
            print("Please enter a number as our input.")

    return a, b, c, d

def add(a, b, c, d):
    '''(int/int + int/int) ->(int, int)
    Adds two fractions together and simplifies them.
    >>> add(1, 2, 1, 2)
    (1, 1)
    '''
    if b == d:
        numerator = a + c
        a, b = fractions.reduce_fraction(numerator, b)
        if b < 0:
            a = a * -1
            b = b * -1
        return a, b

    elif b != d:
        numerator1 = a * d
        de1 = b * d
        numerator2 = c * b
        numerator, de1 = fractions.reduce_fraction((numerator1+numerator2),de1)
        if de1 < 0:
            numerator = numerator*-1
            de1 = de1 * -1
        return numerator, de1

def subtract(a, b, c, d):
    '''(int/int - int/int) ->(int, int)
    Subtracts two fractions together and simplifies them.
    >>> subtract(2, 4, 1, 4)
    (1, 4)
    '''
    if b == d:
        numerator = a - c
        a, b = fractions.reduce_fraction(numerator, b)
        if b < 0:
            a = a * -1
            b = b * -1
        return a, b

    elif b != d:
        numerator1 = a * d
        de1 = b * d
        numerator2 = c * b
        numerator, de1 = fractions.reduce_fraction((numerator1-numerator2),de1)
        if de1 < 0:
            numerator = numerator*-1
            de1 = de1 * -1
        return numerator, de1

def multiply(a, b, c, d):
    '''((int/int) * (int/int)) ->(int, int)
    Multiplies two fractions together and simplifies them.
    >>> multiply(1, 2, 1, 2)
    (1, 4)
    '''
    a = a * c
    b = b * d
    a, b = fractions.reduce_fraction(a, b)
    if b < 0:
            a = a*-1
            b = b*-1
    return a, b

def divide(a, b, c, d):
    '''((int/int) / (int/int)) ->(int, int)
    Divides two fractions together and simplifies them.
    >>> divide(1, 2, 1, 2)
    (1, 1)
    '''
    numerator = a * d
    denomunator = b * c
    numerator, denomunator = fractions.reduce_fraction(numerator,denomunator)
    if denomunator < 0:
            numerator = numerator * -1
            denomunator = denomunator * -1
    return numerator, denomunator

if __name__ == "__main__":
    doctest.testmod()
    option = get_option()
    while option != 5:
        if option == 1:
            a, b, c, d = get_input()
            print(add(a, b, c, d))
            option = get_option()
        if option == 2:
            a, b, c, d = get_input()
            print(subtract(a, b, c, d))
            option = get_option()
        if option == 3:
            a, b, c, d = get_input()
            print(multiply(a, b, c, d))
            option = get_option()
        if option == 4:
            a, b, c, d = get_input()
            print(divide(a, b, c, d))
            option = get_option()
