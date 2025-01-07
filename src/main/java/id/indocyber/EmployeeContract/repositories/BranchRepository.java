package id.indocyber.EmployeeContract.repositories;

import id.indocyber.EmployeeContract.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {
    @Query("""
            SELECT b from Branch b
            WHERE b.name = :name
            """)
    public Optional<Branch> findByName(
            @Param("name") String name
    );
}
