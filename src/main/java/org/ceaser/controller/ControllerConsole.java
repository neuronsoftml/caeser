package org.ceaser.controller;

import org.ceaser.error.ConfigError;
import org.ceaser.model.CaesarCipher;
import org.ceaser.model.FileService;
import org.ceaser.setting.ConfigCLI;

public class ControllerConsole {
    private String command;
    private String filePath;
    private int key;

    public ControllerConsole(String command, String filePath, int key) {
        this.command = command;
        this.filePath = filePath;
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
        }
    }

    private void runDecrypt() {
        CaesarCipher caesarCipher = new CaesarCipher(key, filePath);
        new FileService().writerFile(caesarCipher.decryption(), filePath, "\\[DECRYPTED].txt");
    }

    private void runEncryption() {
        CaesarCipher caesarCipher = new CaesarCipher(key, filePath);
        new FileService().writerFile(caesarCipher.encryption(), filePath, "\\[ENCRYPTED].txt");
    }

    private void runBrutForce(){
        CaesarCipher caesarCipher = new CaesarCipher(filePath);
        new FileService().writerFile(caesarCipher.brutForce(0,30), filePath, "\\[BRUTE_FORCE].txt");
    }

}
