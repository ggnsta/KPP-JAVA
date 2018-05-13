package sample;


import javafx.scene.control.Alert;

public class Plane {


    private int num_of_bombs;
    private Carrier home;

    public Plane(Carrier home)// конструктор, передаём объект класса, состоящего из объектов класса Plain
    {
        this.num_of_bombs=3;
        this.home=home;
    }
    public void bombard()
    {
        while(num_of_bombs!=0)
            num_of_bombs--;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Метод 'Бомбардировать' класса самолет");
        alert.setHeaderText(null);
        alert.setContentText("Самолёт произвел бомбардировку");
        alert.showAndWait();

    };
    public void take_off()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Метод 'Взлететь' класса самолет");
        alert.setHeaderText(null);
        alert.setContentText("Самолёт взлетел");
        alert.showAndWait();
    };
    public void to_land()
    {
        if(num_of_bombs==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Метод 'сесть' класса самолет");
            alert.setHeaderText(null);
            alert.setContentText("Самолёт возвращается");
            alert.showAndWait();

            home.get_plane(this);
        }
    };

}
