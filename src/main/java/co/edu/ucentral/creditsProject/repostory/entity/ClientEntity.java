package co.edu.ucentral.creditsProject.repostory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Client")
@Table(name = "CLIENT")
@Builder
public class ClientEntity {

    @Id
    private String id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
