import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class MenuFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	ActionListener listener = new bListener();
	MouseListener listener2 = new bListener();
	private char[][] board;
	private boolean isFirstClick=true;
	private int length;
	private static int h=5;
	private static int w=5;
	private static int m=5;
	private ImageIcon flag = new ImageIcon(getClass().getResource("Images/Flag.jpg"));
	private ImageIcon mine = new ImageIcon(getClass().getResource("Images/NewMine.jpg"));
	private ImageIcon blownFlag = new ImageIcon(getClass().getResource("Images/BlownFlag.jpg"));
	private ImageIcon blownMine = new ImageIcon(getClass().getResource("Images/BlownMine.jpg"));
	private ArrayList<String> nameMines = new ArrayList<String>();
	private ArrayList<String> nameFlags = new ArrayList<String>();
	private long time =System.currentTimeMillis();
	private JMenuItem timer =createTimer("0:00");
	Runnable startTimer = new Runnable() {
		
		public void run() {
			 while(true){
				 String s="0:00";
				 if((int)(System.currentTimeMillis()-time)/1000!=0){
					 s="";
					 int t =(int)(System.currentTimeMillis()-time)/1000;
					 int min = t/60;
					 int sec =t-(min*60);
					 s=min+":";
					 if(sec<10)
						 s+="0"+sec;
					 else
						 s+=sec;
				 	}
					 
				 timer.setText(s);
			   }
			 
			
		}
		};
		Runnable customGame = new Runnable() {
			
			public void run() {
				 while(true){
					 String s="0:00";
					 if((int)(System.currentTimeMillis()-time)/1000!=0){
						 s="";
						 int t =(int)(System.currentTimeMillis()-time)/1000;
						 int min = t/60;
						 int sec =t-(min*60);
						 s=min+":";
						 if(sec<10)
							 s+="0"+sec;
						 else
							 s+=sec;
					 	}
						 
					 timer.setText(s);
				   }
				 
				
			}
			};
		Thread t = new Thread(startTimer);
	/**
	 * Improvments Yet to Be Made:
	 * High Scores
	 * 	--File with High Scores
	 *  --Keep counter for high times
	 *  --Compare the time to top scores
	 *  --Newest scores always win
	 *  --New Menu item for scores
	 *  ??Encryption so the files can just be edited
	 * Difficulty Levels
	 *  --Resize game board based on level
	 *  --set board size with set num of Mines
	 *  --High Score for each Diff level
	 *  --variables for the different sizes
	 *  --adjust the size of the board to fit 
	 *  --need to find ratio for size
	 *  --switch statment to change the variables height width numMines length and Frame width and height
	 * Custom Game
	 *  --next step after difficulty
	 *  --manually be able to set Hight Width and num Mines
	 *  ??No High Score
	 *  --need to find ratio for size (Height and Width * 47 for size)
	 * More Images
	 *  --for all numbers 1-8
	 *  --delete game type
	 */
	private ArrayList<JButton> field = new ArrayList<JButton>();
	
	public MenuFrame(int height,int width, int numMines) {
		timer.setName("Start");

		h=height;
		w=width;
		m=numMines;
		length=w*h;
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createGameMenu());
		menuBar.add(timer);
		setLayout(new GridLayout(h, w));
		for (int i = 0; i < length; i++) {
			JButton d = new JButton();
			d.setIcon(mine);
			field.add(d);

		}
		for (int i = 0; i < length; i++) {
			field.get(i).setBackground(Color.WHITE);
			field.get(i).setName(Integer.toString(i));
			field.get(i).setActionCommand(Integer.toString(i));
			add(field.get(i));
			field.get(i).addActionListener(listener);
			field.get(i).addMouseListener(listener2);
		}
		nameMines = new ArrayList<String>();
		nameFlags = new ArrayList<String>();
		setLayout(new GridLayout(h, w));
		setSize(w*50, h*50+50);
		board = Minesweeper.makeBoard(h, w, m);
		for(int i=0;i<length;i++){
			int hig2=i/h;
			int wid2=i%w;
			if(board[hig2][wid2]=='X'){
				nameMines.add(Integer.toString(i));
			}	
		}
		
		for(int i =0;i<length;i++){
			field.get(i).setText("");
			field.get(i).setIcon(mine);
			field.get(i).setEnabled(true);
		}
		
		
	}
	public void newGame(int height,int width, int numMines){
	}
	public void clearGame(){
		this.dispose();
	}

	class bListener implements ActionListener, MouseListener {
		public void actionPerformed(ActionEvent event) {	
		}

		@SuppressWarnings("deprecation")
		public void mouseClicked(MouseEvent e) {
			if(timer.getName().equals("Start")){
			timer.setName("Started");
			time =System.currentTimeMillis();
			t.start();
			}

			if (e.getModifiers() == MouseEvent.BUTTON3_MASK){
				for(int i =0;i<length;i++){
					if(field.get(i).getName()==e.getComponent().getName()&&field.get(i).isEnabled()){
						if(field.get(i).getText().equals("")&&!nameFlags.contains(Integer.toString(i))){// Every Time you make a F add to an ArrayList if the F array matches the X array you win
							
							field.get(i).setIcon(null);
							field.get(i).setIcon(flag);
							nameFlags.add(e.getComponent().getName());
							if(nameFlags.containsAll(nameMines)&&nameFlags.size()==m){
								t.stop();
								JOptionPane.showMessageDialog(null, "YOU WIN!");
								board=Minesweeper.makeBoard(h,w,m);
								for(int u=0;u<length;u++){
									int hig2=u/h;
									int wid2=u%w;
									if(board[hig2][wid2]=='X'){
										nameMines.add(Integer.toString(u));
									}	
								}
								clearGame();Frame.newFrame(h,w,m);
								
								
								
								
							}
							
						}
						else{
							field.get(i).setIcon(mine);
							nameFlags.remove(e.getComponent().getName());
							
						}
					}
					
				}
			}
			if (e.getModifiers() == MouseEvent.BUTTON1_MASK){
				int num=Integer.parseInt(e.getComponent().getName());
				int hig=num/w;
				int wid=num%h;
				if(board[hig][wid]!='0'){
					if(board[hig][wid]!='X'){
						field.get(num).setIcon(null);
						field.get(num).setText(""+board[hig][wid]);
						field.get(num).setEnabled(false);
						isFirstClick=false;
					}
					else if(!isFirstClick||m==h*w){
						for(int i =0;i<length;i++){//Where you tell the person they lost
							
							int num2=Integer.parseInt((field.get(i)).getName());
							int hig2=num2/h;
							int wid2=num2%w;

							if(board[hig2][wid2]=='X'){
								if(!nameFlags.contains(Integer.toString(num2))){
									field.get(num2).setIcon(blownMine);
								}
							}
							else if(nameFlags.contains(Integer.toString(num2))){
								if(field.get(num2).isEnabled()){
									field.get(num2).setText("");
									field.get(num2).setIcon(blownFlag);
								}
							}
						}
						t.stop();
						JOptionPane.showMessageDialog(null, "YOU LOSE");
						//stuff for new game here
						//newGame(5,5,5);
						
						clearGame();Frame.newFrame(h, w, m);
						
					}
					else{
						isFirstClick=false;
							while(board[hig][wid]=='X'){
								board = Minesweeper.makeBoard(h, w, m);
							}
						nameMines=new ArrayList<String>();
						for(int i=0;i<length;i++){
							int hig2=i/h;
							int wid2=i%w;
							if(board[hig2][wid2]=='X'){
								nameMines.add(Integer.toString(i));
							}	
						}
						if(board[hig][wid]!='0'){
						field.get(num).setIcon(null);
						field.get(num).setText(""+board[hig][wid]);
						field.get(num).setEnabled(false);
						}
						else{
							ArrayList<P> v = new ArrayList<P>();
							field.get(num).setEnabled(false);
							P.clrZero(board, field, hig, wid, h-1, w-1, v);
						}
					}
					
				}
				else{
					ArrayList<P> v = new ArrayList<P>();
					field.get(num).setEnabled(false);
					P.clrZero(board, field, hig, wid, h-1, w-1, v);
				}

			}
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenu createGameMenu() {
		JMenu menu = new JMenu("Game");
		menu.add(createNewGameItem("New Game"));
		menu.add(createHelpItem("Help"));
		menu.add(createDifMenu());
		menu.add(createCustomGameItem("Custom Game"));
		menu.add(createFileExitItem());
		return menu;
	}

	public JMenu createHelpMenu() {
		JMenu menu = new JMenu("Info");
		menu.add(createHelpItem("Help"));

		return menu;
	}

	public JMenu createDifMenu() {
		JMenu menu = new JMenu("Difficulty");
		menu.add(createEasyItem("Easy"));
		menu.add(createMeduimItem("Intermediate"));
		menu.add(createHardItem("Hard"));
		return menu;
	}

	final JTextField hh = new JTextField("");
	final JTextField mm = new JTextField("");
	final JTextField ww = new JTextField("");

	public JMenuItem createNewGameItem(final String name) {
		JMenuItem item = new JMenuItem(name);
		class easyListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				clearGame();Frame.newFrame(h, w, m);
			}
		}

		ActionListener listener = new easyListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem createCustomGameItem(final String name) {
		 JMenuItem item = new JMenuItem(name);
		class MenuItemListener extends JFrame implements ActionListener, MouseListener {

			private static final long serialVersionUID = 1L;

			MenuItemListener() {
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(4, 4));
				JLabel h = new JLabel("Height");
				JLabel w = new JLabel("Width");
				JLabel m = new JLabel("# Mines");
				JButton b = new JButton("Make");
				b.setName("Runing");
				b.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent e) {
						int s = Integer.parseInt(hh.getText());
						int s2 = Integer.parseInt(mm.getText());
						int s1 = Integer.parseInt(ww.getText());
						if(s1>s){
							int temp =s1;
							s1=s;
							s=temp;
						}
                        if(s2>s1*s){
                            s2=s1*s;
                        }
						clearGame();Frame.newFrame(s, s1, s2);
						
						

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
				panel.add(h);
				panel.add(hh);
				panel.add(w);
				panel.add(ww);
				panel.add(m);
				panel.add(mm);
				panel.add(b);
				b.setToolTipText("Starts A New Game With Your Settings");
				add(panel);
				repaint();
				if(b.getName().equals("Close"))
					this.dispose();


			}
			public void actionPerformed(ActionEvent event) {
				MenuItemListener m = new MenuItemListener();
				m.setLocation(250, 250);
				m.setVisible(true);
				m.setSize(175, 100);
				m.setResizable(false);
				if(m.getName().equals("Kill"))
					m.dispose();
				
				
				
			
				
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		this.repaint();
		return item;
	}

	public JMenuItem createEasyItem(final String name) {
		JMenuItem item = new JMenuItem(name);
		class easyListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				h=5;
				w=5;
				m=10;
				clearGame();Frame.newFrame(h, w, m);

			}
		}

		ActionListener listener = new easyListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem createMeduimItem(final String name) {
		JMenuItem item = new JMenuItem(name);
		class easyListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				h=10;
				w=10;
				m=15;
				clearGame();Frame.newFrame(h, w, m);



			}
		}

		ActionListener listener = new easyListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem createHardItem(final String name) {
		JMenuItem item = new JMenuItem(name);
		class easyListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				h=15;
				w=15;
				m=30;
				clearGame();Frame.newFrame(h, w, m);
				
				
			}
		}

		ActionListener listener = new easyListener();
		item.addActionListener(listener);
		return item;
	}
	public JMenuItem createTimer(final String name){
		JMenuItem item = new JMenuItem(name);
		return item;
	}
	public JMenuItem createHelpItem(final String name) {
		JMenuItem item = new JMenuItem(name);
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				String s = "";
				s += "\nHelp Menu";
				s += "\n--------------------------";
				s += "\nRight Click to mark mines";
				s += "\nLeft Click to uncover";
				s += "\nWhen a mine is clicked you loss";
				s += "\nFlag all mines to win";
				JOptionPane.showMessageDialog(null, s);
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}



	public static class P {
		int x = 0;
		int y = 0;

		public P(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object obj) {
			P other = (P) obj;
			if (other.x == x && other.y == y)
				return true;
			return false;
		}

		public static void clrZero(char[][] board, ArrayList<JButton> j,
				int hpos, int wpos, int maxh, int maxw, ArrayList<P> v) {
			v.add(new P(hpos, wpos)); // add current pos to alread checked
			j.get((hpos * (maxw + 1)) + (wpos)).setText(board[hpos][wpos] + "");
			if ((!j.get((hpos * (maxw + 1)) + (wpos)).getText().equals(""))
					&& j.get((hpos * (maxw + 1)) + (wpos)).getText().charAt(0) == '0') {
				j.get((hpos * (maxw + 1)) + (wpos)).setText("");
				j.get((hpos * (maxw + 1)) + (wpos)).setIcon(null);
				j.get((hpos * (maxw + 1)) + (wpos)).setEnabled(false);
			} else if (!(j.get((hpos * (maxw + 1)) + (wpos)).getText()
					.equals(""))) {
				j.get((hpos * (maxw + 1)) + (wpos)).setIcon(null);
				j.get((hpos * (maxw + 1)) + (wpos)).setEnabled(false);

			}

			if (!j.get((hpos * (maxw + 1)) + (wpos)).getText().equals("")
					&& (Integer.parseInt(j.get((hpos * (maxw + 1)) + (wpos))
							.getText())) > 0) {// base case if current pos != 0
												// change it and leave
				return;
			}
			if (wpos + 1 <= maxw && !v.contains(new P(hpos, wpos + 1))) {
				clrZero(board, j, hpos, wpos + 1, maxh, maxw, v);
			}
			if (wpos - 1 >= 0 && !v.contains(new P(hpos, wpos - 1))) {
				clrZero(board, j, hpos, wpos - 1, maxh, maxw, v);
			}
			if (hpos + 1 <= maxh && !v.contains(new P(hpos + 1, wpos))) {
				clrZero(board, j, hpos + 1, wpos, maxh, maxw, v);
			}
			if (hpos - 1 >= 0 && !v.contains(new P(hpos - 1, wpos - 1))) {
				clrZero(board, j, hpos - 1, wpos, maxh, maxw, v);
			}
			if (wpos + 1 <= maxw && hpos + 1 <= maxh
					&& !v.contains(new P(hpos + 1, wpos + 1))) {
				clrZero(board, j, hpos + 1, wpos + 1, maxh, maxw, v);
			}
			if (wpos - 1 >= 0 && hpos - 1 >= 0
					&& !v.contains(new P(hpos - 1, wpos - 1))) {
				clrZero(board, j, hpos - 1, wpos - 1, maxh, maxw, v);
			}
			if (wpos + 1 <= maxw && hpos - 1 >= 0
					&& !v.contains(new P(hpos - 1, wpos + 1))) {
				clrZero(board, j, hpos - 1, wpos + 1, maxh, maxw, v);
			}
			if (wpos - 1 >= 0 && hpos + 1 <= maxh
					&& !v.contains(new P(hpos + 1, wpos - 1))) {
				clrZero(board, j, hpos + 1, wpos - 1, maxh, maxw, v);
			}
			return;

		}
	}
}
