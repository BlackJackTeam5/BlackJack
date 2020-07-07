package blackjackself;

public class Deck {
    public static void main(String[] args) {
        String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"
        };

        String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
        };

        // initialize deck
        int n = SUITS.length * RANKS.length * 10;
        
        Card[] deckC = new Card[n];
        //String[] deck = new String[n];
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                //deck[SUITS.length*i + j] = RANKS[i] + " of " + SUITS[j];
                deckC[SUITS.length*i + j] = new Card(Integer.parseInt(RANKS[i]), Integer.parseInt(SUITS[j]));
            }
        }

        // shuffle
        for (int i = 0; i < n; i++) {
            int r = i + (int) (Math.random() * (n-i));
            /*
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
            */
            
            Card temp = deckC[r];
            deckC[r] = deckC[i];
            deckC[i] = temp;
        }

        // print shuffled deck
        for (int i = 0; i < n; i++) {
            System.out.println(deckC[i].getSuit());
            System.out.println(deckC[i].getValue());
        }
    }

}