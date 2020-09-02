package pl.ski.static_;

public class ServerPHPToImg {
    private static final String DOMAIN = "localhost";
    private static final String PORT = "8082";
    public static final String BASIC_ADDRESS_URL = DOMAIN + ":" + PORT;

    public static final String NAME_FILE_BAT = "run";
    public static final String EXTENDION_FILE_BAT = "bat";
    public static final String CODE_FILE_BAT = "start cmd /k php -S " + BASIC_ADDRESS_URL;

    public static final String MAIN_SERVER_PHP_DIRECTORY = "serverPHP";
    public static final String IMG_DIRECTORY = "img";

    private static String mainDirectory = null;

    public static String getMainDirectory() {
        if(ServerPHPToImg.mainDirectory == null){
            setMainDirectory();
        }
        return mainDirectory;
    }

    public static void setMainDirectory() {
        ServerPHPToImg.mainDirectory = System.getProperty("user.dir");
    }
}
