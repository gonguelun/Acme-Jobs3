
package acme.features.administrator.challenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChallengeRepository extends AbstractRepository {

	@Query("select s from Challenge s where s.id=?1")
	Challenge findOneById(int id);

	@Query("select s from Challenge s where s.deadline>=sysdate()")
	Collection<Challenge> findManyAll();
}
