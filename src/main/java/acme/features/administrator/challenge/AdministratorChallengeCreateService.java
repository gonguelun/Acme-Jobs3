
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	private AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goalGold", "rewardGold", "goalSilver", "rewardSilver", "goalBronze", "rewardBronze");

	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isPositiveGold = true, isEuroGold;

		String[] aGold = request.getModel().getString("rewardGold").trim().split("EUR");
		String[] bGold = request.getModel().getString("rewardGold").trim().split("€");

		isEuroGold = aGold.length == 1 && bGold.length == 1 && (request.getModel().getString("rewardGold").contains("€") || request.getModel().getString("rewardGold").contains("EUR"));
		errors.state(request, isEuroGold, "rewardGold", "administrator.challenge.error.must-be-euro");

		if (!errors.hasErrors("rewardGold") && request.getModel().getString("rewardGold").contains("€")) {
			isPositiveGold = Double.parseDouble(request.getModel().getString("rewardGold").replace("€", "")) >= 0;
		} else if (!errors.hasErrors("rewardGold") && request.getModel().getString("rewardGold").contains("EUR")) {
			isPositiveGold = Double.parseDouble(request.getModel().getString("rewardGold").replace("EUR", "")) >= 0;
		}
		errors.state(request, isPositiveGold, "rewardGold", "administrator.challenge.error.must-be-positive");

		boolean isPositiveSilver = true, isEuroSilver;

		String[] aSilver = request.getModel().getString("rewardSilver").trim().split("EUR");
		String[] bSilver = request.getModel().getString("rewardSilver").trim().split("€");

		isEuroSilver = aSilver.length == 1 && bSilver.length == 1 && (request.getModel().getString("rewardSilver").contains("€") || request.getModel().getString("rewardSilver").contains("EUR"));
		errors.state(request, isEuroSilver, "rewardSilver", "administrator.challenge.error.must-be-euro");

		if (!errors.hasErrors("rewardSilver") && request.getModel().getString("rewardSilver").contains("€")) {
			isPositiveSilver = Double.parseDouble(request.getModel().getString("rewardSilver").replace("€", "")) >= 0;
		} else if (!errors.hasErrors("rewardSilver") && request.getModel().getString("rewardSilver").contains("EUR")) {
			isPositiveSilver = Double.parseDouble(request.getModel().getString("rewardSilver").replace("EUR", "")) >= 0;
		}
		errors.state(request, isPositiveSilver, "rewardSilver", "administrator.challenge.error.must-be-positive");

		boolean isPositiveBronze = true, isEuroBronze;

		String[] aBronze = request.getModel().getString("rewardBronze").trim().split("EUR");
		String[] bBronze = request.getModel().getString("rewardBronze").trim().split("€");

		isEuroBronze = aBronze.length == 1 && bBronze.length == 1 && (request.getModel().getString("rewardBronze").contains("€") || request.getModel().getString("rewardBronze").contains("EUR"));
		errors.state(request, isEuroBronze, "rewardBronze", "administrator.challenge.error.must-be-euro");

		if (!errors.hasErrors("rewardBronze") && request.getModel().getString("rewardBronze").contains("€")) {
			isPositiveBronze = Double.parseDouble(request.getModel().getString("rewardBronze").replace("€", "")) >= 0;
		} else if (!errors.hasErrors("rewardBronze") && request.getModel().getString("rewardBronze").contains("EUR")) {
			isPositiveBronze = Double.parseDouble(request.getModel().getString("rewardBronze").replace("EUR", "")) >= 0;
		}
		errors.state(request, isPositiveBronze, "rewardBronze", "administrator.challenge.error.must-be-positive");

		Date deadLineMoment;
		Boolean isFutureDate;

		deadLineMoment = request.getModel().getDate("deadline");

		if (deadLineMoment != null) {
			isFutureDate = deadLineMoment.after(Calendar.getInstance().getTime());
			errors.state(request, isFutureDate, "deadline", "administrator.challenge.error.must-be-future");
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		this.repository.save(entity);

	}

}
