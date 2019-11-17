
package acme.features.authenticated.provider.request;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedProviderRequestCreateService implements AbstractCreateService<Provider, Request> {

	@Autowired
	AuthenticatedProviderRequestRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadLine", "text", "reward", "ticker");

	}

	@Override
	public Request instantiate(final acme.framework.components.Request<Request> request) {
		assert request != null;
		Request result;

		result = new Request();

		return result;
	}

	@Override
	public void validate(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {

		boolean isConfirmed;

		isConfirmed = request.getModel().getBoolean("checkbox");
		errors.state(request, isConfirmed, "checkbox", "authenticated.provider.request.error.must-confirmed");
	}

	@Override
	public void create(final acme.framework.components.Request<Request> request, final Request entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		this.repository.save(entity);

	}

}
