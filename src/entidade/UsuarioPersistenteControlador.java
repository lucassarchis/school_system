package entidade;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsuarioPersistenteControlador implements Serializable {

	private static final long serialVersionUID = 1L;

	public static UsuarioPersistente pesquisarLogin(String login) {
		try {
			EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
			String jpql = "SELECT u FROM UsuarioPersistente u WHERE u.login = :login";
			Query query = em.createQuery(jpql);
			query.setParameter("login", login);
			query.setMaxResults(1);
			UsuarioPersistente usuarioAchado = (UsuarioPersistente) query.getSingleResult();
			return usuarioAchado;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(pesquisarLogin("110-31290-321"));
	}

	public static boolean verificarSenhaLogin(UsuarioPersistente usuario, String login, String senha) {
		if (usuario == null) {
			return false;
		}

		String senhaAchada = usuario.getSenha();
		if (usuario.getLogin().equals(login) && senha.equals(senhaAchada)) {
			return true;
		} else {
			return false;
		}
	}

}
