package guns;

import javafx.scene.control.Alert;

import java.util.Random;

public class Cannon extends AGun {
    @Override
    public boolean shoot()
    {
        Random random = new Random();
        int num = random.nextInt(100);
        if (num < 50) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Метод 'Выстрелить' класса пушка главного калибра");
            alert.setHeaderText(null);
            alert.setContentText("Есть попадание");
            alert.showAndWait();
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Метод 'Выстрелить' класса пушка главного калибра");
            alert.setHeaderText(null);
            alert.setContentText("Промах");
            alert.showAndWait();
            return false;
        }
    }
}
