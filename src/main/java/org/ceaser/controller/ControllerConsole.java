package org.ceaser.controller;

import org.ceaser.model.CaesarCipher;
import org.ceaser.model.FileService;
import org.ceaser.setting.Settings;

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
        if(command.equals(Settings.COMMAND_ENCRYPT.getValue())){
            runEncryption();
        } else if (command.equals(Settings.COMMAND_DECRYPT.getValue())) {
            runDecrypt();
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

}
