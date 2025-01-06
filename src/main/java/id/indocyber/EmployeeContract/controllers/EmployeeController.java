package id.indocyber.EmployeeContract.controllers;

import id.indocyber.EmployeeContract.dtos.employees.EmployeeFilterDTO;
import id.indocyber.EmployeeContract.dtos.employees.EmployeeFormDTO;
import id.indocyber.EmployeeContract.dtos.position.PositionFormDTO;
import id.indocyber.EmployeeContract.services.BranchService;
import id.indocyber.EmployeeContract.services.EmployeeService;
import id.indocyber.EmployeeContract.services.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private BranchService branchService;
    private PositionService positionService;

    public EmployeeController(EmployeeService employeeService, BranchService branchService, PositionService positionService) {
        this.employeeService = employeeService;
        this.branchService = branchService;
        this.positionService = positionService;
    }

    @GetMapping("")
    public ModelAndView index(EmployeeFilterDTO dto){
        return new ModelAndView("employee/index")
                .addObject("page", "employee")
                .addObject("employees", employeeService.get(dto));
    }

    @GetMapping("/insert")
    public ModelAndView insert(){
        return new ModelAndView("employee/form")
                .addObject("page", "employee")
                .addObject("branches", branchService.get())
                .addObject("positions", positionService.get())
                .addObject("employee", EmployeeFormDTO.builder().build());
    }

    @GetMapping("/edit")
    public ModelAndView update(String code){
        return new ModelAndView("employee/form")
                .addObject("page", "employee")
                .addObject("branches", branchService.get())
                .addObject("positions", positionService.get())
                .addObject("employee", employeeService.getByCode(code));
    }

    @PostMapping("/upsert")
    public ModelAndView upsert(EmployeeFormDTO dto){
        Boolean result = employeeService.upsert(dto);
        String message = result ? "Data berhasil ditambahkan!" : "Terjadi kesalahan saat menyimpan data";
        return new ModelAndView("employee/index")
                .addObject("page", "employee")
                .addObject("successUpsert", result)
                .addObject("message", message)
                .addObject("employees", employeeService.get(EmployeeFilterDTO.builder().build()));
    }

    @GetMapping("/delete")
    public ModelAndView deleteConfirmation(String code){
        return new ModelAndView("employee/delete-confirmation")
                .addObject("page", "employee")
                .addObject("employee", EmployeeFormDTO.builder()
                        .employeeCode(code)
                        .build());
    }

    @PostMapping("/delete")
    public ModelAndView delete(EmployeeFormDTO dto){
        Boolean result = employeeService.delete(dto);
        String message = result ? "Data berhasil dihapus!" : "Terjadi kesalahan saat menghapus data";
        return new ModelAndView("employee/index")
                .addObject("page", "employee")
                .addObject("successUpsert", result)
                .addObject("message", message)
                .addObject("employees", employeeService.get(EmployeeFilterDTO.builder().build()));
    }
}
