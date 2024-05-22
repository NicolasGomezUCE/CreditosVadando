package co.edu.ucentral.creditsProject.adapter;


import co.edu.ucentral.creditsProject.config.CreditType;
import co.edu.ucentral.creditsProject.config.Status;
import co.edu.ucentral.creditsProject.dto.Client;
import co.edu.ucentral.creditsProject.dto.Credit;
import co.edu.ucentral.creditsProject.repostory.CreditRepository;
import co.edu.ucentral.creditsProject.repostory.entity.CreditEntity;
import co.edu.ucentral.creditsProject.repostory.mapper.CreditToDto;
import co.edu.ucentral.creditsProject.service.ClientService;
import co.edu.ucentral.creditsProject.service.CreditService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.ucentral.creditsProject.service.CreditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAdapter implements CreditService {

    @Autowired
    CreditToDto creditMapper;
    @Autowired
    CreditRepository creditRepository;

    @Autowired
    ClientService clientService;

    @Override
    public Credit registerCredit(Credit credit) {
        switch (credit.getType()){
            case "HOME":
                credit.setCreditType(CreditType.HOME);
                break;
            case "VEHICLE":
                credit.setCreditType(CreditType.VEHICLE);
                break;
            case "STUDIES":
                credit.setCreditType(CreditType.STUDIES);
                break;
            case "WALLET":
                credit.setCreditType(CreditType.WALLET);
                break;
            case "FREE":
                credit.setCreditType(CreditType.FREE);
                break;
        }

        credit.setInterest(getInterest(credit.getCreditType()));

        Client client = new Client();
        client.setId(credit.getIdClient());
        client.setFirstName(credit.getFirstName());
        client.setLastName(credit.getLastName());
        credit.setClient(client);
        credit.setStatus(Status.REQUESTED);

        CreditEntity creditEntity = creditMapper.toCreditEntity(credit);
        creditEntity.setClientId(credit.getClient().getId());
        clientService.saveClient(client);



        creditRepository.save(creditEntity);
        return credit;
    }

    @Override
    public List<Credit> getAllCreditsClient(String id) {
        return null;
    }

    @Override
    public List<Credit> getAllCreditsOfficer(String id) {
        return null;
    }

    @Override
    public double quotesimulation(double totalAmount, double interest, double monthsTime) {
        double total = totalAmount  / monthsTime;
        return total + (total * interest);
    }


    public double getInterest(CreditType creditType){
        double x;
        switch (creditType){
            case HOME:
                x = 0.011;
                break;
            case VEHICLE:
                x = 0.017;
                break;
            case STUDIES:
                x = 0.009;
                break;
            case WALLET:
                x = 0.008;
                break;
            case FREE:
                x = 0.015;
                break;
            default:
                x = 0;
                break;
        }

        return  x;


    }

}
