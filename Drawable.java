package Assignment8_000901702;

import javafx.scene.canvas.GraphicsContext;
/**
 * The Drawable interface defines the contract for objects that can be drawn on a canvas.
 * Any class that implements this interface must provide an implementation for the draw method,
 * which allows the object to be rendered on the provided GraphicsContext.
 *
 * This interface is commonly used in graphical applications where multiple types of objects
 * need to be drawn on a canvas or screen.
 */

public interface Drawable {
    /**
     * Draws the implementing object on the specified GraphicsContext.
     * The implementation of this method should define how the object's representation
     * should be rendered using the given GraphicsContext.
     *
     * @param gc The GraphicsContext used for drawing.
     */
    void draw(GraphicsContext gc);
}
