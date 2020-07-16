package server;


import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private int curr = 0;
	private static ArrayList<Card> deck;
    
    public Deck() {
    	
        String[] SUITS = {
            "Spades", "Hearts", "Diamonds", "Clubs"
        };
    	/*
   	 values:
   	1:  "Ace"
   	2: 	"2"
   	3:  "3"
   	4:  "4"
   	5: 	"5"
   	6: 	"6"
   	7: 	"7"
   	8: 	"8"
   	9: 	"9"
   	10:	"10"
   	11:	"Jack"
   	12:	"Queen"
   	13:	"King"
   	
   	suits:
   	0: spades
   	1: hearts
   	2: diamonds
   	3: clubs
   	 */

        String[] RANKS = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13",
        };

        // initialize deck
        int n = SUITS.length * RANKS.length * 10;
        
        deck = new ArrayList<Card>();
        for(int z = 0; z < 10;z++) {
            for (int i = 0; i < RANKS.length; i++) {
                for (int j = 0; j < SUITS.length; j++) {
                	Card newCard =  new Card(Integer.parseInt(RANKS[i]), SUITS[j]);
                    deck.add(newCard);
          
                }
            }
        }
        //shuffle
        Collections.shuffle(deck);
        // print shuffled deck
       
        /*
        for(int i =0; i < n;i++)
        {
        	System.out.println("card " + i + ": " + deck.get(i).getValue() + "of " + deck.get(i).getSuit() );

        }
         */
        

        
    }
    
    public Card getCard() {
    	curr++;
    	if(curr == 519) {
    		this.reshuffle();
    	}
    	return deck.get(curr);	
    }
    
    public void reshuffle() {
    	curr = 0;
    	Collections.shuffle(deck);
    }
}