package org.ceaser.model;

import org.apache.commons.cli.ParseException;
import org.ceaser.setting.Settings;
import org.ceaser.controller.ControllerConsole;
import org.ceaser.controller.ControllerGUI;

public class Runner {
    private String[] args;
    public Runner(String[] args) {
        this.args = args;
    }

    public void run() throws ParseException {
        checkCLI();
        checkModeOfOperation();
        System.out.println(modeOfOperation);
    }

    private String modeOfOperation;
    private void checkModeOfOperation(){
        this.modeOfOperation = CLI.getModeOfOperation();

        if(modeOfOperation.equals(Settings.MODE_OF_OPERATION_CONSOLE.getValue())){
            runProgramWindowConsole();
        }
        else if(modeOfOperation.equals(Settings.MODE_OF_OPERATION_GUI.getValue())){
            runProgramWindowGUI();
        }

    }

    private CLI CLI;
    private void checkCLI() throws ParseException {
        CLI = new CLI(args);
        CLI.start();
    }

    private void runProgramWindowConsole(){
        ControllerConsole controllerConsole = new ControllerConsole(
                CLI.getCommand(),
                CLI.getFilepath(),
                CLI.getKey()
        );
        controllerConsole.run();
    }

    private void runProgramWindowGUI(){
        ControllerGUI controllerGUI = new ControllerGUI();
        controllerGUI.run();
    };
}
