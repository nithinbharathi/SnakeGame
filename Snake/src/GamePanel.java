import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private boolean running;
	private boolean right=true,left = false,up = false,down = false;
	private BodyPart b;
	private ArrayList<BodyPart>snake;
	private int xcoor=10,ycoor=10,size =5,ticks = 0;
	private Apple apple;
	private ArrayList<Apple>apples;
	private Random r;
	
	public GamePanel(){
		setFocusable(true);
		setPreferredSize(new Dimension(500,500));
		addKeyListener(this);
		snake = new ArrayList<>();
		apples = new ArrayList<>();
		r = new Random();
		start();
	}
	
	public void start(){
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		running = false;
		try{
			thread.join();
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	public void tick(){
		if(snake.size() == 0){
			b = new BodyPart(xcoor,ycoor,10);
			snake.add(b);
		}
		ticks++;
		if(ticks>900000){
			if(right)xcoor++;
			if(left)xcoor--;
			if(up)ycoor--;
			if(down)ycoor++;
			ticks  = 0;
			b = new BodyPart(xcoor,ycoor,10);
			snake.add(b);
			if(snake.size()>size){
				snake.remove(0);
			}
		}
		if(apples.size() == 0){
			int xcoor = r.nextInt(49);
			int ycoor = r.nextInt(49);
			apple = new Apple(xcoor,ycoor,10);
			apples.add(apple);
			
		}
		for(int i=0;i<snake.size();i++){
			if(xcoor == snake.get(i).getXcoor() && snake.get(i).getYcoor() == ycoor){
				if(i != snake.size()-1){
					System.out.println("GAME OVER!!");
					stop();
				}
			}
		}
		
		for(int i=0;i<apples.size();i++){
			if(apples.get(i).getXcoor() == xcoor && apples.get(i).getYcoor() == ycoor){
					size++;
					apples.remove(i);
			}
		}
		if(xcoor<0 || xcoor>49 || ycoor<0 || ycoor>49){
			System.out.println("GAME OVER!!");
			stop();
		}
		

	}
	
	public void paint(Graphics g){
		g.clearRect(0, 0, 500, 500);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);
		for(int i=0;i<500/10;i++){
			g.drawLine(i*10, 0, i*10,500);
		}
		for(int i=0;i<500/10;i++){
			g.drawLine(0, i*10, 500,i*10);
		}
		for(int i=0;i<snake.size();i++){
			snake.get(i).draw(g);
		}
		for(int i=0;i<apples.size();i++){
			apples.get(i).draw(g);
		}
	}
	
	@Override
	public void run() {
		while(running){
			tick();
			repaint();
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left){
			right = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_LEFT && !right){
			left= true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_UP && !down){
			right = false;
			up = true;
			left = false;
		}
		if(key == KeyEvent.VK_DOWN && !up){
			right = false;
			down = true;
			left = false;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
