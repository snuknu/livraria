package livraria.dao;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import livraria.modelo.Usuario;

public class UsuarioDAO {

	public boolean existe(Usuario usuario) {

		EntityManager em = new JPAUtil().getEntityManager();

		TypedQuery<Usuario> query = em.createQuery(
				"select u from Usuario u " + " where u.email = :pEmail and u.senha = :pSenha ", Usuario.class);

		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());

		try {
			Objects.nonNull(query.getSingleResult());
		} catch (NoResultException nre) {
			return false;
		}

		em.close();

		return true;
	}

}
