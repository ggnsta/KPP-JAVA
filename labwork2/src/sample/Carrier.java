
package sample;
import javafx.scene.control.*;
import java.util.*;


public class Carrier extends Ship
{
    private Queue<Plane> planes;

    public Carrier(String name, int displacement )
    {
        this.Name=name;
        this.Displacement=displacement;
        planes=new LinkedList<Plane>();
        for(int i=0;i<3;i++)
        planes.add(new Plane(this));
    }
    public void launch_plane()
    {
        Plane tmp=planes.poll();
        if(tmp!=null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Метод 'запустить' класса авианосец");
            alert.setHeaderText(null);
            alert.setContentText("Запускаем самолёт");
            alert.showAndWait();

            tmp.take_off();
            tmp.bombard();
            tmp.to_land();

        }
    };
    public void get_plane(Plane tmp)
    {
        planes.add(tmp);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Метод 'принять на палубу' класса авианосец");
        alert.setHeaderText(null);
        alert.setContentText("Самолёт  совершил вылет и вернулся");
        alert.showAndWait();
    };
    @Override
    public void swim()
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Уведомление");
        alert.setHeaderText(null);
        alert.setContentText( this.Name+" плывет");
        alert.showAndWait();
    };
}
