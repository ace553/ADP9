package decrypt;

import java.math.BigInteger;
import java.util.Base64;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import material.Feistel;
import material.KeyPair;
import material.RSA;
import model.CypherModel;

public class DecryptController
{

	private DecryptView _view;
	private CypherModel _model;

	public DecryptController(CypherModel m)
	{
		_view = new DecryptView();
		_model = m;
		registerInputTextArea();
		registerMyKeys();
		registerEncryptionButton();
	}

	public VBox getView()
	{
		return _view._box;
	}

	private void registerMyKeys()
	{
		_model._myKeys.addListener(new ChangeListener<KeyPair>()
		{

			@Override
			public void changed(ObservableValue<? extends KeyPair> observable, KeyPair oldValue, KeyPair newValue)
			{
				if (newValue == null)
				{
					_view._decryptButton.setDisable(true);
				} else if (!_view._input.textProperty().get().isEmpty())
				{
					_view._decryptButton.setDisable(false);
				}

			}
		});
	}

	private void registerInputTextArea()
	{
		_view._input.textProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if (newValue.isEmpty())
				{
					_view._decryptButton.setDisable(true);
				} else if (_model._myKeys.getValue() != null)
				{
					_view._decryptButton.setDisable(false);
				}
			}
		});
	}

	private void registerEncryptionButton()
	{
		_view._decryptButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					byte[] encrypted = Base64.getDecoder().decode(_view._input.getText());
					byte[] encrypedSessionKey = new byte[16];
					byte[] string = new byte[encrypted.length - 16];
					System.arraycopy(encrypted, 0, encrypedSessionKey, 0, 16);
					System.arraycopy(encrypted, 16, string, 0, string.length);
					byte[] sessionKey = RSA.getDecrypter().decrypt(encrypedSessionKey, _model._myKeys.get().getPrivate());
					String decrypted = new String(Feistel.getDecrypter().decrypt(string, sessionKey));
					_view._output.setText(decrypted);
					_view._sessionLabel.setText("SessionKey = " + new BigInteger(1,sessionKey));
				} catch (NegativeArraySizeException e)
				{
					Alert negativeArraySize = new Alert(AlertType.ERROR);
					negativeArraySize.setTitle("NegativeArraySizeException");
					negativeArraySize.setHeaderText("Can't decrypt the given String.");
					negativeArraySize.setContentText("String to short.");
					negativeArraySize.showAndWait();
				}
				catch(IllegalArgumentException e)
				{
					Alert illegalArgument = new Alert(AlertType.ERROR);
					illegalArgument.setTitle("IllegalArgumentException");
					illegalArgument.setHeaderText("Can't decrypt the given String.");
					illegalArgument.setContentText("String not compatible with base64.");
					illegalArgument.showAndWait();
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					Alert negativeArraySize = new Alert(AlertType.ERROR);
					negativeArraySize.setTitle("ArrayIndexOutOfBoundsException");
					negativeArraySize.setHeaderText("Can't decrypt the given String.");
					negativeArraySize.setContentText("base64 decoded String does not have the right length.");
					negativeArraySize.showAndWait();
				}
			}
		});
	}
}
