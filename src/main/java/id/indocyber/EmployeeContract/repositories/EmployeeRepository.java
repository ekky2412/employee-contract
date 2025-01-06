package id.indocyber.EmployeeContract.repositories;

import id.indocyber.EmployeeContract.dtos.employees.EmployeeDetailDTO;
import id.indocyber.EmployeeContract.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Procedure(procedureName = "GetEmployeeDetails")
    List<Object[]> getEmployeeDetails(
            @Param("employeeCode") String employeeCode,
            @Param("branchCode") String branchCode,
            @Param("positionCode") String positionCode
    );
}
