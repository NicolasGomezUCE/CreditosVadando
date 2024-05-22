package co.edu.ucentral.creditsProject.controller;

import co.edu.ucentral.creditsProject.dto.Credit;
import co.edu.ucentral.creditsProject.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CreditController {

    @Autowired
    CreditService creditService;

    boolean firstTimeRequest = true;

    @GetMapping("/credit")
    public String mostrarFormulario(Model model){
        Credit credit = new Credit();
        model.addAttribute("credit", credit);
        firstTimeRequest = true;
        model.addAttribute("firstTime", firstTimeRequest);

        return "radicarCredito";
    }


    @PostMapping("/credit")
    public String registerCredit(@ModelAttribute("credit") Credit credit, Model model) {
        if(firstTimeRequest){
            model.addAttribute("credit", credit);
            firstTimeRequest = false;
            model.addAttribute("firstTime", firstTimeRequest);
            model.addAttribute("fee",creditService.quotesimulation(credit.getTotalAmount(),creditService.getInterest(creditService.getCreditType(credit.getType())),credit.getMonthsTime()));
            return "radicarCredito";
        }else{
            firstTimeRequest = true;
            creditService.registerCredit(credit);
            return "redirect:/";
        }

    }


}
