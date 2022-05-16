package com.blackjack;

import java.util.Scanner;

public class Blackjack {




    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack!");

        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();

        // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
        Deck playerHand = new Deck();
        playerHand.createHand(playingDeck);
        Deck dealerHand = new Deck();
        dealerHand.createHand(playingDeck);


        // Game loops

        System.out.println("How much would you like to start with? ");
        Scanner scan = new Scanner(System.in);
        double total = scan.nextDouble();
        System.out.println("Great! You are starting with $" + total);
        System.out.println("How much would you like to bet for this round? ");
        double wager = scan.nextDouble();
        while(wager%5 != 0){
            System.out.println("You must enter a bet in increments of 5");
            wager = scan.nextDouble();
        }
        System.out.println("Looks good! You are wagering $" + wager + " in this game");
        System.out.println("Enter any number to continue ");
        double input = scan.nextDouble();

        System.out.println("The dealer has: ");
        System.out.println(dealerHand.getCard(0));
        System.out.println("HIDDEN-CARD");
        System.out.println("-------------------");

        System.out.println("You're hand is: ");
        playerHand.showHand();
        System.out.println("Your total hand value is " + playerHand.calculateHandValue());

        while(!(playerHand.isOver() && dealerHand.isOver())){
            playerHand.checkAce();
            System.out.println("Would you like to double down? ");
            System.out.println("[1] yes");
            System.out.println("[2] no");
            double doubleDown = scan.nextDouble();
            if(doubleDown == 1){
                wager *= 2;
                System.out.println("Great! You are now playing for $" + wager);
            }else if(doubleDown ==2){
                System.out.println("Okay. I will keep your bet at $" + wager);
            }
            System.out.println("Would you like to ");
            System.out.println("[1] hit");
            System.out.println("[2] stand");
            double answer = scan.nextDouble();
            if(dealerHand.calculateHandValue() < 17){
                dealerHand.draw(playingDeck);
            }
            if(answer == 1){
                playerHand.draw(playingDeck);
                if(playerHand.isOver() || dealerHand.isOver()){
                    System.out.println("BUST!");
                    System.out.println("--------------------------------");
                    System.out.println("The dealer had: ");
                    dealerHand.showHand();
                    System.out.println("----------------");
                    System.out.println("You had: ");
                    playerHand.showHand();
                    System.out.println("----------------");
                    System.out.println("Your total hand value is " + playerHand.calculateHandValue());

                    if(playerHand.isOver()){
                        System.out.println("The dealer has won the round");
                        total -= wager;
                    } else if(dealerHand.isOver()){
                        System.out.println("You have won the round");
                        total += wager;
                    }
                    System.out.println("You now have $" + total);
                    playerHand.getCard(0).getValue().cardValue = 100;
                    dealerHand.getCard(0).getValue().cardValue = 100;

                } else if(dealerHand.calculateHandValue()> playerHand.calculateHandValue()){
                    System.out.println("The dealer had: ");
                    for(int i=0; i< dealerHand.deckSize()-1; i++){
                        System.out.println(dealerHand.getCard(i));
                    }
                    System.out.println("HIDDEN-CARD");
                    System.out.println("---------------------");
                    System.out.println("You had: ");
                    playerHand.showHand();
                    System.out.println("----------------");
                    System.out.println("Your total hand value is " + playerHand.calculateHandValue());
                } else if(playerHand.calculateHandValue() > dealerHand.calculateHandValue()){
                    System.out.println("The dealer has: ");
                    for(int i=0; i< dealerHand.deckSize()-1; i++){
                        System.out.println(dealerHand.getCard(i));
                    }
                    System.out.println("HIDDEN-CARD");
                    System.out.println("----------------------");
                    System.out.println("You have: ");
                    playerHand.showHand();
                    System.out.println("----------------");
                    System.out.println("Your total hand value is " + playerHand.calculateHandValue());
                }

            } else if(answer ==2){
                if(dealerHand.isOver()){
                    System.out.println("BUST!");
                    System.out.println("--------------------------------");
                    System.out.println("The dealer had: ");
                    dealerHand.showHand();
                    System.out.println("----------------");
                    System.out.println("You had: ");
                    playerHand.showHand();
                    System.out.println("----------------");
                    System.out.println("Your total hand value is " + playerHand.calculateHandValue());
                    System.out.println("You have won the round");
                    total -= wager;
                }else if(dealerHand.calculateHandValue()> playerHand.calculateHandValue()){
                    System.out.println("The dealer had: ");
                    dealerHand.showHand();
                    System.out.println("----------------------");
                    System.out.println("You had: ");
                    playerHand.showHand();
                    System.out.println("----------------");
                    System.out.println("Your total hand value is " + playerHand.calculateHandValue());
                    System.out.println("The dealer has won the round");
                    total -= wager;
                    System.out.println("You now have $" + total);
                } else if(playerHand.calculateHandValue() > dealerHand.calculateHandValue()){
                    System.out.println("The dealer had: ");
                    dealerHand.showHand();
                    System.out.println("--------------------------");
                    System.out.println("You had: ");
                    playerHand.showHand();
                    System.out.println("----------------");
                    System.out.println("Your total hand value is " + playerHand.calculateHandValue());
                    System.out.println("You have won this round");
                    total = total + (wager * 1.5);
                    System.out.println("You now have $" +total);
                }
                playerHand.getCard(0).getValue().cardValue = 100;
                dealerHand.getCard(0).getValue().cardValue = 100;
            }

        }
    }
}