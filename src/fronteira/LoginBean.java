package fronteira;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entidade.UsuarioPersistenteControlador;
import entidade.UsuarioPersistente;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// DECLARAÇÃO
	private UsuarioPersistente usuario;
	private String login;
	private String senha;

	// METODOS
	// PESQUISA
	public String validateUsernamePassword() {
		usuario = UsuarioPersistenteControlador.pesquisarLogin(login);
		if (UsuarioPersistenteControlador.verificarSenhaLogin(this.usuario, this.login , this.senha) == true) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("username", this.usuario);
			return "/app/home.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuario ou senha incorretos",
							"Digite um usuario e senha válidos."));
			return "";
		}
	}
	// ADICIONA
	public String logout() {
		return "/security/index.xhtml";
	}
	// REMOVE

	// GETTERS E SETTERS
	public LoginBean() {
		login = new String();
		senha = new String();
	}


	public UsuarioPersistente getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPersistente usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}