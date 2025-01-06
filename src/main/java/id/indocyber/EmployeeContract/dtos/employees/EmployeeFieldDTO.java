package id.indocyber.EmployeeContract.dtos.employees;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@ToString
public class EmployeeFieldDTO {
    private String employeeCode;
    private String employeeName;
    private String branchCode;
    private String positionCode;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;

    public String getContractStartDateFormatted() {
        if (contractStartDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            return contractStartDate.format(formatter);
        }
        return null;
    }

    // Getter untuk contractEndDate
    public String getContractEndDateFormatted() {
        if (contractEndDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            return contractEndDate.format(formatter);
        }
        return null;
    }
}
