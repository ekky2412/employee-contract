package id.indocyber.EmployeeContract.repositories;

import id.indocyber.EmployeeContract.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, String> {
}
