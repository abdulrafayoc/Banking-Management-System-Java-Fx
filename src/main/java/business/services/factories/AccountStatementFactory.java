// AccountStatementFactory.java
package business.services.factories;

import business.models.Account;
import business.models.AccountStatement;
import business.models.Employee;
import business.models.Report;

import java.util.Date;

public class AccountStatementFactory implements ReportFactory {
    @Override
    public Report createReport(String content, Date timestamp, Employee employee) {
        // Assuming you need Account, date range, and Employee for AccountStatement
        return new AccountStatement(0, null, null, null, employee); // Placeholder
    }
}