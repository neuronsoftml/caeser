package org.ceaser.setting;

public enum ConfigGUI {
    VERSION_PROGRAM("1.0"),
    NAME_PROGRAM("Ceaser-enrypter");


    private String str;
    ConfigGUI(String str){
        this.str = str;
    }

    public String getStr(){
        return str;
    }
}
