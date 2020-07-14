package blackjackself;

public class BlackJack {

	//server + ip + port number
	

}

/*
On login, client initializes and sends Player clas to server, server stores 
just for the sake of keeping track of IDs and such

Server uses player class to verify current turn/player.
Depending on command, do logic.
sends player class back with updated info 
that the clientGUi will use to update the visuals.
*/

//player: hand, id, money;
/*
if(player.command == "deal") {
	Card nextCard = deck.getCard();
	player.hand.addCard(nextCard);
	
	if(player.hand.canContinue) {
		player.bust = true;
		socket.send(player);
	}
}


if(player.getID() == currID) {		
	Card card = deck.nextCard();
	hand.add();
	
	socket.send(Player);
}
*/