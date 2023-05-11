package org.ceaser.setting;

public enum СLIConfig {
    MODE_OF_OPERATION_CONSOLE("CONSOLE"),
    MODE_OF_OPERATION_GUI("GUI"),
    COMMAND_ENCRYPT("ENCRYPT"),
    COMMAND_DECRYPT("DECRYPT"),
    COMMAND_BRUTE_FORCE("BRUTE_FORCE");

    private String value;

    СLIConfig(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
