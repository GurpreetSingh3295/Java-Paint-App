package Assignment8_000901702;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
/**
 * An abstract class representing a geometric object that can be drawn on a canvas.
 * This class serves as the base for various shapes that can be drawn.
 * It contains common properties and methods shared by all geometric objects.
 *
 */

public abstract class GeometricObject implements Drawable {
    private double x; // X-coordinate of the object's position
    private double y; // Y-coordinate of the object's position
    private Color fillColor; // Fill color of the object

    /**
     * Constructor to initialize the geometric object.
     *
     * @param x          The x-coordinate of the object's position
     * @param y          The y-coordinate of the object's position
     * @param fillColor  The fill color of the object
     */

    public GeometricObject(double x, double y, Color fillColor) {
        this.x = x;
        this.y = y;
        setFillColor(fillColor);
    }
    /**
     * Sets the fill color of the object.
     *
     * @param fillColor The fill color to be set
     */

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    /**
     * Gets the fill color of the object.
     *
     * @return The fill color of the object
     */

    public Color getFillColor() {
        return fillColor;
    }
    /**
     * Gets the x-coordinate of the object's position.
     *
     * @return The x-coordinate of the object's position
     */


    public double getX() {
        return x;
    }
    /**
     * Gets the y-coordinate of the object's position.
     *
     * @return The y-coordinate of the object's position
     */
    public double getY() {
        return y;
    }
    /**
     * Abstract method to draw the geometric object on a graphics context.
     * Subclasses must implement this method to provide the specific drawing logic.
     *
     * @param gc The graphics context used for drawing
     */


    public abstract void draw(GraphicsContext gc);
    /**
     * Sets the size of the object.
     * This method can be overridden by subclasses to adjust the size as needed.
     *
     * @param max The maximum size to be set
     */
    public void setSize(double max) {
    }
}




