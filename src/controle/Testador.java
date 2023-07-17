package controle;

import entidade.UsuarioPersistente;
import entidade.UsuarioPersistenteControlador;

public class Testador {

	public static void main(String[] args) {
		UsuarioPersistente user = UsuarioPersistenteControlador.pesquisarLogin("joao.jose");
		System.out.println(user.getSenha());
		

	}

}
