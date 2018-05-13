package sample;

import javafx.scene.control.Alert;

public class Cruiser extends Ship {
    public Cruiser(String name, int displacement )
    {
        this.Name=name;
        this.Displacement=displacement;
    }
    @Override
    public void swim()
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Уведомление");
        alert.setHeaderText(null);
        alert.setContentText( this.Name+" плывет");
        alert.showAndWait();
    }
}
