package Assignment8_000901702;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 * The Circle class represents a circular shape that can be drawn on a canvas.
 * It extends the GeometricObject class and provides specific methods for rendering a circle.
 *
 * Circles are defined by their center coordinates (x, y), radius, and fill color.
 * The class overrides the draw method to render the circle on a GraphicsContext.
 *
 */
public class Circle extends GeometricObject {
    private double radius;

    /**
     * Constructs a new Circle object with the specified parameters.
     *
     * @param x         The x-coordinate of the center of the circle.
     * @param y         The y-coordinate of the center of the circle.
     * @param radius    The radius of the circle.
     * @param fillColor The fill color of the circle.
     */
    public Circle(double x, double y, double radius, Color fillColor) {
        super(x, y, fillColor);
        this.radius = radius;
    }

    /**
     * Retrieves the fill color of the circle.
     *
     * @return The fill color of the circle.
     */
    @Override
    public Color getFillColor() {
        return super.getFillColor();
    }

    /**
     * Draws the circle on the specified GraphicsContext using the defined fill color.
     * The circle is drawn by filling an oval with the specified center, radius, and color.
     *
     * @param gc The GraphicsContext used for drawing.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.fillOval(getX(), getY(), radius * 2, radius * 2);
    }
}
