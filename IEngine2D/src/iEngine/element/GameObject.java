package iEngine.element;
import java.lang.reflect.*;
import java.util.Arrays;

import iEngine.element.interfaces.BindTickrate;
import iEngine.element.interfaces.Controlable;
import iEngine.element.interfaces.Tickable;
import iEngine.element.interfaces.World;
import iEngine.output.camera.*;
public abstract class GameObject{
	protected World world = null;
	public GameObject initialize(World world) {
		this.world = world;
		world.getStorage().getGameObjectList().add(this);
		
		//onTickChange();
		
		if(this instanceof Renderable canRender) {
			world.getStorage().getRenderList().add(canRender);
		}
		if(this instanceof Tickable canTick) {
			world.getStorage().getTickableList().add(canTick);
		}
		if(this instanceof Controlable canControl) {
			world.getStorage().getControlList().add(canControl);
		}
		onCreate();
		return this;
	}
	
	public void onTickChange() {
		if(world.getTickrate() == 0)
			return;
		int tickrate = world.getTickrate();
		try {
			//System.out.println(Arrays.toString(this.getClass().getDeclaredFields()));
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
	protected abstract void onCreate();
}
