package GUI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class PolynomialCalculator extends Application {
    @Override

    public void start(Stage stage){
        Group group = new Group();
        PolynomialCalculatorView view = new PolynomialCalculatorView();
        PolynomialCalculatorController controller=new PolynomialCalculatorController(view);
        group.getChildren().addAll(view.getAdd(), view.getSub(), view.getMul(),
                view.getDiv(), view.getDer(), view.getInt(), view.getPol1(),
                view.getPol2(), view.getResult(), view.getP1(), view.getP2());
        Color background=Color.rgb(29,29,29);
        Scene scene = new Scene(group,800,600,background);
        stage.setTitle("Polynomial Calculator");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}