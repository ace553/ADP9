package decrypt;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DecryptView
{

	TextArea _input;
	TextArea _output;
	
	Button _decryptButton;

	Label _sessionLabel;
	
	VBox _box;
	
	public DecryptView()
	{
		_input = new TextArea();

		_output = new TextArea();

		_box = new VBox();
		
		_decryptButton = new Button("Decrypt");

		_sessionLabel = new Label("SessionKey = ");
		
		setupView();
	}

	private void setupView()
	{

		Label l = new Label("Decrypt String with private Key");
		_input.setPrefRowCount(20);
		_input.setWrapText(true);
		_input.setPromptText("Input encrypted base64 String here");
		
		_output.setPrefRowCount(20);
		_output.setEditable(false);
		_output.setWrapText(true);
		_output.setPromptText("Decryped ouput goes here");
		
		_input.setMinWidth(100);
		_output.setMinWidth(100);
		
		_input.setPrefColumnCount(100);
		_output.setPrefColumnCount(100);
		_decryptButton.setDisable(true);
		
		_box.setSpacing(10);
		_box.setPadding(new Insets(10, 10, 10, 10));
		HBox b = new HBox();
		b.setSpacing(10);
		b.getChildren().addAll(l, _decryptButton);
		_box.getChildren().addAll(b, _input, _sessionLabel, _output);
	}
}
