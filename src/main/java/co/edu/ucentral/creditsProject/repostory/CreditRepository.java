package co.edu.ucentral.creditsProject.repostory;

import co.edu.ucentral.creditsProject.repostory.entity.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, String> {
}
