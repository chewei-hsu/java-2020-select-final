/**
 * define debugger info, use debug version to check the system
 */
public class Debugger {
    private static boolean debugMode;
    public static void setDebugMode(boolean debugMode) {
        Debugger.debugMode = debugMode;
        if(debugMode){
            // print a cool "debug mode" figlet in terminal when the debug mode is on.
            System.out.println("    ____       __                __  ___          __   \n" +
                    "   / __ \\___  / /_  __  ______ _/  |/  /___  ____/ /__ \n" +
                    "  / / / / _ \\/ __ \\/ / / / __ `/ /|_/ / __ \\/ __  / _ \\\n" +
                    " / /_/ /  __/ /_/ / /_/ / /_/ / /  / / /_/ / /_/ /  __/\n" +
                    "/_____/\\___/_.___/\\__,_/\\__, /_/  /_/\\____/\\__,_/\\___/ \n" +
                    "                       /____/                          \n");
            System.out.println("==================[DEBUG MODE ON]====================");
            System.out.println("WARNING: Make sure you know what you are doing.");
            System.out.println("Debug Mode may cause application to be unstable");
            System.out.println("or reveal critical personal info in console, use with care.");
            System.out.println("==================[DEBUG MODE ON]====================");
        }
    }

    /**
     * get the debug status
     * @return true if on
     */
    public static boolean getDebugMode() {
            return debugMode;
        }

        // only print the msg when debug mode is on.
        // used to replace the println method and makes it easier to manage the terminal output by a boolean switch.
        public static void showDebugMessage(String msg){
            if(debugMode)
                System.out.println(msg);
        }
}
