package it.polimi.ingsw.dinapolidinardo.client;

import java.io.IOException;


/**
 * Interface that provides methods for both RMI and Socket interfaces
 */
public interface NetworkInterface {
	boolean connect() throws IOException;
	boolean close() throws IOException;
	void startInterface() throws IOException;
	
}
