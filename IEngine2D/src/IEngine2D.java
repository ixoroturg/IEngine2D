import EngineMath.*;

import java.util.List;

import EngineElement.*;
import EngineElement.interfaces.World;
import EngineElement.interfaces.baseInstance.BaseWorld;
import EngineOutput.Window;
public class IEngine2D {

	public static void main(String[] args) {
		
		
		Point[] p = new Point[5];
		p[0] = new Point(0,0);
		p[1] = new Point(300,500);
		p[2] = new Point(100, 300);
		p[3] = new Point(-200,-100);
		p[4] = new Point(-400,100);
		
		World w = new BaseWorld();	
		//Hitbox h = new HitboxTest(p, new Point(960,540), 0);
		//w.renderObjects.put(0,List.of((HitboxTest)h));
		new Window(w);
		
	}
}
