import java.io.*;
import java.util.*;
import java.net.Socket;
public class ScoreBoardService implements Runnable {
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private HighScore hS;

	public ScoreBoardService(Socket aSocket, HighScore hS){
		s = aSocket;
		this.hS = hS;
	}

	public void run() {
		try{
			try{
				in = new Scanner(s.getInputStream());
				out = new PrintWriter(s.getOutputStream());
				doService();
			}
			finally{
				s.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void doService() throws IOException{
		while(true){
			if(!in.hasNext()) return;
			String command = in.next();
			executeCommand(command);
		}
	}
	public void executeCommand(String command) throws FileNotFoundException{
		if(command.equals("GetHighScores")){
			String diff = in.next();
			if(diff.equals("Easy")){
				File f = new File("Source/HS_Easy");
				Scanner read = new Scanner(f);
				while(read.hasNext()){
					out.print(read.nextLine());
				}
				out.flush();
			}
			else if(diff.equals("Med")){
				File f = new File("Source/HS_Med");
				Scanner read = new Scanner(f);
				while(read.hasNext()){
					out.print(read.nextLine());
				}
				out.flush();
				
			}
			else if(diff.equals("Hard")){
				File f = new File("Source/HS_Hard");
				Scanner read = new Scanner(f);
				while(read.hasNext()){
					out.print(read.nextLine());
				}
				out.flush();
				
			}
		}
		else if(command.equals("SendHighScore")){
			String diff = in.next();
			ArrayList<HighScore> scoreList = new ArrayList<HighScore>();
			if(diff.equals("Easy")){
				File f = new File("Source/HS_Easy");
				Scanner read = new Scanner(f);
				while(read.hasNext()){
					String name = read.next();
					String score = read.next();
					String date = read.next();
				}
				PrintWriter pW = new PrintWriter("Source/HS_Easy");
				for(HighScore h: scoreList){
					pW.print(h);
				}
				pW.flush();
				
				
			}
			else if(diff.equals("Med")){
				File f = new File("Source/HS_Med");
				Scanner read = new Scanner(f);
				while(read.hasNext()){
					scoreList.add(new HighScore(read.next(),read.next(),read.next()));
				}
				PrintWriter pW = new PrintWriter("Source/HS_Med");
				for(HighScore h: scoreList){
					pW.print(h);
				}
				pW.flush();
				
				
			}
			else if(diff.equals("Hard")){
				File f = new File("Source/HS_Hard");
				Scanner read = new Scanner(f);
				while(read.hasNext()){
					scoreList.add(new HighScore(read.next(),read.next(),read.next()));
				}
				PrintWriter pW = new PrintWriter("Source/HS_Hard");
				for(HighScore h: scoreList){
					pW.print(h);
				}
				pW.flush();
				
				
			}
		}
	}
	

}
