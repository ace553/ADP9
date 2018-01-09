package application;

import decrypt.DecryptController;
import encrypt.EncryptController;
import generatekey.GenerateKeyController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CypherModel;
import otherkey.OtherKeyController;

public class Main extends Application
{
	private EncryptController _encrypt;
	private DecryptController _decrypt;
	
	private GenerateKeyController _generateKey;
	private OtherKeyController _otherKey;
	
	private CypherModel _model;
	
	
	@Override
	public void start(Stage stage)
	{
		_model = new CypherModel();
		_encrypt = new EncryptController(_model);
		_decrypt = new DecryptController(_model);
		_generateKey = new GenerateKeyController(_model);
		_otherKey = new OtherKeyController(_model);

		HBox box = new HBox();
		HBox configBox = new HBox();
		configBox.getChildren().addAll(_generateKey.getView(),_otherKey.getView());

		configBox.setPadding(new Insets(10,10,10,10));
		configBox.setSpacing(70);
		VBox b = new VBox();
		b.getChildren().addAll(configBox,box);
		
		
		Scene scene = new Scene(b);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Encryption Tester");

		box.getChildren().addAll(_decrypt.getView(), _encrypt.getView());
		stage.setScene(scene);

		stage.sizeToScene();
		stage.getIcons().add(new Image("images/key-variant.png"));
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	

}
