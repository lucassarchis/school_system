package entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public class CursoPersistenteControlador {
	
	//ADICIONA
	
	public static void adiciona(CursoPersistente cursoPersistente) {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		em.getTransaction().begin();
		em.merge(cursoPersistente);
		em.getTransaction().commit();
		em.close();
	}
	
	
	//RETIRA
	
	public static void remover(CursoPersistente curso) {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		em.getTransaction().begin();
		em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
		int id = curso.getId();
		curso = em.find(CursoPersistente.class, id);
		em.remove(curso);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void removerTodos() {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		CriteriaQuery<CursoPersistente> query = em.getCriteriaBuilder().createQuery(CursoPersistente.class);
		query.select(query.from(CursoPersistente.class));
		List<CursoPersistente> lista = em.createQuery(query).getResultList();
		em.getTransaction().begin();
		em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
		for (CursoPersistente c : lista) {
			em.remove(c);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	
	//PESQUISA
	
	public static List<CursoPersistente> getCursosPersistentes() {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		CriteriaQuery<CursoPersistente> query = em.getCriteriaBuilder().createQuery(CursoPersistente.class);
		query.select(query.from(CursoPersistente.class));
		List<CursoPersistente> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}
	
	public CursoPersistente cursoPorId(int id) {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		CursoPersistente instancia = em.find(CursoPersistente.class, id);
		em.close();
		return instancia;

	}
	
	public static List<CursoPersistente> getCursosSemProfessores() {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		CriteriaQuery<CursoPersistente> query = em.getCriteriaBuilder().createQuery(CursoPersistente.class);
		query.select(query.from(CursoPersistente.class));
		List<CursoPersistente> lista = em.createQuery(query).getResultList();
		List<CursoPersistente> lista2 = new ArrayList<CursoPersistente>();
		for (CursoPersistente c : lista) {
			if (c.getProfessor()==null) {
				lista2.add(c);
			}
		}
		return lista2;
	}
	
	public static List<CursoPersistente> pesquisarTermo(String termoPesquisa){
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		String jpql = "SELECT c FROM CursoPersistente c WHERE c.nomeCurso LIKE :nomeCurso";
		TypedQuery<CursoPersistente> query =  em.createQuery(jpql, CursoPersistente.class);
		query.setParameter("nomeCurso", termoPesquisa + "%");
		return query.getResultList();
		
	}
	
	
	
	
	

	
}
