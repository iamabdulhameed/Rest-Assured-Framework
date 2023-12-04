package com.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class Setup implements ITestListener {
    public static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    private static ExtentReports extentReports;

    public void onStart(ITestContext context) {
        String fileName = ExtentReportManager.getReportNameWithTimeStamp();
        String reportPath = System.getProperty("user.dir") + "/reports/" + fileName;
        extentReports = ExtentReportManager.createInstance(reportPath, "Test API Automation Report", "Test Execution Report");
    }

    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest("Test Name: " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
    }

    public void onTestFailure(ITestResult result) {
        ExtentReportManager.logFailureDetails(result.getThrowable().getMessage());
        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace = stackTrace.replaceAll(",", "<br>");
        String formattedTrace = "<details>\n" +
                "  <summary>Click for Exception Log</summary>\n" +
                "  " + stackTrace + ".\n" +
                "</details>";
        ExtentReportManager.logExceptionDetails(formattedTrace);
    }

}
