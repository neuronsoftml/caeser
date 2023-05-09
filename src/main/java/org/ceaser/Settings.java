package org.ceaser;

public enum Settings {
    MODE_OF_OPERATION_CONSOLE("CONSOLE"),
    MODE_OF_OPERATION_GUI("GUI");

    private String value;

    Settings(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
