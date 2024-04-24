package co.edu.ucentral.creditsProject.adapter;

import co.edu.ucentral.creditsProject.service.CreditService;

public class CreditAdapter implements CreditService {

    @Override
    public double quotesimulation(double totalAmount, double interest, double monthsTime) {
        double total = totalAmount  / monthsTime;
        return total + (total * interest);
    }
}
