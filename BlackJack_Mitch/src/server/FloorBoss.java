package server;

public class FloorBoss {
	private int numTables;
	private int activePlayers;
	private Tables[] tables;
	
	public FloorBoss() {
		this.activePlayers = 0;
		this.numTables = 0;
		tables = new Tables[1];
	}
	
	public void addTable() {
		tables[numTables] = new Tables(numTables);
		tables[numTables].startGame();
		numTables++;
	}
	
	public void seatTable(Players player) {
		for(int i = 0; i < numTables; i++) {
			if(!tables[i].isFull) {
				tables[i].addPlayer(player);
				if(tables[i].getNumPlayers() == 1)
					tables[i].startGame();
				activePlayers++;
			}
		}	
	}
	

	
	
	

	public int getNumTables() {
		return numTables;
	}

	public void setNumTables(int numTables) {
		this.numTables = numTables;
	}

	public int getActivePlayers() {
		return activePlayers;
	}

	public void setActivePlayers(int activePlayers) {
		this.activePlayers = activePlayers;
	}

	public Tables[] getTables() {
		return tables;
	}

	public void setTables(Tables[] tables) {
		this.tables = tables;
	}

	
}
