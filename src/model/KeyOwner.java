package model;

import javafx.beans.property.SimpleStringProperty;

public class KeyOwner
{
	private SimpleStringProperty _key;
	private SimpleStringProperty _name;
	
	public KeyOwner(String key, String name)
	{
		_key = new SimpleStringProperty(key);
		_name = new SimpleStringProperty(name);
	}
	
	public SimpleStringProperty keyProperty()
	{
		return _key;
	}
	
	public SimpleStringProperty nameProperty()
	{
		return _name;
	}
}
