package generatekey;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GenerateKeyView
{
	Button _generateButton;
	TextField _output;

	Label _pLabel;
	Label _qLabel;
	Label _nLabel;
	Label _phiLabel;
	Label _eLabel;
	Label _dLabel;
	
	VBox _box;
	
	public GenerateKeyView()
	{
		_output = new TextField();
		_generateButton = new Button("Generate Keys");
		_box = new VBox();
		_pLabel = new Label("p\t= ");
		_qLabel = new Label("q\t= ");
		_nLabel = new Label("N\t= ");
		_phiLabel = new Label("\u03A6\t= ");
		_eLabel = new Label("e\t= ");
		_dLabel = new Label("d\t= ");
		setupView();
	}
	
	private void setupView()
	{
		_output.setEditable(false);
		_output.setPromptText("Your Public Key");
		_output.setPrefColumnCount(24);
		HBox b = new HBox();
		b.getChildren().addAll(_output, _generateButton);
		b.setSpacing(10);
		_box.setPadding(new Insets(0,10,10,10));
		_box.setSpacing(5);
		_box.getChildren().addAll(new Label("Your Public Key"),b, _pLabel, _qLabel, _nLabel, _phiLabel, _eLabel, _dLabel);
	}
	
}
