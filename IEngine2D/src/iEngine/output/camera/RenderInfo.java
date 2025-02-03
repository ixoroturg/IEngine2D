package iEngine.output.camera;

import java.awt.Image;

import iEngine.math.Point;
/**
 * Image sprite<br>
 * Point position<br>
 * float angle<br>
 * double scale<br>
 * float[][] matrix<br>
 */
public record RenderInfo(Image sprite, Point position, float angle, double scale, float[][] basicMatrix) {

}
