package org.ceaser.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ceaser.setting.ConfigGUI;

public class ControllerGUI extends Application {
    public void run() {
        System.out.println("Запуск програми в графічному інтерфейсі");
    }

    @Override
    public void start(Stage stage) throws Exception {
        creationOfTheMainInterface(stage);
    }


    private void creationOfTheMainInterface(Stage stage){
        stage.setTitle(ConfigGUI.NAME_PROGRAM.getStr() + ConfigGUI.VERSION_PROGRAM.getStr());
    }
}
