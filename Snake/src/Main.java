
import javax.swing.JFrame;

public class Main {
	public Main(){
		JFrame frame = new JFrame();
		GamePanel gamepanel = new GamePanel();
		frame.add(gamepanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TheSnake");
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
