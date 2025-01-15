package Assignment8_000901702;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 * The Square class represents a square shape that can be drawn on a canvas.
 * It extends the GeometricObject class and provides specific methods for rendering a square.
 *
 * Squares are defined by their top-left corner coordinates (x, y), side length (size),
 * and fill color. The class overrides the draw method to render the square on a GraphicsContext.
 *
 */
public class Square extends GeometricObject {
    private double size;

    /**
     * Constructs a new Square object with the specified parameters.
     *
     * @param x         The x-coordinate of the top-left corner of the square.
     * @param y         The y-coordinate of the top-left corner of the square.
     * @param size      The side length of the square.
     * @param fillColor The fill color of the square.
     */
    public Square(double x, double y, double size, Color fillColor) {
        super(x, y, fillColor);
        this.size = size;
    }

    /**
     * Retrieves the fill color of the square.
     *
     * @return The fill color of the square.
     */
    @Override
    public Color getFillColor() {
        return super.getFillColor();
    }

    /**
     * Draws the square on the specified GraphicsContext using the defined fill color.
     * The square is drawn by filling a rectangle with the specified top-left corner,
     * side length, and color.
     *
     * @param gc The GraphicsContext used for drawing.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.fillRect(getX(), getY(), size, size);
    }
}
