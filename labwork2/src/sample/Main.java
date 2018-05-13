package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;

public class Main extends Application {

    Carrier carrier=new Carrier("Авианосец", 100);
    Destroyer destroyer=new Destroyer("Эсминец",50);
    Cruiser cruiser=new Cruiser("Крейсер",30);
    // Начальное окно
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Лабораторная работа №2");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25,25,25,25));
        grid.setVgap(10);
        grid.setHgap(10);

        Button button_add = new Button("Добавление корабля");
        grid.add(button_add,4,0);
        //кнопка вызова второго окна
        button_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                GridPane secondGrid = new GridPane();
                final ToggleGroup group=new ToggleGroup();
                RadioButton rb1=new RadioButton("Авианосец");
                rb1.setToggleGroup(group);
                rb1.setSelected(true);
                RadioButton rb2=new RadioButton("Крейсер");
                rb2.setToggleGroup(group);
                RadioButton rb3=new RadioButton("Эсминец");
                rb3.setToggleGroup(group);
                secondGrid.add(rb1,0,0);
                secondGrid.add(rb2,0,1);
                secondGrid.add(rb3,0,2);
                Label lab1=new Label("Имя: ");
                lab1.setFont(new Font("Times New Roman",16));
                secondGrid.add(lab1,1,0);
                Label lab2=new Label("Водоизмещение: ");
                lab2.setFont(new Font("Times New Roman",16));
                secondGrid.add(lab2,1,1);
                TextField tf1=new TextField();
                secondGrid.add(tf1,2,0);
                TextField tf2=new TextField();
                secondGrid.add(tf2,2,1);
                Button button=new Button("Добавить");
                secondGrid.add(button,1,3);

                Scene secondScene=new Scene(secondGrid,450,200);
                secondGrid.setPadding(new Insets(25,25,25,25));
                secondGrid.setVgap(10);
                secondGrid.setHgap(10);
                Stage secondWindow = new Stage();
                secondWindow.setTitle("Добавление корабля");
                secondWindow.setScene(secondScene);
                secondWindow.show();
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int displacement=0;
                        String name = tf1.getText();
                        boolean check=false;
                        if (name.trim().length()!=0)
                        {
                            try
                            {
                                displacement = Integer.parseInt((tf2.getText()));
                                check=true;
                            }catch (NumberFormatException excep)
                            {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Ошибка");
                                alert.setHeaderText(null);
                                alert.setContentText("Введите водоизмещение(число)");
                                alert.showAndWait();
                            }

                        }
                        else
                        {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Ошибка");
                            alert.setHeaderText(null);
                            alert.setContentText("Введите имя");
                            alert.showAndWait();
                        }
                        if(check) {
                            if (rb1.isSelected()) {
                                carrier = new Carrier(tf1.getText(), displacement);
                            }
                            if (rb2.isSelected()) {
                                cruiser = new Cruiser(tf1.getText(), displacement);
                            }
                            if (rb3.isSelected()) {
                                destroyer = new Destroyer(tf1.getText(), displacement);
                            }

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Уведомление");
                            alert.setHeaderText(null);
                            alert.setContentText("Корабль добавлен");
                            alert.showAndWait();
                            secondWindow.close();
                        }

                    }
                });
            }
        });

        //группа элементов отображающих класс Авианосец на начальном окне
        Label label_carrier=new Label("Авианосец");
        label_carrier.setFont(new Font("Times New Roman",18));
        grid.add(label_carrier,0,0);
        Button b_carrier_swim=new Button("Плыть");
        grid.add(b_carrier_swim,0,1);
        b_carrier_swim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                carrier.swim();
            }
        });
        Button b_launch_plane=new Button("Запустить самолёт");
        grid.add(b_launch_plane,0,2);
        b_launch_plane.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                carrier.launch_plane();
            }
        });

        //группа элементов отображающих класс Эсминец на начальном окне
        Label lab2=new Label("Эсминец");
        lab2.setFont(new Font("Times New Roman",18));
        grid.add(lab2,1,0);
        Button bd_swim=new Button("Плыть");
        grid.add(bd_swim,1,1);
        bd_swim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                destroyer.swim();
            }
        });
        //метод класса эсминец запустить торпеду вызовет метод класса Торпеда взоварться
        Button bd_launch=new Button("Запустить торпеду");
        grid.add(bd_launch,1,2);
        bd_launch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                destroyer.launch_rocket();
            }
        });

        //группа элементов отображающих класс крейсер на начальном окне
        Label lab3=new Label("Крейсер");
        lab3.setFont(new Font("Times New Roman",18));
        grid.add(lab3,2,0);
        Button bc_swim=new Button("Плыть");
        grid.add(bc_swim,2,1);
        bc_swim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cruiser.swim();
            }
        });

        Scene scene = new Scene(grid, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
