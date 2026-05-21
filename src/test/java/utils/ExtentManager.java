package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            ExtentSparkReporter spark = new ExtentSparkReporter("Report/ExtentReport.html");

            spark.config().setReportName("Amazon Test Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "Amazon Selenium Framework");
            extent.setSystemInfo("Tester", "Omar");
            extent.setSystemInfo("Environment", "QA");
        }

        return extent;
    }
}