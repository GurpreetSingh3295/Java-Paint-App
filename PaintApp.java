package Assignment8_000901702;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 * Use this template to create drawings in FX. Change the name of the class and
 * put your own name as author below. Change the size of the canvas and the
 * window title where marked and add your drawing code where marked.
 *
 * @author Gurpreet Singh, 000901702
 */

class FreehandShape extends GeometricObject {
    private List<Point2D> points = new ArrayList<>();

    public FreehandShape(double x, double y, Color fillColor) {
        super(x, y, fillColor);
        points.add(new Point2D(x, y));
    }

    public void addPoint(double x, double y) {
        points.add(new Point2D(x, y));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getFillColor()); // Use getFillColor() method to access the fill color
        gc.setLineWidth(2.0);

        if (points.size() > 1) {
            Point2D prevPoint = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                Point2D currentPoint = points.get(i);
                gc.strokeLine(prevPoint.getX(), prevPoint.getY(), currentPoint.getX(), currentPoint.getY());
                prevPoint = currentPoint;
            }
        }
    }
}
/**0This class represents a simple drawing application that allows users to draw shapes
 * on a canvas using different tools and settings.
*/
public class PaintApp extends Application {
    // List to store drawn shapes
    private List<GeometricObject> shapes = new ArrayList<>();
    private Color currentFillColor = Color.BLACK;
    private GeometricObject currentShape;
    private double startX, startY;
    private boolean isCircleSelected = true;
    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // YOUR CODE STARTS HERE

        // Create UI elements
        Button circleBtn = new Button("Circle");
        Button squareBtn = new Button("Square");
        Button drawBtn = new Button("Draw");
        Button unDrawBtn = new Button("UnDraw");
        TextField xField = new TextField();
        TextField yField = new TextField();
        TextField sizeField = new TextField();
        TextField r = new TextField();
        TextField g = new TextField();
        TextField b = new TextField();
        Label errorLabel = new Label("No Errors");
        errorLabel.setFont(Font.font(14));
        errorLabel.setTextFill(Color.GREEN);

        // Set button actions
        circleBtn.setOnAction(e -> {
            try {
                double redValue = Double.parseDouble(r.getText());
                double greenValue = Double.parseDouble(g.getText());
                double blueValue = Double.parseDouble(b.getText());

                if (!isValidRGBValue(redValue) || !isValidRGBValue(greenValue) || !isValidRGBValue(blueValue)) {
                    errorLabel.setText("Invalid RGB values");
                    errorLabel.setTextFill(Color.RED);
                } else {
                    Color fillColor = Color.rgb((int) redValue, (int) greenValue, (int) blueValue);
                    currentFillColor = fillColor;
                    currentShape.setFillColor(fillColor); // Set fill color for the shape
                    errorLabel.setText("No Errors");
                    errorLabel.setTextFill(Color.GREEN);
                }
                if (sizeCheck(Double.parseDouble(sizeField.getText()))) {
                    errorLabel.setText("NO Error");
                } else {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Size should be greater than 0!");
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid input");
                errorLabel.setTextFill(Color.RED);
            }
        });

        squareBtn.setOnAction(e -> {
            try {
                double redValue = Double.parseDouble(r.getText());
                double greenValue = Double.parseDouble(g.getText());
                double blueValue = Double.parseDouble(b.getText());

                if (!isValidRGBValue(redValue) || !isValidRGBValue(greenValue) || !isValidRGBValue(blueValue)) {
                    errorLabel.setText("Invalid RGB values");
                    errorLabel.setTextFill(Color.RED);
                } else {
                    currentFillColor = Color.rgb((int) redValue, (int) greenValue, (int) blueValue);
                    errorLabel.setText("No Errors");
                    errorLabel.setTextFill(Color.GREEN);
                }
                if (sizeCheck(Double.parseDouble(sizeField.getText()))) {
                    errorLabel.setText("NO Error");
                } else {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Size should be greater than 0!");
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid input");
                errorLabel.setTextFill(Color.RED);
            }
        });
        circleBtn.setOnAction(e -> isCircleSelected = true);
        squareBtn.setOnAction(e -> isCircleSelected = false);

        drawBtn.setOnAction(e -> {
            try {
                double x = Double.parseDouble(xField.getText());
                double y = Double.parseDouble(yField.getText());
                double size = Double.parseDouble(sizeField.getText());

                if (currentShape != null) {
                    shapes.add(currentShape);
                }

                if (isCircleSelected) {
                    currentShape = new Circle(x, y, size, currentFillColor);
                } else {
                    currentShape = new Square(x, y, size, currentFillColor);
                }

                drawShapes(gc);
                errorLabel.setText("No Errors");
                errorLabel.setTextFill(Color.GREEN);
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid input");
                errorLabel.setTextFill(Color.RED);
            }
        });

        unDrawBtn.setOnAction(e -> {
            if (!shapes.isEmpty()) {
                shapes.remove(shapes.size() - 1);
                drawShapes(gc);
            }
            errorLabel.setText("No Errors");
            errorLabel.setTextFill(Color.GREEN);
        });
        canvas.setOnMousePressed(e -> {
            startX = e.getX();
            startY = e.getY();
            currentShape = new FreehandShape(startX, startY, currentFillColor);
            shapes.add(currentShape);
            drawShapes(gc);
        });

        canvas.setOnMouseDragged(e -> {
            if (currentShape instanceof FreehandShape) {
                FreehandShape freehandShape = (FreehandShape) currentShape;
                freehandShape.addPoint(e.getX(), e.getY());
                drawShapes(gc);
            }
        });

        canvas.setOnMouseReleased(e -> {
            currentShape = null;
        });
        // Set UI elements' properties and locations


        xField.setPrefColumnCount(4);
        yField.setPrefColumnCount(4);
        r.setPrefColumnCount(4);
        g.setPrefColumnCount(4);
        b.setPrefColumnCount(4);
        sizeField.setPrefColumnCount(4);
        // Arrange UI components using relocate
        circleBtn.relocate(0, 740);
        squareBtn.relocate(50, 740);
        drawBtn.relocate(680, 740);
        unDrawBtn.relocate(730, 740);
        xField.relocate(170, 740);
        yField.relocate(230, 740);
        r.relocate(470, 740);
        g.relocate(530, 740);
        b.relocate(590, 740);
        sizeField.relocate(350, 740);
        errorLabel.relocate(10, 780);

        // Create layout and scene
        Pane layout = new Pane();
        layout.getChildren().addAll(canvas, circleBtn, squareBtn, drawBtn, unDrawBtn, xField, yField, sizeField, errorLabel, r, g, b);

        Group root = new Group(layout);
        Scene scene = new Scene(root, 1000, 800);

        // Set stage properties
        stage.setTitle("Paint App");
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }
    /**
     * Checks if the provided RGB value is within the valid range.
     *
     * @param value RGB value to be checked
     * @return true if the value is valid, false otherwise
     */
    private boolean isValidRGBValue(double value) {
        return value >= 0 && value <=255 ;

    }
    /**
     * Checks if the provided size value is greater than 0.
     *
     * @param value Size value to be checked
     * @return true if the value is greater than 0, false otherwise
     */
    public boolean sizeCheck(double value){
        if(value>0){
            return true;
        }
        else{return false;}
    }
    /**
     * Draws all shapes on the canvas using the provided graphics context.
     *
     * @param gc Graphics context to draw shapes
     */

    private void drawShapes(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600);
        for (GeometricObject shape : shapes) {
            shape.draw(gc);
        }
        if (currentShape != null) {
            currentShape.draw(gc);
        }
    }
}
