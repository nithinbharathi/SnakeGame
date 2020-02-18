import java.awt.Color;
import java.awt.Graphics;

public class BodyPart {
	private int xcoor,ycoor,width,height;
	public BodyPart(int xcoor,int ycoor,int titleSize){
	 this.xcoor  = xcoor;
	 this.ycoor = ycoor;
	 this.width = titleSize;
	 this.height = titleSize;
	}

	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect(xcoor*width, ycoor*height,width,height);
	}
	public int getXcoor() {
		return xcoor;
	}
	public void setXcoor(int xcoor) {
		this.xcoor = xcoor;
	}
	public int getYcoor() {
		return ycoor;
	}
	public void setYcoor(int ycoor) {
		this.ycoor = ycoor;
	}

}
