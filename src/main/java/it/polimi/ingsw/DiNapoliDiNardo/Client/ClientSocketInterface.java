package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ClientSocketInterface implements NetworkInterface {

	private Socket s;
	private PrintWriter out;
	private BufferedReader in; 
	boolean stop = false;
	private CommandHandler ComHan = new CommandHandler();
	
	public ClientSocketInterface() {
		
	} 
	
	public boolean connect() throws IOException {
		try {
			s = new Socket("127.0.0.1", 8888);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {			
			e.printStackTrace();
			s.close();
			return false;
		}
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {			
			e.printStackTrace();
			out.close();
			s.close();
			return false;
		}
		return true;
	}

	
	//interaction method
	public void startInterface() {
		
			String input, output;
			try {
				while((input = in.readLine()) != null && !stop){
					String[] splitted = input.split("&");
					Map<String, String> params = new HashMap<String, String>();
					for(String s : splitted){
						params.put(s.split("=")[0],s.split("=")[1]);
					}
					output = this.ComHan.handleCommand(params);
					out.println(output);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	
	
	public boolean close() {
		try{
			in.close();
			out.close();
			s.close();
			return true;
		} catch(IOException ex){
			return false;
		}
	}

	
}
