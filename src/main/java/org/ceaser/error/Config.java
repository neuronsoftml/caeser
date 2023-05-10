package org.ceaser.error;

public enum Config {
    ERROR_COMMAND("Помилка запуску \"command\" not found -c виберіть три доступні варіанти:[ENCRYPT, DECRYPT, BRUTE_FORCE]"),
    ERROR_FILE_PATH("Помилка запуску \"filepath\" not found  -f абсолютний(повний) шлях до файлу, який кодується."),
    ERROR_MODE_OF_OPERATION("Помилка запуску \"modeOfOperations\" not found  -m режим запуску програми [CONSOLE, GUI]"),
    ERROR_REGULAR_FILE("Помилка \"URL File\" not found такого файла не їснує.");

    Config(String text){
        this.text = text;
    }
    private String text;
    public String getText(){
        return  text;
    }
}
