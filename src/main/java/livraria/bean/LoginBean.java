package livraria.bean;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import livraria.dao.DAO;
import livraria.dao.UsuarioDAO;
import livraria.modelo.Usuario;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuarLogin() {
		System.out.println("Fazendo login do usuário " + this.usuario.getEmail());

		boolean existe = new UsuarioDAO().existe(this.usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		
		//context.addMessage("login:email", new FacesMessage("Usuário não encontrado!"));
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado!"));

		return "livro?faces-redirect=true";

	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";

	}
	

	public String efetuarLoginOld() {
		System.out.println("Fazendo login do usuário " + this.usuario.getEmail());

		List<Usuario> usuarios = new DAO<Usuario>(Usuario.class).listaTodos();

		String redirect = "login?faces-redirect=true";

		Optional<Usuario> login = usuarios.stream().filter(usr -> usr.getEmail().equals(this.usuario.getEmail()))
				.collect(Collectors.toList()).stream().findAny();

		if (Objects.nonNull(login.get()) && login.get().getSenha().equals(this.usuario.getSenha()))
			redirect = "livro?faces-redirect=true";

		return redirect;

	}

}
