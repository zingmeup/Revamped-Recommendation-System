package loggerAPI;

public class Log {
    public static boolean visibility;

    static public void v(String tag, String message) {
        if (visibility) {

            String output = "LOG/VERBOSE- " + tag + ": " + message;
            String a = (char) 27 + "[67m" + output;
            System.out.println(a);
        }
    }

    static public void a(String tag, String message) {
        if (visibility) {

            String output = "LOG/ASSERT- " + tag + ": " + message;
            String a = (char) 27 + "[92m" + output;
            System.out.println(a);
        }
    }

    static public void e(String tag, String message) {
        if (visibility) {

            String output = "LOG/ERROR- " + tag + ": " + message;
            String a = (char) 27 + "[41m" + output;
            System.out.println(a);
        }
    }

    static public void b(String tag, String message) {
        if (visibility) {

            String output = "LOG/BUG- " + tag + ": " + message;
            String a = (char) 27 + "[34m" + output;
            System.out.println(a);
        }
    }
    static public void i(String tag, String message) {
        if (visibility) {

            String output = "LOG/INFO- " + tag + ": " + message;
            String a = (char) 27 + "[94m" + output;
            System.out.println(a);
        }
    }

}
