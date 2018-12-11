import javax.swing.*;

public class Frame {

	public static void newFrame(int height,int width, int numMines){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new MenuFrame(height,width,numMines);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("JMinesweeper");
		frame.setIconImage(new ImageIcon(frame.getClass().getResource("Images/NewMine.jpg")).getImage());
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		newFrame(5,5,5);
	}

}
