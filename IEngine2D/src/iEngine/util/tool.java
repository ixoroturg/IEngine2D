package iEngine.util;

import iEngine.math.Matrix2D;
import iEngine.math.Point;
import iEngine.math.Vector;

public class tool {
	@SuppressWarnings("unchecked")
	public static <T> T[] createGenericArray(Class<T> type, int length) {
		return (T[]) java.lang.reflect.Array.newInstance(type, length);
	}
	
	public static boolean isLineSegmentCross(Point a, Point b, Point c, Point d) {

		Vector v1 = a.getVector(b);
		Vector v2 = a.getVector(c);
		Vector v3 = a.getVector(d);

		float z1 = v1.mulVector(v2);
		float z2 = v1.mulVector(v3);
		if (z1 * z2 > 0)
			return false;

		v1 = c.getVector(d);
		v2 = c.getVector(a);
		v3 = c.getVector(b);
		z1 = v1.mulVector(v2);
		z2 = v1.mulVector(v3);
		if (z1 * z2 > 0)
			return false;

		if (z1 == z2 && z1 == 0) {
			Point p1 = a.getCenter(b);
			Point p2 = c.getCenter(d);
			if (p1.getDistance(p2) > p1.getDistance(b) + p2.getDistance(c))
				return false;
		}
		return true;
	}
	
	public static Point getLineSegmentCrossPoint(Point a, Point b, Point c, Point d) {
		if (!isLineSegmentCross(a, b, c, d))
			return null;
		float dx1 = b.x - a.x;
		float dy1 = b.y - a.y;

		float dx2 = d.x - c.x;
		float dy2 = d.y - c.y;

		Matrix2D M = new Matrix2D(dy1, dy2, -dx1, -dx2,dy1 * a.x - dx1 * a.y,dy2 * c.x - dx2 * c.y);// .reverse();
		if (M.getDeterminant() != 0) {
			float[] answer = M.resolveSLAE();
			if(answer == null)
				return null;
			if (answer[0] > Math.max(a.x, b.x) || answer[0] < Math.min(a.x, b.x) || answer[0] > Math.max(c.x, d.x)
					|| answer[0] < Math.min(c.x, d.x) || answer[1] > Math.max(a.y, b.y)
					|| answer[1] < Math.min(a.y, b.y) || answer[1] > Math.max(c.y, d.y)
					|| answer[1] < Math.min(c.y, d.y)) {
				return null;
			}

			return new Point(answer[0], answer[1]);
		}
		return null;
	}
}


