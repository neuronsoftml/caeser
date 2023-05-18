package org.ceaser.error;

public enum ConfigError {
    ERROR_COMMAND("Помилка запуску \"command\" not found -c виберіть три доступні варіанти:[ENCRYPT, DECRYPT, BRUTE_FORCE]"),
    ERROR_FILE_PATH("Помилка запуску \"filepath\" not found  -f абсолютний(повний) шлях до файлу, який кодується."),
    ERROR_MODE_OF_OPERATION("Помилка запуску \"modeOfOperations\" not found  -m режим запуску програми [CONSOLE, GUI]"),
    ERROR_REGULAR_FILE("Помилка \"URL File\" not found такого файла не їснує."),
    ERROR_KEY("Помилка \"key\" not found -k схоже відсутній ключь або неправильний діапзон числ.. (1 or 125, 364...)."),
    ERROR_KEY_NOT_NUMBER_ZERO("Помилка \"key\" ключ не може бути відємним або дорівнювати 0.");

    ConfigError(String text){
        this.text = text;
    }
    private String text;
    public String getText(){
        return  text;
    }
}
