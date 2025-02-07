package iEngine.element;
import java.lang.reflect.*;

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
		
		setTickrate();
		
		if(this instanceof Renderable canRender) {
			world.getStorage().getRenderList().add(canRender);
		}
		if(this instanceof Tickable canTick) {
			world.getStorage().getTickableList().add(canTick);
		}
		if(this instanceof Controlable canControl) {
			world.getStorage().getControlList().add(canControl);
		}
		//System.out.println("До");
		onCreate();
		//System.out.println("После");
		return this;
	}
	
	@BindTickrate
	public void setTickrate() {
		if(world.getTickrate() == 0)
			return;
		int tickrate = world.getTickrate();
		//System.out.println(tickrate);
		try {
			
			for(Field field: this.getClass().getFields()){
				//System.out.println("Проверка: "+field.getName());
				BindTickrate annotation = null;
				if((annotation = field.getAnnotation(BindTickrate.class)) != null) {
					field.setAccessible(true);
					//Field base = this.getClass().getField("_base_"+field.getName());
					//base.setAccessible(true);
					
					//System.out.println("Найденно: "+field.getName());
					
					switch(field.getType().getName()) {
					
						case "float" -> {field.setFloat(this, annotation.Float() / tickrate);}
						case "double" -> {field.setDouble(this, annotation.Double() / tickrate);}
						
						case "byte" -> {field.setByte(this, (byte) (annotation.Byte() / tickrate));}
						case "short" -> {field.setShort(this, (short)(annotation.Short() / tickrate));}
						case "int" -> {field.setInt(this, annotation.Integer() / tickrate);}
						case "long" -> {field.setLong(this, (long) (annotation.Long() / tickrate));}
						
					}
					//System.out.println(field.getName()+" установленно");
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
