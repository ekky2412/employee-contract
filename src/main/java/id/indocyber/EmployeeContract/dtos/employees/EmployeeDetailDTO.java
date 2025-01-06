package id.indocyber.EmployeeContract.dtos.employees;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@Builder
@ToString
public class EmployeeDetailDTO {
    private String employeeCode;
    private String employeeName;
    private String branchCode;
    private String branchName;
    private String positionCode;
    private String positionName;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;

    public String getContractStartDateFormatted() {
        Locale locale = new Locale("id", "ID");
        if (contractStartDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
            return contractStartDate.format(formatter);
        }
        return null;
    }

    // Getter untuk contractEndDate
    public String getContractEndDateFormatted() {
        Locale locale = new Locale("id", "ID");
        if (contractEndDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
            return contractEndDate.format(formatter);
        }
        return null;
    }
}
