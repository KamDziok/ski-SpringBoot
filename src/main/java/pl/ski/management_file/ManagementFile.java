package pl.ski.management_file;

import pl.ski.static_values.ServerPHPToImg;

import java.io.*;
import java.nio.file.Files;

public class ManagementFile {

    public static String addToPath(String path, String upDirectory){
//        if(!ManagementFile.isDirectory(path, upDirectory)){
//            ManagementFile.createDirectory(path, upDirectory);
//        }
        return path + "\\" + upDirectory;
    }

    public static String fullPathFile(String path, String nameFile, String extensionFile) {
        return path + "\\" + nameFile + "." + extensionFile;
    }

    public static String fullPathFile(String path, String nameFile) {
        return path + "\\" + nameFile;
    }

    public static String fullPathDirectory(String path, String nameDirectory) {
        return path + "\\" + nameDirectory;
    }

    public static boolean isDirectory(String path, String nameDirectory){
        return Files.exists(new File(fullPathDirectory(path, nameDirectory)).toPath());
    }

    public static boolean isFile(String path, String nameFile, String extensionFile){
        return Files.exists(new File(fullPathFile(path, nameFile, extensionFile)).toPath());
    }

    public static boolean isFile(String path, String nameFile){
        return Files.exists(new File(fullPathFile(path, nameFile)).toPath());
    }

    public static boolean createDirectory(String path, String nameDirectory){
        if(isDirectory(path, nameDirectory)){
            return true;
        }
        return new File(fullPathDirectory(path, nameDirectory)).mkdir();
    }

    public static boolean createFile(String path, String nameFile, String extensionFile){
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

    public static boolean createFile(String path, String nameFile){
        try {
            if(isFile(path, nameFile)){
                return true;
            }
            return new File(fullPathFile(path, nameFile)).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean writeToFile(String path, String nameFile, String extensionFile, String text) {
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

    public static boolean writeByte(byte[] bytes, File file) {
        boolean result = false;
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
            result = true;
        }

        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return result;
    }

    public static boolean runFileBAT(String path,String nameFile, String extensionFile){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(fullPathFile(path, nameFile, extensionFile));
            processBuilder.start();
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
