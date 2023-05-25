package livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import livraria.dao.DAO;
import livraria.modelo.Autor;

@ManagedBean
@RequestScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);

		this.autor = new Autor();
		
		return "livro?faces-redirect=true";
	}
}
