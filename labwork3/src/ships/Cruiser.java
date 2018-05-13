package ships;

import javafx.scene.control.Alert;
import guns.*;

public class Cruiser extends Ship {
    private AGun absGun;
    public Cruiser (String name, int displacement,int gunType )
    {
        this.Name=name;
        this.Displacement=displacement;
        if (gunType==0)absGun=new AirGun();
        if (gunType==1)absGun=new Cannon();
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
    @Override
    public void shooting()
    {
        for(int i=0;i<3;i++)
            absGun.shoot();
    }
}
