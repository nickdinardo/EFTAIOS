package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ClientSocketInterface implements NetworkInterface {

	private Socket s;
	private PrintWriter out;
	private BufferedReader in; 
	ObjectOutputStream outObj;
	ObjectInputStream inObj;
	boolean stop = false;
	private CommandHandler ComHan = new CommandHandler(this);
	
	public ClientSocketInterface() {
		
	} 
	
	public boolean connect() throws IOException {
		try {
			s = new Socket("127.0.0.1", 8888);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println("Remote initializers not more avaible. Server is not accepting further players connections.");
			return false;
		}
		
		try {
			outObj = new ObjectOutputStream(s.getOutputStream());
			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {			
			e.printStackTrace();
			s.close();
			return false;
		}
		try {
			inObj = new ObjectInputStream(s.getInputStream());
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
		
			String input;
			try {
				while((input = in.readLine()) != null && !stop){
					String[] splitted = input.split("&");
					Map<String, String> params = new HashMap<String, String>();
					for(String s : splitted){
						params.put(s.split("=")[0],s.split("=")[1]);
					}
					this.ComHan.handleCommand(params);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	public PrintWriter getOut() {
		return out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public ObjectOutputStream getOutObj() {
		return outObj;
	}

	public ObjectInputStream getInObj() {
		return inObj;
	}

	
	@Override
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
