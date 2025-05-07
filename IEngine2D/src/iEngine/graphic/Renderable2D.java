package iEngine.graphic;

import iEngine.graphic.camera.Camera;

/**
 * Интерфейс, который реализуют объекты, которые должны рендериться
 */
public interface Renderable2D {

	/**
	 * @return информацию для рендеринга объекта
	 */
	public Model2D getModel(Camera camera);

}
