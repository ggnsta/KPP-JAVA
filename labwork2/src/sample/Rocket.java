package sample;

import javafx.scene.control.Alert;

public class Rocket {
    public void explode(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Метод Взорваться класса торпеда");
        alert.setHeaderText(null);
        alert.setContentText("Торпеда взорвалась");
        alert.showAndWait();
    };
}
