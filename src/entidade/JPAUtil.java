package entidade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static JPAUtil instancia;
	private EntityManagerFactory factory;

	private JPAUtil() {
	factory = Persistence.createEntityManagerFactory("Escola");
	}

	public static JPAUtil getInstancia() {
	if (instancia == null) {
	instancia = new JPAUtil();
	}
	return instancia;
	}

	public EntityManager getEntidadeManager() {
	return factory.createEntityManager();
	}
	
}