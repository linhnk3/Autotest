package ultilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    private static LogListener logListener = new LogListener(TestListener.class);

    @Override
    public void onStart(ITestContext iTestContext) {
        super.onStart(iTestContext);
        logListener.info("***** START TEST: " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        super.onFinish(iTestContext);
        logListener.info("***** END TEST: " + iTestContext.getName() + " (" + "PASSED: " + getPassedTests().size() + "; " + "FAILED: " + getFailedTests().size() + "; " + "SKIPPED: "
                + getSkippedTests().size() + ")");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        super.onTestSuccess(iTestResult);
        endTestCase(getTestName(iTestResult), "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        super.onTestFailure(iTestResult);
        endTestCase(getTestName(iTestResult), "FAILED", getTestMessage(iTestResult), getStackTrace(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        super.onTestSkipped(iTestResult);
        endTestCase(getTestName(iTestResult), "SKIPPED", getTestMessage(iTestResult), getStackTrace(iTestResult));
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        super.onTestStart(iTestResult);
        logListener.info("------------------------- Started Test '" + iTestResult.getName() + "' -------------------------");
        logListener.info("Decription: " + getTestDescription(iTestResult));
        logListener.info("Parameters: " + getTestParams(iTestResult));
    }

    /**
     * getTestName method
     *
     * @param tr
     * @return String
     */
    public String getTestName(ITestResult tr) {
        return tr.getName();
    }

    /**
     * getTestMessage method
     *
     * @param tr
     * @return String
     */
    public String getTestMessage(ITestResult tr) {
        Boolean found = false;
        if (tr != null && tr.getThrowable() != null) {
            found = true;
        }
        if (found == true) {
            return tr.getThrowable().getMessage() == null ? "" : tr.getThrowable().getMessage();
        } else {
            return "";
        }
    }

    /**
     * getTestDescription method
     *
     * @param tr
     * @return String
     */
    private String getTestDescription(ITestResult tr) {
        String message = "";
        try {
            if (tr.getParameters().length > 0) {
                message = ": " + tr.getParameters()[1].toString();
            }
        } catch (Exception e) {
        }

        return message;
    }

    /**
     * getTestParams method
     *
     * @param tr
     * @return String
     */
    public String getTestParams(ITestResult tr) {
        int iLength = tr.getParameters().length;
        String message = "";
        try {
            if (tr.getParameters().length > 0) {
                message = tr.getParameters()[0].toString();
                for (int iCount = 0; iCount < iLength; iCount++) {
                    if (iCount == 0) {
                        message = tr.getParameters()[0].toString();
                    } else {
                        message = message + ", " + tr.getParameters()[iCount].toString();
                    }
                }
            }
        } catch (Exception e) {
        }

        return message;
    }

    private void endTestCase(String testName, String result) {
        logListener.info("Result: " + result);
        logListener.info("------------------------- Ended Test '" + testName + "' -------------------------");
    }

    private void endTestCase(String testName, String result, String testMessage, String[] stackTrace) {
        logListener.info("Result: " + result);
        if (testMessage != null) logListener.info("Message: " + testMessage);
        if (stackTrace != null) logListener.error(stackTrace);
        logListener.info("------------------------- Ended Test '" + testName + "' -------------------------");
    }

    private String[] getStackTrace(ITestResult tr) {
        StackTraceElement[] stackTraces = tr.getThrowable().getStackTrace();
        String[] stackTraceLogs = new String[stackTraces.length];
        for (int index = 0; index < stackTraces.length; index++) {
            stackTraceLogs[index] = stackTraces[index].toString();
        }
        return stackTraceLogs;
    }
}
