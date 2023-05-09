package org.ceaser.model;

import org.apache.commons.cli.*;
import org.ceaser.Settings;
import org.ceaser.error.Config;

public class CLI {
    private final String[] settings;

    public CLI(String[] settings) {
        this.settings = settings;
    }

    public void start() throws ParseException {
        definitionOfParameters();
        analysisOfParameters();
        System.out.println(command);
        System.out.println(filepath);
        System.out.println(key);
        checkParameters();
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

    private String command;
    private String filepath;
    private int key;
    private String modeOfOperation;

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
        String key = cmd.getOptionValue("k");
        if(key == null){
            this.key = 0;
        }else {
            this.key = Integer.parseInt(cmd.getOptionValue("k"));
        }

    }

    private void checkParameters(){
        if(command == null){
            System.out.println(Config.ERROR_COMMAND.getText());
            System.exit(0);
        }
        else if(filepath == null){
            System.out.println(Config.ERROR_FILE_PATH.getText());
            System.exit(0);
        }
        else if (modeOfOperation ==  null) {
            System.out.println(Config.ERROR_MODE_OF_OPERATION.getText());
            System.exit(0);
        }
    }

    public String getCommand() {
        return command;
    }

    public String getFilepath() {
        return filepath;
    }

    public int getKey() {
        return key;
    }

    public String getModeOfOperation(){return modeOfOperation;}

}
