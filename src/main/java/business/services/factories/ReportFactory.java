package business.services.factories;

import business.models.Report;

import business.models.Employee;
import java.util.Date;

public interface ReportFactory {
    Report createReport( // Factory method
            String content,
            Date timestamp,
            Employee employee
    );
}