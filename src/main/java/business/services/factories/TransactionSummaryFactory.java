package business.services.factories;

import business.models.TransactionSummary;
import business.models.Report;
import business.models.Employee;

import java.util.Date;


public class TransactionSummaryFactory implements ReportFactory {
    @Override
    public Report createReport(String content, Date timestamp, Employee employee) {
        // Logic specific to creating a TransactionSummary report
        return new TransactionSummary(0,content, timestamp, employee);
    }
}