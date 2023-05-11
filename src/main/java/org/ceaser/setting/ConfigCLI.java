package org.ceaser.setting;

public enum ConfigCLI {
    MODE_OF_OPERATION_CONSOLE("CONSOLE"),
    MODE_OF_OPERATION_GUI("GUI"),
    COMMAND_ENCRYPT("ENCRYPT"),
    COMMAND_DECRYPT("DECRYPT"),
    COMMAND_BRUTE_FORCE("BRUTE_FORCE");

    private String value;

    ConfigCLI(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
