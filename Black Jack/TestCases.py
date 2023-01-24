from Functions import *
from main import *

suit = ['A', 'B']
suit2 = ["D", "S", "C", "H"]

assert Deck_Generate('A', '2') == [('A', '2')]
assert Deck_Generate(suit, '3') == [('A', '3'), ('B', '3')]
assert Deck_Generate(suit2, '4') == [('D', '4'), ('S', '4'), ('C', '4'), ('H', '4')]

assert start() == True