package co.edu.ucentral.creditsProject.service;


import co.edu.ucentral.creditsProject.dto.Credit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CreditService {
    public Credit registerCredit(Credit credit);

    public List<Credit> getAllCreditsClient(String id);

    public List<Credit> getAllCreditsOfficer(String id);

    public double quotesimulation(double totalAmount, double interest, double monthsTime);

}
