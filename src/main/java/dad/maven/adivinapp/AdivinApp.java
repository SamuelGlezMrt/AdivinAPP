package dad.maven.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	private Label enunciadoLabel;
	private TextField numeroText;
	private Button comprobarButton;
	private VBox root;
	private int numAleatorio = (int) (Math.random() * (100 - 1)) + 1;
	private int intentos = 0;
	private int numero;

	Alert hasGanado = new Alert(AlertType.INFORMATION);
	Alert numMenor = new Alert(AlertType.WARNING);
	Alert numMayor = new Alert(AlertType.WARNING);
	Alert error = new Alert(AlertType.ERROR);

	@Override
	public void start(Stage primaryStage) throws Exception {

		enunciadoLabel = new Label("Introduce un numero del 1 al 100");

		numeroText = new TextField();

		comprobarButton = new Button("Comprobar");
		comprobarButton.setOnAction(e -> onComprobarButtonAction(e));

		root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(enunciadoLabel, numeroText, comprobarButton);
		root.setFillWidth(false);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setScene(scene);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();
	}

	public void onComprobarButtonAction(ActionEvent e) {

		hasGanado.setTitle("AdivinApp");
		hasGanado.setHeaderText("Has ganado");
		hasGanado.setContentText("Solo has necesitado " + intentos + " intentos.");

		numMayor.setTitle("AdivinApp");
		numMayor.setHeaderText("Has fallado");
		numMayor.setContentText("El numero a adivinar es mayor que " + numeroText.getText() + ".");

		numMenor.setTitle("AdivinApp");
		numMenor.setHeaderText("Has fallado");
		numMenor.setContentText("El numero a adivinar es menor que " + numeroText.getText() + ".");

		error.setTitle("AdivinApp");
		error.setHeaderText("Error");
		error.setContentText("El numero introducido no es valido.");

		try {

			numero = Integer.parseInt(numeroText.getText());

			if (numero > 100 || numero < 1) {
				error.showAndWait();
				numeroText.setText("");
			}

			else if (numero > numAleatorio) {
				intentos++;
				numMenor.showAndWait();
				numeroText.setText("");
			}

			else if (numero < numAleatorio) {
				intentos++;
				numMayor.showAndWait();
				numeroText.setText("");
			}

			else if (numero == numAleatorio) {
				intentos++;
				hasGanado.showAndWait();
				numeroText.setText("");
			}
		} catch (NumberFormatException e1) {
			error.setTitle("AdivinApp");
			error.setHeaderText("Error");
			error.setContentText("El numero introducido no es valido.");
			error.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}
