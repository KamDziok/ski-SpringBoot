package pl.ski.management_file;

import pl.ski.static_.ServerPHPToImg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class ManagementFile {

    private static String addToPath(String path, String upDirectory){
        return path + "/" + upDirectory;
    }

    private static String fullPathFile(String path, String nameFile, String extensionFile) {
        return path + "/" + nameFile + "." + extensionFile;
    }

    private static String fullPathDirectory(String path, String nameDirectory) {
        return path + "/" + nameDirectory;
    }

    private static boolean isDirectory(String path, String nameDirectory){
        return Files.exists(new File(fullPathDirectory(path, nameDirectory)).toPath());
    }

    private static boolean isFile(String path, String nameFile, String extensionFile){
        return Files.exists(new File(fullPathFile(path, nameFile, extensionFile)).toPath());
    }

//    public static boolean isFileOrDirectory(String path, String nameFile, String extensionFile){
//        return Files.exists(new File(path + "/" + nameFile).toPath());
//    }

    private static boolean createDirectory(String path, String nameDirectory){
        if(isDirectory(path, nameDirectory)){
            return true;
        }
        return new File(fullPathDirectory(path, nameDirectory)).mkdir();
    }

    private static boolean createFile(String path, String nameFile, String extensionFile){
        try {
            if(isFile(path, nameFile, extensionFile)){
                return true;
            }
            return new File(fullPathFile(path, nameFile, extensionFile)).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean writeToFile(String path, String nameFile, String extensionFile, String text) {
        try {
            FileWriter fileWriter = new FileWriter(fullPathFile(path, nameFile, extensionFile));
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(text);
            printWriter.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static boolean runFileBAT(String path,String nameFile, String extensionFile){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(fullPathFile(path, nameFile, extensionFile));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean runServerPHP(){
        String currentPath = ServerPHPToImg.getMainDirectory();
        try {
            if (createDirectory(currentPath, ServerPHPToImg.MAIN_SERVER_PHP_DIRECTORY)) {
                currentPath = addToPath(currentPath, ServerPHPToImg.MAIN_SERVER_PHP_DIRECTORY);
                if (createFile(currentPath, ServerPHPToImg.NAME_FILE_BAT, ServerPHPToImg.EXTENDION_FILE_BAT)) {
                    if(writeToFile(currentPath, ServerPHPToImg.NAME_FILE_BAT,
                            ServerPHPToImg.EXTENDION_FILE_BAT, ServerPHPToImg.CODE_FILE_BAT)) {
                        if(createDirectory(currentPath, ServerPHPToImg.IMG_DIRECTORY)) {
                            if(runFileBAT(currentPath, ServerPHPToImg.NAME_FILE_BAT, ServerPHPToImg.EXTENDION_FILE_BAT)){
                                return true;
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        return false;
    }


}
