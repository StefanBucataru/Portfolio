#==============================================================================
#Author: Andrew Bucataru
#Title: Black Jack
#Description: This program lets you play Black Jack using pygame!
# ==============================================================================

import pygame, os
from HandClass import *
from Button import *
from Functions import *
#Import needed libraries and files

pygame.init()
#Start Pygame

window = pygame.display.set_mode((900, 500))
fps = 60
pygame.display.set_caption("Black Jack")
tab_icon = pygame.image.load(
    os.path.join('Sprites', "BJ icon.png"))
pygame.display.set_icon(tab_icon)
#Set the window properties.

Hit_Button_Image = pygame.transform.smoothscale(pygame.image.load(
    os.path.join('Sprites', "HitButton.png")), (100, 35))
Stand_Button_Image = pygame.transform.smoothscale(pygame.image.load(
    os.path.join('Sprites', "StandButton.png")), (100, 35))
Play_Again_Image = pygame.transform.smoothscale(pygame.image.load(
    os.path.join('Sprites', "PlayAgain.png")), (100, 35))
Quit_Image = pygame.transform.smoothscale(pygame.image.load(
    os.path.join('Sprites', "Quit.png")), (100, 35))
Start_Image = pygame.transform.smoothscale(pygame.image.load(
    os.path.join('Sprites', "Start.png")), (100, 35))
#Get the button images

HitButton = Button(275, 425, Hit_Button_Image)
StandButton = Button(500, 425, Stand_Button_Image)
PlayAgainButton = Button(275, 375, Play_Again_Image)
QuitButton = Button(500, 375, Quit_Image)
StartButton = Button(275, 375, Start_Image)
#Make the different button instances

Smaller_Font = pygame.font.Font('Abel-Regular.ttf', 16)
Medium_Font = pygame.font.Font('Abel-Regular.ttf', 20)
Bigger_Font = pygame.font.Font('Abel-Regular.ttf', 36)
#Set the different font sizes and the font

value = [2, 3, 4, 5, 6, 7, 8, 9, 10, "J", "Q", "K", "A"]
suit = ["D", "S", "C", "H"]
#Value and suits for cards

Did_Stand = False
#Check if the player picked stand

Start = False
#Check if the player has started

def start():
    '''() -> (bool)
    Draw Cards for the dealer and player.
    '''
    global Player_Hand, Dealer_Hand
    Unshuffled_Deck = Deck_Generate(suit, value)
    Shuffled_Deck = Deck_Shuffle(Unshuffled_Deck)
    Player_Hand = Hand(Shuffled_Deck)
    Dealer_Hand = Hand(Shuffled_Deck)
    Player_Hand.Draw_Card(2)
    Dealer_Hand.Draw_Card(2)
    return True


def main():
    '''() -> ()
    Starts the Pygame program limiting the fps and setting the window size
    '''
    global Did_Stand, Start

    start()
    clock = pygame.time.Clock()
    run = True

    while run:
        clock.tick(fps)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
            #Set the Fps of the screen and check if player quits

            if Start == False:
                window.fill((53, 101, 77))
                instructions(Medium_Font, window)
                instructions_line_2(Medium_Font, window)
                instructions_line_3(Medium_Font, window)
                if StartButton.Click_Check(window):
                    Start = True
                elif QuitButton.Click_Check(window):
                    pygame.quit()

                #Print the instructions on screen and check if they clicked play

            elif Start == True:
                window.fill((53, 101, 77))
                Player_Hand.showcards(400-15*Player_Hand.amount, 300, window)
                Dealer_Cards(Bigger_Font, window)
                Dealer_Hand.showcardsDealer(400-15*Dealer_Hand.amount, 50, window, Did_Stand)
                Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                Dealer_Hand.DealerCardTotal()

                #Start the program and deal the cards

                if HitButton.Click_Check(window):
                    Player_Hand.Draw_Card(1)

                #If player clicks button draw a card

                elif StandButton.Click_Check(window) or Did_Stand == True:
                    Did_Stand = True
                    if Dealer_Hand.CardTotal<17:
                        while Dealer_Hand.CardTotal<17:
                            Dealer_Hand.Draw_Card(1)
                            Dealer_Hand.DealerCardTotal()
                        if Dealer_Hand.CardTotal>=17:
                            if Dealer_Hand.CardTotal > 21:
                                window.fill((53, 101, 77))
                                Win(Bigger_Font, window)
                                Player_Cards(Bigger_Font, window)
                                Dealer_Total(Smaller_Font, window, Dealer_Hand.CardTotal)
                                Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                                Player_Hand.showcards(400-15*Player_Hand.amount, 100, window)
                                if PlayAgainButton.Click_Check(window):
                                    Player_Hand.reset()
                                    Dealer_Hand.reset()
                                    Did_Stand = False
                                    start()
                                if QuitButton.Click_Check(window):
                                    pygame.quit()

                #If player stands draw cards for dealer and check if player wins and give option to play again or quit

                            elif Player_Hand.CardTotal > Dealer_Hand.CardTotal:
                                window.fill((53, 101, 77))
                                Win(Bigger_Font, window)
                                Player_Cards(Bigger_Font, window)
                                Dealer_Total(Smaller_Font, window, Dealer_Hand.CardTotal)
                                Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                                Player_Hand.showcards(400-15*Player_Hand.amount, 100, window)
                                if PlayAgainButton.Click_Check(window):
                                    Player_Hand.reset()
                                    Dealer_Hand.reset()
                                    Did_Stand = False
                                    start()
                                if QuitButton.Click_Check(window):
                                    pygame.quit()

                #If player stands draw cards for dealer and check if player wins and give option to play again or quit

                            elif Dealer_Hand.CardTotal > Player_Hand.CardTotal:
                                window.fill((53, 101, 77))
                                Lost(Bigger_Font, window)
                                Dealer_Total(Smaller_Font, window, Dealer_Hand.CardTotal)
                                Dealer_Cards(Bigger_Font, window)
                                Dealer_Hand.showcardsDealer(400-15*Dealer_Hand.amount, 50, window, Did_Stand)
                                Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                                if PlayAgainButton.Click_Check(window):
                                    Player_Hand.reset()
                                    Dealer_Hand.reset()
                                    Did_Stand = False
                                    start()
                                if QuitButton.Click_Check(window):
                                    pygame.quit()

                #Check if Dealer wins and give option to play again or quit

                            elif Dealer_Hand.CardTotal == Player_Hand.CardTotal:
                                window.fill((53, 101, 77))
                                Tie(Bigger_Font, window)
                                Dealer_Total(Smaller_Font, window, Dealer_Hand.CardTotal)
                                Dealer_Hand.showcardsDealer(400-15*Dealer_Hand.amount, 50, window, Did_Stand)
                                Dealer_Cards(Bigger_Font, window)
                                Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                                if PlayAgainButton.Click_Check(window):
                                        Player_Hand.reset()
                                        Dealer_Hand.reset()
                                        Did_Stand = False
                                        start()
                                if QuitButton.Click_Check(window):
                                    pygame.quit()

                #Check if it is a tie and give player option to play again or quit

                    elif Dealer_Hand.CardTotal>=17:

                        if Player_Hand.CardTotal > Dealer_Hand.CardTotal:
                            window.fill((53, 101, 77))
                            Win(Bigger_Font, window)
                            Player_Cards(Bigger_Font, window)
                            Dealer_Total(Smaller_Font, window, Dealer_Hand.CardTotal)
                            Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                            Player_Hand.showcards(400-15*Player_Hand.amount, 100, window)
                            if PlayAgainButton.Click_Check(window):
                                Player_Hand.reset()
                                Dealer_Hand.reset()
                                Did_Stand = False
                                start()
                            if QuitButton.Click_Check(window):
                                pygame.quit()

                #Check if player wins and give player option to play again or quit
                        elif Dealer_Hand.CardTotal > 21:
                            window.fill((53, 101, 77))
                            Win(Bigger_Font, window)
                            Player_Cards(Bigger_Font, window)
                            Dealer_Total(Smaller_Font, window, Dealer_Hand.CardTotal)
                            Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                            Player_Hand.showcards(400-15*Player_Hand.amount, 100, window)
                            if PlayAgainButton.Click_Check(window):
                                Player_Hand.reset()
                                Dealer_Hand.reset()
                                Did_Stand = False
                                start()
                            if QuitButton.Click_Check(window):
                                pygame.quit()

                        elif Dealer_Hand.CardTotal > Player_Hand.CardTotal:
                            window.fill((53, 101, 77))
                            Lost(Bigger_Font, window)
                            Dealer_Cards(Bigger_Font, window)
                            Dealer_Total(Smaller_Font, window, Dealer_Hand.CardTotal)
                            Dealer_Hand.showcardsDealer(400-15*Dealer_Hand.amount, 50, window, Did_Stand)
                            Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                            if PlayAgainButton.Click_Check(window):
                                Player_Hand.reset()
                                Dealer_Hand.reset()
                                Did_Stand = False
                                start()
                            if QuitButton.Click_Check(window):
                                pygame.quit()

                #Check if player lost and give player option to play again or quit

                        elif Dealer_Hand.CardTotal == Player_Hand.CardTotal:
                            window.fill((53, 101, 77))
                            Tie(Bigger_Font, window)
                            Dealer_Total(Smaller_Font, window, Dealer_Hand.CardTotal)
                            Dealer_Hand.showcardsDealer(400-15*Dealer_Hand.amount, 50, window, Did_Stand)
                            Dealer_Cards(Bigger_Font, window)
                            Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                            if PlayAgainButton.Click_Check(window):
                                    Player_Hand.reset()
                                    Dealer_Hand.reset()
                                    Did_Stand = False
                                    start()
                            if QuitButton.Click_Check(window):
                                pygame.quit()

                #Check if it is a tie and give player option to play again or quit

                elif Player_Hand.CardTotal == 21:
                    window.fill((53, 101, 77))
                    Win(Bigger_Font, window)
                    Dealer_Total(Smaller_Font, window, Dealer_Hand.CardTotal)
                    Player_Cards(Bigger_Font, window)
                    Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                    Player_Hand.showcards(400-15*Player_Hand.amount, 100, window)
                    if PlayAgainButton.Click_Check(window):
                        Player_Hand.reset()
                        Dealer_Hand.reset()
                        Did_Stand = False
                        start()
                    if QuitButton.Click_Check(window):
                        pygame.quit()

                #Check if player wins and give player option to play again or quit

                elif Player_Hand.CardTotal > 21:
                    window.fill((53, 101, 77))
                    Busted(Bigger_Font, window)
                    Player_Cards(Bigger_Font, window)
                    Player_Hand.showcardtotal(Smaller_Font, 365, 270, window)
                    Player_Hand.showcards(400-15*Player_Hand.amount, 100, window)
                    if PlayAgainButton.Click_Check(window):
                        Player_Hand.reset()
                        Dealer_Hand.reset()
                        start()
                    elif QuitButton.Click_Check(window):
                        pygame.quit()
            
                #Check if player busted over 21 and give option to play again or quit
                
            pygame.display.update()

                #Update display
    pygame.quit

if __name__ == "__main__":
    main()
#Run main program