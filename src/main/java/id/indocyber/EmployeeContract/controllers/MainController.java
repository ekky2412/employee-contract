package id.indocyber.EmployeeContract.controllers;

import id.indocyber.EmployeeContract.dtos.employees.EmployeeFilterDTO;
import id.indocyber.EmployeeContract.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
    private EmployeeService employeeService;

    public MainController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ModelAndView index(EmployeeFilterDTO dto) {
        return new ModelAndView("main/index")
                .addObject("page", "main")
                .addObject("employees", employeeService.get(dto));
    }

//    @PostMapping("/upload")
//    public String upload(){
//        return "redirect:/main";
//    }

    // todo : tambahkan fitur upsert dari excel
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, EmployeeFilterDTO dto, Model model) {
        model.addAttribute("page", "main");
        model.addAttribute("employees", employeeService.get(dto));
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "main/index";
        }

        String fileName = file.getOriginalFilename();
        if (fileName != null && (fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))) {
            // Lakukan logika penyimpanan file atau parsing Excel di sini
            model.addAttribute("message", "File uploaded successfully: " + fileName);
        } else {
            model.addAttribute("message", "Invalid file format. Please upload an Excel file.");
        }

        return "main/index";
    }
}
