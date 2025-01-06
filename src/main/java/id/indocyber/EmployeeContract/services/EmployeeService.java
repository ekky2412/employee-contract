package id.indocyber.EmployeeContract.services;

import id.indocyber.EmployeeContract.dtos.branch.BranchFieldDTO;
import id.indocyber.EmployeeContract.dtos.employees.EmployeeDetailDTO;
import id.indocyber.EmployeeContract.dtos.employees.EmployeeFieldDTO;
import id.indocyber.EmployeeContract.dtos.employees.EmployeeFilterDTO;
import id.indocyber.EmployeeContract.dtos.employees.EmployeeFormDTO;
import id.indocyber.EmployeeContract.dtos.position.PositionFieldDTO;
import id.indocyber.EmployeeContract.dtos.position.PositionFormDTO;
import id.indocyber.EmployeeContract.models.Branch;
import id.indocyber.EmployeeContract.models.Employee;
import id.indocyber.EmployeeContract.models.Position;
import id.indocyber.EmployeeContract.repositories.BranchRepository;
import id.indocyber.EmployeeContract.repositories.EmployeeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private BranchService branchService;
    private PositionService positionService;

    public EmployeeService(EmployeeRepository employeeRepository, BranchService branchService, PositionService positionService) {
        this.employeeRepository = employeeRepository;
        this.branchService = branchService;
        this.positionService = positionService;
    }

    @Transactional(readOnly = true)
    public List<EmployeeDetailDTO> get(EmployeeFilterDTO dto){
        String checkedEmployeeCode = dto.getEmployeeCode() == null || dto.getEmployeeCode().isEmpty() ? null : dto.getEmployeeCode();
        String checkedBranchCode = dto.getBranchCode() == null || dto.getBranchCode().isEmpty() ? null : dto.getBranchCode();
        String checkedPositionCode = dto.getPositionCode() == null || dto.getPositionCode().isEmpty() ? null : dto.getPositionCode();

        var employees = employeeRepository.getEmployeeDetails(checkedEmployeeCode, checkedBranchCode, checkedPositionCode).stream().map(
                employee -> EmployeeDetailDTO.builder()
                        .employeeCode(employee[0].toString())
                        .employeeName(employee[1].toString())
                        .branchCode(employee[2].toString())
                        .branchName(employee[3].toString())
                        .positionCode(employee[4].toString())
                        .positionName(employee[5].toString())
                        .contractStartDate(employee[6] != null ? LocalDate.parse(employee[6].toString()) : null)
                        .contractEndDate(employee[7] != null ? LocalDate.parse(employee[7].toString()) : null)
                        .build()
        ).toList();
        return employees;
    }

    public EmployeeFieldDTO getByCode(String code){
        var employee = employeeRepository.findById(code);
        if(employee.isPresent()) {
            var existEmployee = employee.get();
            return EmployeeFieldDTO.builder()
                    .employeeCode(existEmployee.getCode())
                    .employeeName(existEmployee.getName())
                    .branchCode(existEmployee.getBranch().getCode())
                    .positionCode(existEmployee.getPosition().getCode())
                    .contractStartDate(existEmployee.getContractStartDate())
                    .contractEndDate(existEmployee.getContractEndDate())
                    .build();
        }

        return EmployeeFieldDTO.builder().build();
    }

    public Boolean upsert(EmployeeFormDTO dto){
        Branch branch = new Branch();
        branch.setCode(dto.getBranchCode());
        Position position = new Position();
        position.setCode(dto.getPositionCode());
        try{
            var checkBranch = branchService.getByCode(dto.getBranchCode());
            var checkPosition = positionService.getByCode(dto.getPositionCode());
        } catch (Exception e){
            System.err.println("Error : " + e.getMessage());
            return false;
        }

        var employee = Employee.builder()
                .code(dto.getEmployeeCode())
                .name(dto.getEmployeeName())
                .branch(branch)
                .position(position)
                .contractStartDate(dto.getContractStartDate())
                .contractEndDate(dto.getContractEndDate())
                .build();
        try{
            employeeRepository.save(employee);
            return true;
        } catch (Exception e){
            System.err.println("Error : " + e.getMessage());
            return false;
        }
    }

    public Boolean delete(EmployeeFormDTO dto){
        try{
            employeeRepository.deleteById(dto.getEmployeeCode());
            return true;
        }catch (Exception e){
            System.err.println("Error : " + e.getMessage());
            return false;
        }
    }

    public Boolean insertExcelFile(MultipartFile file){
        List<Employee> employees = new ArrayList<>();
        try(InputStream inputStream = file.getInputStream()){
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet){
                //Skip header
                if(row.getRowNum() == 0){
                    continue;
                }

                var branch = Branch.builder()
                        .code(row.getCell(2).getStringCellValue())
                        .build();
                var position = Position.builder()
                        .code(row.getCell(3).getStringCellValue())
                        .build();

                Employee employee = Employee.builder()
                        .code(row.getCell(0).getStringCellValue())
                        .name(row.getCell(1).getStringCellValue())
                        .branch(branch)
                        .position(position)
                        .contractStartDate(row.getCell(4).getLocalDateTimeCellValue().toLocalDate())
                        .contractEndDate(row.getCell(5).getLocalDateTimeCellValue().toLocalDate())
                        .build();

                employees.add(employee);
                System.out.println("Data : " + employee);
            }

            employeeRepository.saveAll(employees);

            return true;
        } catch (Exception e){
            System.err.println("Error : " + e.getMessage());
            return false;
        }
    }
}
