
public class HighScore {
	private String name;
	private String score;
	private String date;
	public HighScore() {
		super();
		this.name = "";
		this.score = "";
		this.date = "";
	}
	public HighScore(String name, String score, String date) {
		super();
		this.name = name;
		this.score = score;
		this.date = date;
	}
	public int getScore(){
		return Integer.parseInt(score);
	}
	public String toString(){
		return name+" "+score+" "+date;
	}
}
