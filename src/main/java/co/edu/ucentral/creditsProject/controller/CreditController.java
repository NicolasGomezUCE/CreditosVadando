package co.edu.ucentral.creditsProject.controller;

import co.edu.ucentral.creditsProject.config.Utilities;
import co.edu.ucentral.creditsProject.dto.Credit;
import co.edu.ucentral.creditsProject.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CreditController {

    @Autowired
    CreditService creditService;

    boolean firstTimeRequest = true;

    @GetMapping("/credit")
    public String showForm(Model model){
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
            model.addAttribute("fee",creditService.quotesimulation(credit.getTotalAmount().doubleValue(),creditService.getInterest(creditService.getCreditType(credit.getType())),credit.getMonthsTime()));
            return "radicarCredito";
        }else{
            firstTimeRequest = true;
            creditService.registerCredit(credit);
            return "redirect:/";
        }

    }

    @GetMapping("/ApprovingCredit")
    public String getApprovingPendingCreditos(Model model){
        model.addAttribute("credits", creditService.getApprovingPendingCreditsOfficer());
        return "CreditsOfficerApproving";
    }

    @GetMapping("/creditApprove")
    public String getCreditApprove(@RequestParam("id") int id, Model model) {
        System.out.println("entra controller");
        model.addAttribute("credit",  creditService.getCredit(id));
        return "CreditApproving";
    }

    @PostMapping("/Approve")
    public String approveCredit(@RequestParam("id") int id,
                                @RequestParam("dateCutoff") int dateCutoff,
                                @RequestParam(value = "approve", defaultValue = "false") boolean approve) {
        creditService.approveCredit(approve,id,dateCutoff);
        return "redirect:/Dashboard";
    }

    @GetMapping("/creditsClient")
    public String getCredits(Model model, @RequestParam(value = "clientId", required = false) String clientId) {
        List<Credit> credits;
        if (clientId != null && !clientId.isEmpty()) {
            credits = creditService.getAllCreditsClient(clientId);
        } else {
            credits = creditService.getAllCredits();
        }
        model.addAttribute("credits", credits);
        return "CreditsClient";
    }

    @GetMapping("/creditsOfficer")
    public String getCreditsOfficer(Model model) {
        List<Credit> credits;

        if(Utilities.IS_LOGED_IN){
            credits = creditService.getAllCreditsOfficer(Utilities.ID_LOG_IN);
            model.addAttribute("credits", credits);
            return "CreditsClient";
        }else{
            return "redirect:/Login";
        }

    }
}
