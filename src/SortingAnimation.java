
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import javafx.scene.paint.Color;

public class SortingAnimation extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primary) {
        Pane root = new Pane();
        Pane pane = new Pane();
        Pane pane2 = new Pane();
        Pane pane3 = new Pane();

        pane.setLayoutX(150);
        pane.setLayoutY(50);
        pane2.setLayoutX(480);
        pane2.setLayoutY(50);
        pane3.setLayoutX(860);
        pane3.setLayoutY(50);
        pane.setRotate(180);
        pane2.setRotate(180);
        pane3.setRotate(180);

        Label l1 = new Label("bubble sort");
        Label l2 = new Label("selection sort");
        Label l3 = new Label("insertion sort");

        l1.setLayoutX(250);
        l1.setLayoutY(350);
        l2.setLayoutX(550);
        l2.setLayoutY(350);
        l3.setLayoutX(960);
        l3.setLayoutY(350);

        // create an array and populate it from 1 to 50
        final int SIZE = 50;
        Rectangle[] rects = new Rectangle[SIZE];
        Rectangle[] rects2 = new Rectangle[SIZE];
        Rectangle[] rects3 = new Rectangle[SIZE];

        Integer[] array = new Integer[SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        //shuffle the array randomly
        List<Integer> l = Arrays.asList(array);
        Collections.shuffle(l);
        l.toArray(array);
        //create rectangles
        int xPosition = 0;
        for (int i = 0; i < array.length; i++) {
            rects[i] = new Rectangle(xPosition, 50, 5, array[i] * 5);
            rects[i].setStroke(Color.BLACK);
            rects[i].setFill(Color.TRANSPARENT);
            pane.getChildren().add(rects[i]);
            xPosition = xPosition + 5;
        }
        for (int i = 0; i < array.length; i++) {
            rects2[i] = new Rectangle(xPosition, 50, 5, array[i] * 5);
            rects2[i].setStroke(Color.BLACK);
            rects2[i].setFill(Color.TRANSPARENT);
            pane2.getChildren().add(rects2[i]);
            xPosition = xPosition + 5;
        }
        for (int i = 0; i < array.length; i++) {
            rects3[i] = new Rectangle(xPosition, 50, 5, array[i] * 5);
            rects3[i].setStroke(Color.BLACK);
            rects3[i].setFill(Color.TRANSPARENT);
            pane3.getChildren().add(rects3[i]);
            xPosition = xPosition + 5;
        }

        // create a thread that sort the array
         new Thread(new Runnable() {
            public void run() {
                int n = rects.length;
                int pass = 0;
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - 1 - pass; j++) {

                        rects[j].setFill(Color.RED);
                        rects[j+1].setFill(Color.BLUE);
                        try {
                                Thread.sleep(90);
                            } catch (InterruptedException ex) {

                            }
                        if (rects[j].getHeight() > rects[j+1].getHeight()) {
                            double temp = rects[j].getHeight();
                            rects[j].setHeight(rects[j+1].getHeight());
                            rects[j+1].setHeight(temp);

                            
                        }
                        rects[j].setFill(Color.TRANSPARENT);
                        rects[j+1].setFill(Color.TRANSPARENT);
                    }
                    try {
                        Thread.sleep(90);
                    } catch (InterruptedException ex) {

                    }
                    pass++;
                }
            }
        }).start();
 new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < rects2.length - 1; i++) {
                    // Find the minimum element in unsorted array 
                    int min_idx = i;
                    for (int j = i + 1; j < rects2.length; j++) {
                        rects2[j].setFill(Color.BLUE);
                        if (rects2[j].getHeight() < rects2[min_idx].getHeight()) {
                            min_idx = j;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {

                        }
                        rects2[j].setFill(Color.TRANSPARENT);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {

                    }

                    // Swap the found minimum element with the first 
                    // element 
                    double temp = rects2[min_idx].getHeight();
                    rects2[min_idx].setHeight(rects2[i].getHeight());
                    rects2[i].setHeight(temp);
                }

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < rects3.length; i++) {
                    /*storing current element whose left side is checked for its 
             correct position .*/

                    double temp = rects3[i].getHeight();
                    int j = i;

                    /* check whether the adjacent element in left side is greater or
            less than the current element. */
                    while (j > 0 && temp < rects3[j - 1].getHeight()) {
                        rects3[j].setFill(Color.BLUE);
                        // moving the left side element to one position forward.
                        rects3[j].setHeight(rects3[j - 1].getHeight());
                        j = j - 1;
                        try {
                            Thread.sleep(110);
                        } catch (InterruptedException ex) {

                        }
                        rects3[j].setFill(Color.TRANSPARENT);

                    }
                    // moving current element to its  correct position.
                    rects3[j].setHeight(temp);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }).start();
        root.getChildren().addAll(pane, pane2, pane3, l1, l2, l3);
        Scene scene = new Scene(root, 1200, 400);
        primary.setTitle("Animation");
        primary.setScene(scene);
        primary.show();
    }

}
