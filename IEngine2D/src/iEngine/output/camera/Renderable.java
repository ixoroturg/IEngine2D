package iEngine.output.camera;
/**
 * Интерфейс, который реализуют объекты, которые должны рендериться
 */
public interface Renderable {
	/**
	 * @return информацию для рендеринга объекта
	 */
	public RenderInfo getRenderInfo();
}
