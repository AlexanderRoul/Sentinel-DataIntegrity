package com.sentinel.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            reporter.config().setDocumentTitle("Sentinel Automation Report");
            reporter.config().setReportName("Data Integrity Framework");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
