package business.models;


import java.util.Date;
import java.util.List;

public abstract class Report { // Abstract base class
    private int reportId;
    private String content;
    private Date timestamp;
    private Employee employee; // Employee who generated the report
    // ... other common report attributes

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Report(int reportId, String content, Employee employee) {
        this.reportId = reportId;
        this.content = content;
        this.timestamp = new Date();
        this.employee = employee;
    }

    // Getters and Setters

    public abstract String generateReport(); // Abstract method to be implemented by subclasses
}

// ... other report types (e.g., TransactionSummary, UserActivityLog)