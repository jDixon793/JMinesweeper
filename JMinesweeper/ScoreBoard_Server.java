import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class ScoreBoard_Server {

	public static void main(String[] args)throws IOException {
		HighScore h = new HighScore();
		ServerSocket server = new ServerSocket(8888);
		System.out.println("Waiting for clients to connect . . . . . . .");
		
		while(true){
			Socket s = server.accept();
			System.out.println("Client connected.");
			ScoreBoardService service= new ScoreBoardService(s,h);
			Thread t = new Thread(service);
			t.start();
		}

	}

}
