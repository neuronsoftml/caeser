package org.ceaser.controller;

import org.ceaser.error.ConfigError;
import org.ceaser.model.CaesarCipher;
import org.ceaser.setting.ConfigCLI;

public class ControllerConsole {
    private String command;
    private String filepath;
    private int key;

    public ControllerConsole(String command, String filepath, int key) {
        this.command = command;
        this.filepath = filepath;
        this.key = key;
    }

    public void run() {
        checkCommand(command);
    }

    private void checkCommand(String command){
        if(command.equals(ConfigCLI.COMMAND_ENCRYPT.getValue())){
            runEncryption();
        } else if (command.equals(ConfigCLI.COMMAND_DECRYPT.getValue())) {
            runDecrypt();
        } else if (command.equals(ConfigCLI.COMMAND_BRUTE_FORCE.getValue())) {
            runBrutForce();
        }else{
            runErrorNotCommand();
        }
    }

    private void runDecrypt() {
        CaesarCipher caesarCipher = new CaesarCipher(key,filepath);
        caesarCipher.decryption();
    }

    private void runEncryption() {
        CaesarCipher caesarCipher = new CaesarCipher(key,filepath);
        caesarCipher.encryption();
    }

    private void runBrutForce(){
        CaesarCipher caesarCipher = new CaesarCipher(filepath);
        caesarCipher.brutForce();
    }

    private void runErrorNotCommand(){
        System.out.println(ConfigError.ERROR_COMMAND.getText());
        System.exit(0);
    }

}
