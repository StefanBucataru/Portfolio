import doctest
import random as r

def Busted(font, window):
    '''(str, str) -> ()
    Put the busted message on screen
    '''
    text = font.render(f'You Busted!', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 500/2))
    text = window.blit(text, text_rect)

def Win(font, window):
    '''(str, str) -> ()
    Put the win message on screen
    '''
    text = font.render(f'You Win!', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 500/2))
    text = window.blit(text, text_rect)

def Tie(font, window):
    '''(str, str) -> ()
    Put the tie message on screen
    '''
    text = font.render(f'You Tie!', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 500/2))
    text = window.blit(text, text_rect)

def Lost(font, window):
    '''(str, str) -> ()
    Put the lost message on screen
    '''
    text = font.render(f'You Lost!', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 500/2))
    text = window.blit(text, text_rect)

def Dealer_Cards(font, window):
    '''(str, str) -> ()
    Put the dealer cards on screen
    '''
    text = font.render(f'These are the dealer cards', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 15))
    text = window.blit(text, text_rect)

def Player_Cards(font, window):
    '''(str, str) -> ()
    Put the player cards on screen
    '''
    text = font.render(f'These are your cards', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 15))
    text = window.blit(text, text_rect)

def Dealer_Total(font, window, total):
    '''(str, str) -> ()
    Put the dealer total on screen
    '''
    text = font.render(f'The dealer total is {total}', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 40))
    text = window.blit(text, text_rect)

def instructions(font, window):
    '''(str, str) -> ()
    Put the instructions on screen
    '''
    text = font.render(f'Welcome to BlackJack!, Get as close to or 21 as you can but don\'t go over!', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 40))
    text = window.blit(text, text_rect)

def instructions_line_2(font, window):
    '''(str, str) -> ()
    Put the instructions on screen
    '''
    text = font.render(f'The Dealer is also trying to get close to or 21 or they will try to get higher than you to beat you!', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 80))
    text = window.blit(text, text_rect)

def instructions_line_3(font, window):
    '''(str, str) -> ()
    Put the instructions on screen
    '''
    text = font.render(f'You can \'hit\' to get another card if you are under 21 or you can \'stand\' and let the dealer play. Goodluck!', True, (0, 0, 0))
    text_rect = text.get_rect(center=(900/2, 120))
    text = window.blit(text, text_rect)

def Deck_Generate(suit, value):
    '''(list, list) -> list
    Make a deck of cards.
    >>> Deck_Generate('A', '1')
    [('A', '1')]
    >>> Deck_Generate('B', '5')
    [('B', '5')]
    '''
    Deck = []
    for Suit in suit:
        for v in value:
            Deck.append((Suit, v))
    return Deck

def Deck_Shuffle(Deck):
    '''(List) -> List
    Make a shuffled deck of cards.
    '''
    Deck = Deck
    r.shuffle(Deck)
    return Deck

doctest.testmod()