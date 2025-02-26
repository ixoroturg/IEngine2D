package iEngine.element.interfaces;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.*;

@Retention(RUNTIME)
@Target({FIELD, METHOD})
@Inherited
/**
 *	Установите базовое значение для поля;
 */
public @interface BindTickrate {
	
	public byte Byte() default 0;
	public short Short() default 0;
	public int Integer() default 0;
	public long Long() default 0;
	
	public float Float() default 0;
	public double Double() default 0;
}
