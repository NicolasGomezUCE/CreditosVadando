package co.edu.ucentral.creditsProject.service;

import co.edu.ucentral.creditsProject.dto.Credit;
import org.springframework.stereotype.Component;

@Component
public interface CreditService {
    public Credit registerCredit(Credit credit);

}
