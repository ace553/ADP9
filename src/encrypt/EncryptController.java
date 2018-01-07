package encrypt;

import java.math.BigInteger;
import java.util.Base64;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import material.Feistel;
import material.KeyPair;
import material.PublicKey;
import material.RSA;
import model.CypherModel;
import model.KeyOwner;

public class EncryptController
{

	private EncryptView _view;
	private CypherModel _model;

	public EncryptController(CypherModel m)
	{
		_view = new EncryptView();
		_model = m;
		registerInputTextArea();
		registerMyKeys();
		registerEncryptionButton();
		registerSelecedChange();
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
					_view._encryptButton.setDisable(true);
				} else if (!_view._input.textProperty().get().isEmpty())
				{
					_view._encryptButton.setDisable(false);
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
					_view._encryptButton.setDisable(true);
				} else if (_model._selected.get() != null)
				{
					_view._encryptButton.setDisable(false);
				}
			}
		});
	}

	private void registerSelecedChange()
	{
		_model._selected.addListener(new ChangeListener<KeyOwner>()
		{

			@Override
			public void changed(ObservableValue<? extends KeyOwner> observable, KeyOwner oldValue, KeyOwner newValue)
			{
				if(newValue != null)
				{
					_view._eLabel.setText("Encrypting String for: "+ newValue.nameProperty().get());
					if(!_view._input.getText().isEmpty())
					{
						_view._encryptButton.setDisable(false);
					}
				}
				else
				{
					_view._encryptButton.setDisable(true);
				}
				
			}
		});
	}
	private void registerEncryptionButton()
	{
		_view._encryptButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				byte[] sessionKey = Feistel.generateSessionKey();
				byte[] encrypedString = Feistel.getEncrypter().encrypt(_view._input.getText().getBytes(), sessionKey);
				byte[] encrypedKey = RSA.getEncrypter().encrypt(sessionKey, PublicKey.fromBase64(_model._selected.get().keyProperty().get()));

				System.out.println(new BigInteger(1, sessionKey));
				byte[] array = new byte[16 + encrypedString.length];
				System.out.println("Array length: " + array.length);
				System.arraycopy(encrypedKey, 0, array, 0, encrypedKey.length);
				System.arraycopy(encrypedString, 0, array, 16, encrypedString.length);
				
				String encrypted = Base64.getEncoder().encodeToString(array);
				_view._sessionLabel.setText("SessionKey = " + new BigInteger(1,sessionKey));
				_view._output.setText(encrypted);
			}
		});
	}
}
