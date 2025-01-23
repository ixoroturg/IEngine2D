package EngineMath;

/**
 * Класс, содержащий полезные статичные методы
 */
public class Function {
	/**
	 * 
	 * @param a - 1-ая точка 1-го отрезка
	 * @param b - 2-ая точка 1-го отрезка
	 * @param c - 1-ая точка 2-го отрезка
	 * @param d - 2-ая точка 2-отрезка
	 * @return true если отрезки пересекаются
	 */
	public static boolean isLineSegmentCross(Point a, Point b, Point c, Point d) {
		
		Vector v1 = a.getVector(b);
		Vector v2 = a.getVector(c);
		Vector v3 = a.getVector(d);
		
		float z1 = v1.mulVector(v2);
		float z2 = v1.mulVector(v3);
		if(z1*z2 > 0)
			return false;
		
		v1 = c.getVector(d);
		v2 = c.getVector(a);
		v3 = c.getVector(b);
		z1 = v1.mulVector(v2);
		z2 = v1.mulVector(v3);
		if(z1*z2 > 0)
			return false;
		if(z1 == z2 && z1 == 0) {
			Point p1 = a.getCenter(b);
			Point p2 = c.getCenter(d);
			if(p1.getDistance(p2) > p1.getDistance(b) + p2.getDistance(c))
				return false;
		}
		return true;
	}
	/**
	 * 
	 * @param a - 1-ая точка 1-го отрезка
	 * @param b - 2-ая точка 1-го отрезка
	 * @param c - 1-ая точка 2-го отрезка
	 * @param d - 2-ая точка 2-отрезка
	 * @return точка пересечения отрезков<br> 
	 * Если отрезки совпадают, то точку, находящуюся посередине между серединами отрезками<br>
	 * null если точки пересечения нет
	 */
	public static Point getLineSegmentCrossPoint(Point a, Point b, Point c, Point d) {
		float a1 = a.y - b.y;
		float b1 = b.x - a.x;
		float c1 = - (a1 * b1);
		
		float a2 = c.y - d.y;
		float b2 = d.x - c.y;
		float c2 = - (a2 * b2);
		
		float det = a1*b2 - a2* b1;
		if(det == 0) {
			Point p1 = a.getCenter(b);
			Point p2 = c.getCenter(d);
			if(p1.getDistance(p2) > p1.getDistance(b) + p2.getDistance(c)) 
				return null;
			return p1.getCenter(p2);
		}	
		return new Point(
				(float)((c1*b2 - b1*c2) / det),
				(float)((a1*c2 - c1*b2) / det)
		);
	}

}
