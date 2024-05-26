package co.edu.ucentral.creditsProject.controller;

import co.edu.ucentral.creditsProject.config.Utilities;
import co.edu.ucentral.creditsProject.dto.Credit;
import co.edu.ucentral.creditsProject.dto.Login;
import co.edu.ucentral.creditsProject.dto.Officer;
import co.edu.ucentral.creditsProject.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfficerController {

    @Autowired
    OfficerService officerService;

    @GetMapping("/login")
    public String ShowLogin(Model model){
        if(Utilities.IS_LOGED_IN){
            model.addAttribute("officer",officerService.getOfficer(Utilities.ID_LOG_IN));
            return "Dashboard";
        }else{
            Login login = new Login();

            model.addAttribute("login",login);
            model.addAttribute("error",true);
            return "Login";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login, Model model){
        if(officerService.login(login.getUser(),login.getPassword())){
            Utilities.IS_LOGED_IN = true;
            Utilities.ID_LOG_IN = login.getUser();

            model.addAttribute("officer",officerService.getOfficer(Utilities.ID_LOG_IN));
            return "Dashboard";
        }else {
            model.addAttribute("error",false);
            return "Login";
        }
    }
}
