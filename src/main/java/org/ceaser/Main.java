package org.ceaser;

import org.apache.commons.cli.ParseException;
import org.ceaser.model.Runner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Runner runner = new Runner(args);
        runner.run();
    }
}