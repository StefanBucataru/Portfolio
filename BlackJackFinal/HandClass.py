import random as r
import pygame, os

class Hand:
    
    def __init__(self, Deck, cardheight=90, cardwidth=60):
        self.cardheight = cardheight
        self.cardwidth = cardwidth
        self.Cards = []
        self.amount = 0
        self.CardTotal = 0
        self.Deck = Deck
    
    def Draw_Card(self, amount):
        '''(int) -> list
        Draw cards
        '''
        self.amount += amount
        for x in range(self.amount):
            self.Cards.append(self.Deck.pop(0))

    def showcards(self, cardpositionx, cardpositiony, window):
        '''(int, int, str) -> ()
        Show the cards on screen
        '''
        Index = 0
        for card in range(self.amount):
            Card = self.Cards[Index]
        
            image = pygame.image.load(
                    os.path.join(f'Sprites', Card[0],  f'{Card[0]}{Card[1]}.png'))
            resize = pygame.transform.scale(image, (self.cardwidth, self.cardheight))
            window.blit(resize, (cardpositionx, cardpositiony))
            Index += 1
            cardpositionx += 80
    
    def showcardsDealer(self, cardpositionx, cardpositiony, window, Stand):
        '''(int, int, str, Bool) -> ()
        Show the cards on screen
        '''
        Index = 0
        for x in range(self.amount):
            Card = self.Cards[Index]

            if x == 1:
                if Stand == False:
                    image = pygame.image.load(
                            os.path.join('Sprites', 'BackCard.png'))
                    resize = pygame.transform.scale(image, (self.cardwidth, self.cardheight))
                    window.blit(resize, (cardpositionx, cardpositiony))
                else:
                    image = pygame.image.load(
                    os.path.join(f'Sprites', Card[0], f'{Card[0]}{Card[1]}.png'))
                    resize = pygame.transform.scale(image, (self.cardwidth, self.cardheight))
                    window.blit(resize, (cardpositionx, cardpositiony))
            
            else:
                image = pygame.image.load(
                        os.path.join(f'Sprites', Card[0], f'{Card[0]}{Card[1]}.png'))
                resize = pygame.transform.scale(image, (self.cardwidth, self.cardheight))
                window.blit(resize, (cardpositionx, cardpositiony))
            Index += 1
            cardpositionx += 80
    
    def showcardtotal(self, font, cpx, cpy, window):
        '''(str, int, int, str) -> (int)
        Add the cards and show the total on screen
        '''
        Card = 0
        self.CardTotal = 0
        Aces = 0
        for card in range(self.amount):
            if self.Cards[Card][1] == 'J':
                self.CardTotal += 10
            elif self.Cards[Card][1] == 'Q':
                self.CardTotal += 10
            elif self.Cards[Card][1] == 'K':
                self.CardTotal += 10
            elif self.Cards[Card][1] == 'A':
                Aces += 1
            else:
                self.CardTotal += self.Cards[Card][1]
                
            Card += 1
        
        if Aces >= 1:
            for ace in range(Aces):
                if (self.CardTotal + 11) > 21:
                    self.CardTotal += 1
                else:
                    self.CardTotal += 11
    

        text = font.render(f'Your Current Amount is {self.CardTotal}', True, (0, 0, 0))
        window.blit(text, (cpx, cpy))

    def DealerCardTotal(self):
        '''() -> ()
        Add the cards and show the total on screen for dealer
        '''
        Card = 0
        self.CardTotal = 0
        Aces = 0
        for x in range(self.amount):
            if self.Cards[Card][1] == 'J':
                self.CardTotal += 10
            elif self.Cards[Card][1] == 'Q':
                self.CardTotal += 10
            elif self.Cards[Card][1] == 'K':
                self.CardTotal += 10
            elif self.Cards[Card][1] == 'A':
                Aces += 1
            else:
                self.CardTotal += self.Cards[Card][1]
                
            Card += 1
        
        if Aces >= 1:
            for x in range(Aces):
                if (self.CardTotal + 11) > 21:
                    self.CardTotal += 1
                else:
                    self.CardTotal += 11
                    
    
    def reset(self):
        '''() -> ()
        Reset the Deck
        '''
        self.cardheight = self.cardheight
        self.cardwidth = self.cardwidth
        self.Cards = []
        self.amount = 0
        self.CardTotal = 0
        self.Deck = []

    def Stand(self, amount, cardpositionx, cardpositiony, window):
        '''(int, int, int, str) -> ()
        if the player stands show the dealers card
        '''
        Index = 0
        for x in range(amount):
            Card = self.Cards[Index]

            image = pygame.image.load(
                    os.path.join(f'Sprites', Card[0], f'{Card[0]}{Card[1]}.png'))
            resize = pygame.transform.scale(image, (self.cardwidth, self.cardheight))
            window.blit(resize, (cardpositionx, cardpositiony))

            Index += 1
            cardpositionx += 80