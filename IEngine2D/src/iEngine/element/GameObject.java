package iEngine.element;
import java.lang.reflect.*;

import java.util.Arrays;
import iEngine.element.interfaces.*;
import iEngine.render.*;
public abstract class GameObject{
	protected World world = null;
//	public BaseGameObject initialize(World world) {
//		this.world = world;
//		world.getStorage().getGameObjectList().add(this);
//		
//		//onTickChange();
//		
//		if(this instanceof Renderable canRender) {
//			world.getStorage().getRenderList().add(canRender);
//		}
//		if(this instanceof Tickable canTick) {
//			world.getStorage().getTickableList().add(canTick);
//		}
//		if(this instanceof Controlable canControl) {
//			world.getStorage().getControlList().add(canControl);
//		}
//		onCreate();
//		return this;
//	}

	public void onTickChange(int tickrate) {
		if(tickrate == 0)
			return;
		try {
			for(Field field: this.getClass().getDeclaredFields()){
				BindTickrate annotation = null;
				if((annotation = field.getAnnotation(BindTickrate.class)) != null) {
					field.setAccessible(true);
					switch(field.getType().getName()) {
					
						case "float" -> {field.setFloat(this, annotation.Float() / tickrate);}
						case "double" -> {field.setDouble(this, annotation.Double() / tickrate);}
						
						case "byte" -> {field.setByte(this, (byte) (annotation.Byte() / tickrate));}
						case "short" -> {field.setShort(this, (short)(annotation.Short() / tickrate));}
						case "int" -> {field.setInt(this, annotation.Integer() / tickrate);}
						case "long" -> {field.setLong(this, (long) (annotation.Long() / tickrate));}
						
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Настройка тикрейтозависимых элементов провалилась на типе "+this.getClass().getName());
			System.err.println(e.toString());
			//e.printStackTrace();
			System.exit(ErrorCode.BIND_TICKRATE_FAILED);
		}
	}
	
	public World getWorld() {
		return world;
	}
	
	public GameObject setWorld(World world) {
		this.world = world;
		return this;
	}
	public abstract void onCreate();
}
