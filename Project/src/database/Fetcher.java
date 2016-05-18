package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class Fetcher {
	
	
	public static String download(String host, String path) {
		
		int PORT = 80;
		StringBuffer buf = new StringBuffer();
		
		try (
				
				Socket sock = new Socket(host, PORT);
				OutputStream out = sock.getOutputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					
			) {
			String request = getRequest(host, path);
			out.write(request.getBytes());
			out.flush();
			
			String line = reader.readLine();
			while(line.length() != 0) {				
				line = reader.readLine();
			}
			
			line = reader.readLine();
			buf.append(line);
			
			
		}
		catch (IOException e) {
			e.getMessage();
		} 
		
		
		
		return buf.toString();
		
	}

	private static String getRequest(String host, String path) {
		String request = "GET " + path + " HTTP/1.1" + "\n"		 //GET request
				+ "Host: " + host + "\n" 						//Host header required for HTTP/1.1
				+ "Connection: close\n" 						//make sure the server closes the connection after we fetch one page
				+ "\r\n";								
		return request;
	}

}
