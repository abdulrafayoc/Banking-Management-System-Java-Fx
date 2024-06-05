package business.models;

import java.util.Date;

public class AccountStatement extends Report {
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Account account;
    private Date startDate;
    private Date endDate;

    public AccountStatement(int reportId, Account account, Date startDate, Date endDate, Employee employee) {
        super(reportId, "", employee); // Initially empty content
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String generateReport() {
        // Logic to generate account statement content (transactions, balance, etc.)
        // ...
        return "Generated Account Statement Content"; // Placeholder
    }
}
