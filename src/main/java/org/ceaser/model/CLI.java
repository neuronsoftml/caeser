package org.ceaser.model;

import org.apache.commons.cli.*;
import org.ceaser.error.ConfigError;
import org.ceaser.setting.ConfigCLI;

public class CLI {
    private final String[] settings;

    public CLI(String[] settings) {
        this.settings = settings;
    }

    public void start() throws ParseException {
        definitionOfParameters();
        analysisOfParameters();
        System.out.println("Команда :"+command);
        System.out.println("Шлях до файла :"+filepath);
        System.out.println("Ключь :"+key);
    }

    private Options options;

    private void definitionOfParameters(){
        Options options = new Options();

        Option optionCommand = new Option("c",true,"command");
        options.addOption(optionCommand);

        Option optionFilePath = new Option("f", true, "filepath");
        options.addOption(optionFilePath);

        Option optionKey = new Option("k", true,"key");
        options.addOption(optionKey);

        Option optionModeOfOperation = new Option("m", true, "modeOfOperation");
        options.addOption(optionModeOfOperation);
        this.options = options;
    }

    private void analysisOfParameters() throws ParseException {
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cmd = commandLineParser.parse(options, settings);

        cmd.hasOption("c");
        this.command = cmd.getOptionValue("c");
        cmd.hasOption("f");
        this.filepath = cmd.getOptionValue("f");
        cmd.hasOption("m");
        this.modeOfOperation = cmd.getOptionValue("m");
        cmd.hasOption("k");
        this.key = cmd.getOptionValue("k") == null ? 0 : Integer.parseInt(cmd.getOptionValue("k"));

        checkParameters(
                command,
                filepath,
                modeOfOperation,
                key
        );
    }

    private String command;
    public String getCommand() {
        return command;
    }

    private String filepath;
    public String getFilepath() {
        return filepath;
    }

    private int key;
    public int getKey() {
        return key;
    }

    private String modeOfOperation;
    public String getModeOfOperation(){return modeOfOperation;}

    private void checkParameters(String command, String filepath, String modeOfOperation, int key){
        if(!validateCommand(command)){
            System.exit(0);
        }
        else if(!validateModeOfOperation(modeOfOperation)){
            System.exit(0);
        }
        else if (!validateFilePath(filepath)) {
            System.exit(0);
        }
        else if(!validateKey(key)){
            System.exit(0);
        }
    }

    private boolean validateKey(int key){
        if(key == 0 && modeOfOperation.equals(ConfigCLI.COMMAND_BRUTE_FORCE.getValue())){
            return true;
        }
        else if(key <= 0){
            System.out.println(ConfigError.ERROR_KEY.getText());
            return false;
        }
        this.key = key;
        return true;
    }

    private boolean validateCommand(String command){
        if(command == null){
            System.out.println(ConfigError.ERROR_COMMAND.getText());
            return false;

        } else if (
                !command.equals(ConfigCLI.COMMAND_ENCRYPT.getValue()) &&
                !command.equals(ConfigCLI.COMMAND_DECRYPT.getValue()) &&
                !command.equals(ConfigCLI.COMMAND_BRUTE_FORCE.getValue()) ) {

            System.out.println(ConfigError.ERROR_COMMAND.getText());
            return false;
        }

        return true;
    }

    private boolean validateModeOfOperation(String modeOfOperation){
        if(modeOfOperation == null){
            System.out.println(ConfigError.ERROR_MODE_OF_OPERATION.getText());
            return false;

        } else if (
                !modeOfOperation.equals(ConfigCLI.MODE_OF_OPERATION_CONSOLE.getValue()) &&
                        !modeOfOperation.equals(ConfigCLI.MODE_OF_OPERATION_GUI.getValue())) {

            System.out.println(ConfigError.ERROR_COMMAND.getText());
            return false;
        }

        return true;
    }

    private boolean validateFilePath(String filepath) {
        if(filepath == null){
            System.out.println(ConfigError.ERROR_FILE_PATH.getText());
            return false;

        }
        return true;
    }
}
