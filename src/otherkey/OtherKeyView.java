package otherkey;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.KeyOwner;

public class OtherKeyView
{
	
	TableView<KeyOwner> _table;
	TextField _newKey;
	TextField _newName;
	Button _addButton;
	VBox _box;
	
	public OtherKeyView()
	{
		_table = new TableView<>();
		_newKey = new TextField();
		_newName = new TextField();
		_addButton = new Button("Add");
		_box = new VBox();
		setupView();
	}
	
	@SuppressWarnings("unchecked")
	private void setupView()
	{
		_table.setEditable(true);
		_newKey.setPromptText("PublicKey");
		_newName.setPromptText("Name");
		TableColumn<KeyOwner, String> name = new TableColumn<>("Name");
		name.setCellValueFactory(new PropertyValueFactory<KeyOwner, String>("name"));
		name.setCellFactory(TextFieldTableCell.<KeyOwner>forTableColumn());
		TableColumn<KeyOwner, String> key = new TableColumn<>("Public Key");
		key.setCellValueFactory(new PropertyValueFactory<KeyOwner, String>("key"));
		key.setCellFactory(TextFieldTableCell.<KeyOwner>forTableColumn());
		_table.getColumns().addAll(name, key);
		key.setPrefWidth(300);
		_table.setPrefHeight(200);
		_newKey.setPrefWidth(300);
		_addButton.setDisable(true);
		name.setPrefWidth(150);
		
		HBox addBox = new HBox();
		addBox.getChildren().addAll(_newName,_newKey,_addButton);
		
		_box.getChildren().addAll(_table,addBox);
	}
}
