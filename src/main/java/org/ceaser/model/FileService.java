package org.ceaser.model;

import org.ceaser.error.ConfigError;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileService {
    public String readFile(String url){
        checkIsRegularFile(url);
        String unencryptedTerm = null;
        try(FileInputStream fileInputStream = new FileInputStream(url);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            unencryptedTerm = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return unencryptedTerm;
    }

    public void writerFile(String data, String url, String type){
        String urlResultFile = genericPath(url);
        try(FileWriter writer = new FileWriter(urlResultFile)) {
            writer.write(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkIsRegularFile(String url){
        Path path = Path.of(url);
        if(!Files.isRegularFile(path)){
            System.out.println(ConfigError.ERROR_REGULAR_FILE +" "+url);
            System.exit(0);
        }
    }
    public String genericPath(String url){
        Path path = Path.of(url).getParent();
        return String.valueOf(path);
    }

}
