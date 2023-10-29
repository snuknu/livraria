package livraria.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.persistence.EntityManager;

public class ConnectionPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {

		System.out.println("FASE: " + event.getPhaseId().getName());

		FacesContext facesContext = FacesContext.getCurrentInstance();
		PhaseId phaseId = facesContext.getCurrentPhaseId();

		if (PhaseId.RESTORE_VIEW.equals(phaseId)) {
			List<EntityManager> entityManagerList = new ArrayList<EntityManager>();
			facesContext.getAttributes().put("disconnectAfterRendering", entityManagerList);
		}

		if (PhaseId.RENDER_RESPONSE.equals(phaseId)) {
			List<?> entityManagerList = (List<?>) facesContext.getAttributes().get("disconnectAfterRendering");

			if (entityManagerList != null) {
				entityManagerList.forEach(entityManager -> ((EntityManager) entityManager).close());
				System.out.println("ENDING CONNECTION");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
