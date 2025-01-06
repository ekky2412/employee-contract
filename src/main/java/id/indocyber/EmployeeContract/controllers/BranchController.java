package id.indocyber.EmployeeContract.controllers;

import id.indocyber.EmployeeContract.dtos.branch.BranchFormDTO;
import id.indocyber.EmployeeContract.services.BranchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/branch")
public class BranchController {
    private BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("")
    public ModelAndView index(){
        return new ModelAndView("branch/index")
                .addObject("page", "branch")
                .addObject("branches", branchService.get());
    }

    @GetMapping("/insert")
    public ModelAndView insert(){
        return new ModelAndView("branch/form")
                .addObject("page", "branch")
                .addObject("branch", BranchFormDTO.builder().build());
    }

    @GetMapping("/edit")
    public ModelAndView update(String code){
        return new ModelAndView("branch/form")
                .addObject("page", "branch")
                .addObject("branch", branchService.getByCode(code));
    }

    @PostMapping("/upsert")
    public ModelAndView upsert(BranchFormDTO dto){
        Boolean result = branchService.upsert(dto);
        String message = result ? "Data berhasil ditambahkan!" : "Terjadi kesalahan saat menyimpan data";
        return new ModelAndView("branch/index")
                .addObject("page", "branch")
                .addObject("successUpsert", result)
                .addObject("message", message)
                .addObject("branches", branchService.get());
    }

    @GetMapping("/delete")
    public ModelAndView deleteConfirmation(String code){
        return new ModelAndView("branch/delete-confirmation")
                .addObject("page", "branch")
                .addObject("branch", BranchFormDTO.builder()
                        .code(code)
                        .build());
    }

    @PostMapping("/delete")
    public ModelAndView delete(BranchFormDTO dto){
        Boolean result = branchService.delete(dto);
        String message = result ? "Data berhasil dihapus!" : "Terjadi kesalahan saat menghapus data";
        return new ModelAndView("branch/index")
                .addObject("page", "branch")
                .addObject("successUpsert", result)
                .addObject("message", message)
                .addObject("branches", branchService.get());
    }
}
