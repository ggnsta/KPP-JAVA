package sample;

import javafx.scene.control.Alert;

import java.util.LinkedList;

public class Destroyer extends Ship {
    public Destroyer(String name, int displacement )
    {
        this.Name=name;
        this.Displacement=displacement;
    }
    public void launch_rocket()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Метод Запустить торпеду  класса эсминец");
        alert.setHeaderText(null);
        alert.setContentText("Торпеда запущена");
        alert.showAndWait();
        Rocket rocket=new Rocket();
        rocket.explode();
    };
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
