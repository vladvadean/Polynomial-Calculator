package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PolynomialCalculatorView {
    private final Button Addition;
    private final Button Subtraction;
    private final Button Multiply;
    private final Button Divide;
    private final Button Derivate;
    private final Button Integrate;
    private final TextField pol1;
    private final TextField pol2;
    private final Label result;
    private final Label p1;
    private final Label p2;

    public Button getAdd() {
        return Addition;
    }

    public Button getSub() {
        return Subtraction;
    }

    public Button getMul() {
        return Multiply;
    }

    public Button getDiv() {
        return Divide;
    }

    public Button getDer() {
        return Derivate;
    }

    public Button getInt() {
        return Integrate;
    }

    public TextField getPol1() {
        return pol1;
    }

    public TextField getPol2() {
        return pol2;
    }

    public Label getResult() {
        return result;
    }

    public void setResult(String result){
        this.result.setText(result);
    }

    public Label getP1() {
        return p1;
    }

    public Label getP2() {
        return p2;
    }

    public PolynomialCalculatorView(){
        Addition = new Button();
        Addition.setText("Add");
        Addition.setTextFill(Color.color(0.468,0.468,0.468));
        Addition.setStyle("-fx-background-color: #3F3F41;");
        Addition.setLayoutX(250);
        Addition.setLayoutY(350);
        Addition.setPrefWidth(100);
        Addition.setOnAction(PolynomialCalculatorController.addition);

        Subtraction = new Button();
        Subtraction.setText("subtract");
        Subtraction.setTextFill(Color.color(0.468,0.468,0.468));
        Subtraction.setStyle("-fx-background-color: #3F3F41;");
        Subtraction.setLayoutX(450);
        Subtraction.setLayoutY(350);
        Subtraction.setPrefWidth(100);
        Subtraction.setOnAction(PolynomialCalculatorController.subtraction);

        Multiply = new Button();
        Multiply.setText("Multiply");
        Multiply.setTextFill(Color.color(0.468,0.468,0.468));
        Multiply.setStyle("-fx-background-color: #3F3F41;");
        Multiply.setLayoutX(250);
        Multiply.setLayoutY(400);
        Multiply.setPrefWidth(100);
        Multiply.setOnAction(PolynomialCalculatorController.multiplication);

        Divide = new Button();
        Divide.setTextFill(Color.color(0.468,0.468,0.468));
        Divide.setStyle("-fx-background-color: #3F3F41;");
        Divide.setText("Divide");
        Divide.setLayoutX(450);
        Divide.setLayoutY(400);
        Divide.setPrefWidth(100);
        Divide.setOnAction(PolynomialCalculatorController.division);

        Derivate = new Button();
        Derivate.setText("Derivate");
        Derivate.setTextFill(Color.color(0.468,0.468,0.468));
        Derivate.setStyle("-fx-background-color: #3F3F41;");
        Derivate.setLayoutX(250);
        Derivate.setLayoutY(450);
        Derivate.setPrefWidth(100);
        Derivate.setOnAction(PolynomialCalculatorController.derivative);

        Integrate = new Button();
        Integrate.setText("Integrate");
        Integrate.setTextFill(Color.color(0.468,0.468,0.468));
        Integrate.setStyle("-fx-background-color: #3F3F41;");
        Integrate.setLayoutX(450);
        Integrate.setLayoutY(450);
        Integrate.setPrefWidth(100);
        Integrate.setOnAction(PolynomialCalculatorController.integral);

        pol1 = new TextField();
        pol1.setStyle("-fx-background-color: #292929;-fx-text-inner-color: #FFFFFF;");
        pol1.setLayoutX(210.0);
        pol1.setLayoutY(150.0);
        pol1.setPrefWidth(400);

        pol2 = new TextField();
        pol2.setStyle("-fx-background-color: #292929;-fx-text-inner-color: #FFFFFF;");
        pol2.setLayoutX(210.0);
        pol2.setLayoutY(200.0);
        pol2.setPrefWidth(400);

        result = new Label();
        result.setTextFill(Color.color(1,1,1));
        result.setLayoutX(210.0);
        result.setLayoutY(100.0);
        result.setPrefWidth(400);

        p1 = new Label("1: ");
        p1.setTextFill(Color.color(0.468,0.468,0.468));
        p1.setLayoutX(190.0);
        p1.setLayoutY(150.0);

        p2 = new Label("2: ");
        p2.setTextFill(Color.color(0.468,0.468,0.468));
        p2.setLayoutX(190.0);
        p2.setLayoutY(200.0);
    }
}