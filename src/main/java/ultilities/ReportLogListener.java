package ultilities;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;

public class ReportLogListener extends  LogListener {
    public ReportLogListener(Class<?> clazz) {
        super(clazz);
    }

    private static Logger ReportLog = Logger.getLogger(ReportLogListener.class.getName());

    @Step()
    public void startLog(String testClassName) {
        ReportLog.info("Test is Starting...");
    }

    @Step()
    @Override
    public void error(String[] messages) {
        super.error(messages);
    }

    @Step()
    public static void endLog(String testClassName) {
        ReportLog.info("Test is Ending...");
    }


    @Step()
    @Override
    public void info(String message) {
        super.info(message);
    }

    @Step()
    @Override
    public void warn(String message) {
        super.warn(message);
    }

    @Step()
    @Override
    public void error(String message) {
        super.error(message);
    }

    @Step()
    public void fatal(String message) {
        super.fatal(message);
    }

    @Step()
    @Override
    public void debug(String message) {
        super.debug(message);
    }
}
