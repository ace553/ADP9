package otherkey;

import java.util.Base64;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.CypherModel;
import model.KeyOwner;

public class OtherKeyController
{

	OtherKeyView _view;

	CypherModel _model;

	public OtherKeyController(CypherModel model)
	{
		_view = new OtherKeyView();
		_model = model;
		_view._table.setItems(model._keyowners);
		registerAddButton();
		registerTextFieldChange();
		registerSelection();
	}

	private void registerTextFieldChange()
	{
		_view._newKey.textProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				try
				{
					if (Base64.getDecoder().decode(newValue).length != 24)
					{
						_view._addButton.setDisable(true);
					} else if (!_view._newName.getText().isEmpty())
					{
						_view._addButton.setDisable(false);
					}
				} catch (IllegalArgumentException e)
				{

				}
			}
		});

		_view._newName.textProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				if (newValue.isEmpty())
				{
					_view._addButton.setDisable(true);
				} else
				{
					try
					{
						if (Base64.getDecoder().decode(newValue).length == 24)
						{
							_view._addButton.setDisable(false);
						}
					} catch (IllegalArgumentException e)
					{

					}
				}
			}
		});

	}

	private void registerAddButton()
	{
		_view._addButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				addNewKeyOwner();
				_view._newKey.clear();
				_view._newName.clear();
			}
		});
	}

	private void registerSelection()
	{
		_view._table.setOnMouseClicked(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				_model._selected.set(_view._table.getSelectionModel().getSelectedItem());
			}
		});
	}

	private void addNewKeyOwner()
	{
		String key = _view._newKey.getText();
		String name = _view._newName.getText();

		KeyOwner owner = new KeyOwner(key, name);
		_model._keyowners.add(owner);
	}

	public VBox getView()
	{
		return _view._box;
	}
}
