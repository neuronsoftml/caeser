package org.ceaser.model;

import org.apache.commons.cli.ParseException;
import org.ceaser.Settings;
import org.ceaser.controller.ControllerConsole;
import org.ceaser.controller.ControllerGUI;

public class Runner {
    private String[] args;
    public Runner(String[] args) {
        this.args = args;
    }
    public void start() throws ParseException {
        checkCLI();
        checkModeOfOperation();
        System.out.println(modeOfOperation);
    }
    private String modeOfOperation;
    private void checkModeOfOperation(){
        this.modeOfOperation = CLI.getModeOfOperation();

        if(modeOfOperation.equals(Settings.MODE_OF_OPERATION_CONSOLE.getValue())){
            startProgramWindowConsole();
        }
        else if(modeOfOperation.equals(Settings.MODE_OF_OPERATION_GUI.getValue())){
            startProgramWindowGUI();
        }

    }
    private CLI CLI;
    private void checkCLI() throws ParseException {
        CLI = new CLI(args);
        CLI.start();
    }

    private void startProgramWindowConsole(){
        ControllerConsole controllerConsole = new ControllerConsole();
        controllerConsole.run();
    }

    private void startProgramWindowGUI(){
        ControllerGUI controllerGUI = new ControllerGUI();
        controllerGUI.run();
    };
}
