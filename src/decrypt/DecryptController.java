package decrypt;

import java.util.Base64;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
				byte[] encrypted = Base64.getDecoder().decode(_view._input.getText());
				byte[] encrypedSessionKey = new byte[16];
				byte[] string = new byte[encrypted.length - 16];
				System.arraycopy(encrypted, 0, encrypedSessionKey, 0, 16);
				System.arraycopy(encrypted, 16, string, 0, string.length);
				byte[] sessionKey = RSA.getDecrypter().decrypt(encrypedSessionKey, _model._myKeys.get().getPrivate());
				String decrypted = new String(Feistel.getDecrypter().decrypt(string, sessionKey));
				_view._output.setText(decrypted);
			}
		});
	}
}
