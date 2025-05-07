package iEngine.graphic;

import java.awt.Image;

import iEngine.math.MatrixOld;
import iEngine.math.Point;

/**
 * @param Image         sprite - спрайт<br>
 * @param Point         position - точка центра отрисовки<br>
 * @param Float         angle - угол отрисовки<br>
 * @param Double        scale - занимаемое место по вертикали относительно
 *                      высоты экрана. Изображение рисуется квадратом. Это
 *                      значение является размером по умолчанию. Для Изменения
 *                      размеров используйте матрицу преобразования [scaleX, 0,
 *                      0, scaleY]<br>
 * @param MatrixOld matrix - матрица преобразования<br>
 */
public record RenderContext(Image sprite, Point position, float angle, float width, float height, MatrixOld matrix) {
}
