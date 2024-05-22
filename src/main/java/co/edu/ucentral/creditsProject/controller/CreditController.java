package co.edu.ucentral.creditsProject.controller;

import co.edu.ucentral.creditsProject.dto.Credit;
import co.edu.ucentral.creditsProject.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreditController {

    @Autowired
    CreditService creditService;

    @GetMapping("/credit")
    public String mostrarFormulario(Model model){
        Credit credit = new Credit();
        model.addAttribute("credit", credit);
        return "radicarCredito";
    }


    @PostMapping("/credit")
    public String registerCredit(@ModelAttribute("credit") Credit credit) {
        creditService.registerCredit(credit);
        return "redirect:/";
    }

}
