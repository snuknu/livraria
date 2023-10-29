package livraria.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

public class DisconnectAfterRendering {

	public static void close(EntityManager entityManager) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<Object, Object> attributes = facesContext.getAttributes();

		List<?> genericList = (List<?>) attributes.get("disconnectAfterRendering");

		List<EntityManager> entityManagerList = new ArrayList<EntityManager>();

		if (entityManager != null)
			entityManagerList.add(entityManager);

		if (genericList != null)

			genericList.forEach(object -> {
				EntityManager em = (EntityManager) object;
				entityManagerList.add(em);
			});

		attributes.put("disconnectAfterRendering", entityManagerList);
		System.out.println("ENDING CONNECTION");
	}
}
