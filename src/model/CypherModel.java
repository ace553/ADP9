package model;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import material.KeyPair;

public class CypherModel
{

	public SimpleObjectProperty<KeyPair> _myKeys;
	public ObservableList<KeyOwner> _keyowners;
	
	public SimpleObjectProperty<KeyOwner> _selected;
	
	public CypherModel()
	{
		_myKeys = new SimpleObjectProperty<KeyPair>(null);
		_keyowners = FXCollections.observableArrayList();
		_selected = new SimpleObjectProperty<>();
	}
	
}
