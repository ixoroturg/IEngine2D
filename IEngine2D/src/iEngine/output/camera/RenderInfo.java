package iEngine.output.camera;

import java.awt.Image;

import iEngine.math.*;
import iEngine.math.Point;
/**
 * @param
 * Image sprite - спрайт<br>
 * @param
 * Point position - точка центра отрисовки<br>
 * @param
 * Float angle - угол отрисовки<br>
 * @param
 * Double scale - занимаемое место по вертикали относительно высоты экрана.
 * Изображение рисуется квадратом.
 * Это значение является размером по умолчанию.
 * Для Изменения размеров используйте матрицу преобразования [scaleX, 0, 0, scaleY]<br>
 * @param
 * MatrixOLDsuka matrix - матрица преобразования<br>
 */
public record RenderInfo(Image sprite, Point position, float angle, float xSize, float ySize, Matrix matrix) {}
