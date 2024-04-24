package co.edu.ucentral.creditsProject.dto;

import co.edu.ucentral.creditsProject.config.CreditType;
import co.edu.ucentral.creditsProject.config.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit {

    private String id;
    private boolean active;
    private double interest;
    private double totalAmount;
    private int monthsTime;
    private Date datePayment;
    private Status status;
    private CreditType creditType;
    private Officer officer;
    private Client client;


}
