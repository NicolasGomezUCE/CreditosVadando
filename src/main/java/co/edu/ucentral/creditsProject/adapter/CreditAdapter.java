package co.edu.ucentral.creditsProject.adapter;


import co.edu.ucentral.creditsProject.dto.Credit;
import co.edu.ucentral.creditsProject.repostory.CreditRepository;
import co.edu.ucentral.creditsProject.repostory.entity.CreditEntity;
import co.edu.ucentral.creditsProject.service.CreditService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.ucentral.creditsProject.service.CreditService;

@Component
public class CreditAdapter implements CreditService {

    ModelMapper mp = new ModelMapper();

    @Autowired
    CreditRepository creditRepository;

    @Override
    public Credit registerCredit(Credit credit) {
        creditRepository.save(mp.map(credit, CreditEntity.class));
        return credit;
    }

    @Override
    public double quotesimulation(double totalAmount, double interest, double monthsTime) {
        double total = totalAmount  / monthsTime;
        return total + (total * interest);
    }

}
