package it.polimi.ingsw.DiNapoliDiNardo.model.decks;

public class ItemDeck {
	ItemDeck(){
		int i;
		for (i=0; i<2; i++){
			deck.add(new AttackCard());
		for (i=0; i<2; i++){
			deck.add(new TeleportCard());
		for (i=0; i<3; i++){
			deck.add(new SedativesCard());
		for (i=0; i<2; i++){
			deck.add(new LightsCard());
		for (i=0; i<2; i++){
			deck.add(new AdrenalineCard());
		deck.add(new DefenseCard());
		this.shuffleDeck();	
		}
			
}

		//attacco 2
		//teletr 2
		//sedatvi 3
		//spolights 2
		//difesa 1
		//adrenalina 2