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
        credit.setCreditType(getCreditType(credit.getType()));

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

    @Override
    public double getInterest(CreditType creditType){

        return switch (creditType) {
            case HOME -> 0.011;
            case VEHICLE -> 0.017;
            case STUDIES -> 0.009;
            case WALLET -> 0.008;
            case FREE -> 0.015;
            default -> 0;
        };


    }

    @Override
    public CreditType getCreditType(String creditType){
        return switch (creditType) {
            case "HOME" -> CreditType.HOME;
            case "VEHICLE" -> CreditType.VEHICLE;
            case "STUDIES" -> CreditType.STUDIES;
            case "WALLET" -> CreditType.WALLET;
            case "FREE" -> CreditType.FREE;
            default -> null;
        };
    }

}
