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
	
	VBox _box;
	
	public DecryptView()
	{
		_input = new TextArea();

		_output = new TextArea();

		_box = new VBox();
		
		_decryptButton = new Button("Decrypt");
		
		setupView();
	}

	private void setupView()
	{

		Label l = new Label("Decrypt String with private Key");
		_input.setPrefRowCount(10);
		_input.setWrapText(true);
		
		_output.setPrefRowCount(10);
		_output.setEditable(false);
		_output.setWrapText(true);

		_input.setMinWidth(50);
		_output.setMinWidth(50);
		_decryptButton.setDisable(true);
		
		_box.setSpacing(10);
		_box.setPadding(new Insets(10, 10, 10, 10));
		HBox b = new HBox();
		b.setSpacing(10);
		b.getChildren().addAll(l, _decryptButton);
		_box.getChildren().addAll(b, _input, _output);
	}
}
