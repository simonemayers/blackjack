package com.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void createFullDeck() {
        // generate cards
        for(int i = 0; i<Values.values().length; i++){
            deck.add(new Card(Suits.DIAMOND, Values.values()[i]));
            deck.add(new Card(Suits.HEART, Values.values()[i]));
            deck.add(new Card(Suits.SPADE, Values.values()[i]));
            deck.add(new Card(Suits.CLUB, Values.values()[i]));
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public Card getCard(int i){
        return this.deck.get(i);
    }

    public void removeCard(int i){
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Creates hand
    public void createHand(Deck mainDeck){
        this.addCard(mainDeck.getCard(0));
        this.addCard(mainDeck.getCard(1));
        mainDeck.removeCard(0);
        mainDeck.removeCard(1);
    }

    //calculates the total value of a players hand
    public int calculateHandValue(){
        int total = 0;
        for(int i=0; i<this.deckSize(); i++){
            total += this.getCard(i).getValue().cardValue;
        }
        return total;
    }

    //show hand in a readable format
    public void showHand(){
        for(int i=0; i<this.deckSize(); i++){
            System.out.println(this.getCard(i));
        }
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
        this.addCard(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    //change ace card value
    public void checkAce(){
        for(int i=0; i<this.deckSize(); i++){
            if(this.getCard(i).getValue().cardValue == 11){
                System.out.println("Would you like to change the value of your Ace for this round? ");
                System.out.println("[1] Yes");
                System.out.println("[2] No");
                Scanner scan = new Scanner(System.in);
                int response = scan.nextInt();
                if(response == 1){
                    this.getCard(i).getValue().cardValue = 1;
                    System.out.println("Your ace now has a value of " + this.getCard(i).getValue().cardValue);
                    System.out.println("Your total hand value is " + this.calculateHandValue());
                } else{
                    System.out.println("Okay. Your ace will keep it's value of " + this.getCard(i).getValue().cardValue);
                }
            }
        }
    }

    // Checks if hand is a winner
    public Boolean isOver(){
        Boolean result = false;
        if(this.calculateHandValue() > 21){
            result = true;
        }
        return result;
    }


}