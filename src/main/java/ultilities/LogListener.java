package ultilities;

import org.apache.log4j.Logger;

public class LogListener {
    public Logger Log;


    /*
     * Trace log : đồng thời lắng nge log khi chạy 1 TCs automation api or nhiều cây cùng lúc nó sẽ show log để mình debug
     *
     * */

    public LogListener(Class<?> clazz) {
        Log = Logger.getLogger(clazz);
    }

    public LogListener() {

    }

    public void info(String message) {
        Log.info(message);
    }

    public void warn(String message) {
        Log.warn(message);
    }

    public void error(String message) {
        Log.error(message);
    }

    public void fatal(String message) {
        Log.fatal(message);
    }

    public void debug(String message) {
        Log.debug(message);
    }

    public void error(String[] messages) {
        for (String msg : messages) {
            Log.error(msg.toString());
        }
    }
}
