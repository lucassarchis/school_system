package entidade;

import java.io.FileOutputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ProfessorPersistenteControlador {

	// ADICIONA

	public void adiciona(ProfessorPersistente professorPersistente) {
		try {
			EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
			em.getTransaction().begin();
			em.merge(professorPersistente);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Login duplicado",
							"Digite um usuario e senha válidos."));
		}
	}
	
	public static void gerarTabelaPdf() {
		Document pdf = new Document(PageSize.A4, 30, 30, 125, 50);
		String pathDoPdf = "/downloads/teste.pdf";
		boolean gerarPdf = gerarPdf(getProfessoresTodos(), pdf, pathDoPdf);
		if (gerarPdf == true) {
			System.out.println("PDF gerado com sucesso!");
		} else {
			System.out.println("Falha ao gerar PDF!");
		}
	}

	private static boolean gerarPdf(
			List<ProfessorPersistente> listaProfessores, Document pdf,
			String pathDoPdf) {
		try {
			PdfWriter writer = PdfWriter.getInstance(pdf, new FileOutputStream(
					"Trabalho/Workspace/Escola/downloads/tabela_professor.pdf"));
			pdf.open();
			pdf = ProfessorUtil.adicionarHeaderNoPdf(pdf, writer);
			pdf = ProfessorUtil.adicionarCorpoAoPdf(listaProfessores, pdf);
			pdf.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		

	}

	// REMOVE

	public void remover(ProfessorPersistente professor) {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		em.getTransaction().begin();
		em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
		int id = professor.getId();
		professor = em.find(ProfessorPersistente.class, id);
		em.remove(professor);
		em.getTransaction().commit();
		em.close();
	}

	public void removerTodos() {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		CriteriaQuery<ProfessorPersistente> query = em.getCriteriaBuilder()
				.createQuery(ProfessorPersistente.class);
		query.select(query.from(ProfessorPersistente.class));
		List<ProfessorPersistente> lista = em.createQuery(query)
				.getResultList();
		em.getTransaction().begin();
		em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
		for (ProfessorPersistente p : lista) {
			em.remove(p);
		}
		em.getTransaction().commit();
		em.close();

	}

	// PESQUISA

	public static List<ProfessorPersistente> getProfessoresTodos() {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		CriteriaQuery<ProfessorPersistente> query = em.getCriteriaBuilder()
				.createQuery(ProfessorPersistente.class);
		query.select(query.from(ProfessorPersistente.class));
		List<ProfessorPersistente> lista = em.createQuery(query)
				.getResultList();
		em.close();
		return lista;
	}

	public CursoPersistente cursoPorId(int id) {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		CursoPersistente instancia = em.find(CursoPersistente.class, id);
		em.close();
		return instancia;

	}

	public static ProfessorPersistente buscaPeloCurso(int id) {
		try {
			EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
			String jpql = "SELECT p FROM ProfessorPersistente p WHERE p.cursoMinistrados.id = :id";
			TypedQuery<ProfessorPersistente> query = em.createQuery(jpql,
					ProfessorPersistente.class);
			query.setParameter("id", id);
			query.setMaxResults(1);
			return query.getSingleResult();

		} catch (Exception e) {

		}
		return null;
	}

	public static List<ProfessorPersistente> pesquisarTermo(String termoPesquisa) {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		String jpql = "SELECT p FROM ProfessorPersistente p WHERE p.nomeCompleto LIKE :nomeCompleto";
		TypedQuery<ProfessorPersistente> query = em.createQuery(jpql,
				ProfessorPersistente.class);
		query.setParameter("nomeCompleto", termoPesquisa + "%");
		return query.getResultList();
	}
}
