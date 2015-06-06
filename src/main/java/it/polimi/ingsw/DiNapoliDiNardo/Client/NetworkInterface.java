package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.io.IOException;


public interface NetworkInterface {
	boolean connect() throws IOException;
	boolean close() throws IOException;
	void startInterface() throws IOException;
	
}
