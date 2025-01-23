package EngineOutput.camera;

import EngineMath.Point;

public record RenderInfo(Point position, float angle, double scale, float[][] basicMatrix) {

}
