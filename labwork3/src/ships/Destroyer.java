package ships;

import javafx.scene.control.Alert;
import guns.*;
import components.Rocket;

public class Destroyer extends Ship {
    private Rocket rocket;
    private AGun absGun;
    public Destroyer(String name, int displacement,int gunType )
    {
        this.Name=name;
        this.Displacement=displacement;
        if (gunType==0)absGun=new AirGun();
        if (gunType==1)absGun=new Cannon();
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
    @Override
    public void shooting()
    {
        for(int i=0;i<3;i++)
            absGun.shoot();
    }

}
