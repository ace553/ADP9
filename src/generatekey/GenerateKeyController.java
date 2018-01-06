package generatekey;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import material.RSA;
import model.CypherModel;

public class GenerateKeyController
{

	CypherModel _model;
	GenerateKeyView _view;
	
	public GenerateKeyController(CypherModel model)
	{
		_model = model;
		_view = new GenerateKeyView();
		registerGenerateButton();
	}
	
	private void registerGenerateButton()
	{
		_view._generateButton.setOnAction(new EventHandler<ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent event)
			{
				_model._myKeys.set(RSA.generateKeys());
				_view._output.setText(_model._myKeys.get().getPublic().toString());
				
				_view._pLabel.setText("p\t= "+ _model._myKeys.get()._p);
				_view._qLabel.setText("q\t= "+ _model._myKeys.get()._q);
				_view._nLabel.setText("N\t= "+ _model._myKeys.get()._n);
				_view._phiLabel.setText("\u03A6\t= "+ _model._myKeys.get()._phi);

				_view._eLabel.setText("e\t= "+ _model._myKeys.get()._e);
				_view._dLabel.setText("d\t= "+ _model._myKeys.get()._d);
			}
		});
	}

	public VBox getView()
	{
		return _view._box;
	}
}
