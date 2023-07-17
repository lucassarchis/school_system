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

public class AlunoPersistenteControlador {

	// ADICIONA

	public void adiciona(AlunoPersistente alunoPersistente) {
		try {
			EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
			em.getTransaction().begin();
			em.merge(alunoPersistente);
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
		boolean gerarPdf = gerarPdf(getAlunosTodos(), pdf, pathDoPdf);
		if (gerarPdf == true) {
			System.out.println("PDF gerado com sucesso!");
		} else {
			System.out.println("Falha ao gerar PDF!");
		}
	}

	private static boolean gerarPdf(
			List<AlunoPersistente> listaAlunos, Document pdf,
			String pathDoPdf) {
		try {
			PdfWriter writer = PdfWriter.getInstance(pdf, new FileOutputStream(
					"Trabalho/Workspace/Escola/downloads/tabela_alunos.pdf"));
			pdf.open();
			pdf = AlunoUtil.adicionarHeaderNoPdf(pdf, writer);
			pdf = AlunoUtil.adicionarCorpoAoPdf(listaAlunos, pdf);
			pdf.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// RETIRA

	public void remover(AlunoPersistente aluno) {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		em.getTransaction().begin();
		em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
		int id = aluno.getId();
		aluno = em.find(AlunoPersistente.class, id);
		em.remove(aluno);
		em.getTransaction().commit();
		em.close();
	}

	public void removerTodos() {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		CriteriaQuery<AlunoPersistente> query = em.getCriteriaBuilder().createQuery(AlunoPersistente.class);
		query.select(query.from(AlunoPersistente.class));
		List<AlunoPersistente> lista = em.createQuery(query).getResultList();
		em.getTransaction().begin();
		em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
		for (AlunoPersistente a : lista) {
			em.remove(a);
		}
		em.getTransaction().commit();
		em.close();
	}

	// PESQUISA

	public static List<AlunoPersistente> getAlunosTodos() {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		CriteriaQuery<AlunoPersistente> query = em.getCriteriaBuilder().createQuery(AlunoPersistente.class);
		query.select(query.from(AlunoPersistente.class));
		List<AlunoPersistente> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}

	public static List<AlunoPersistente> pesquisarTermo(String termoPesquisa) {
		EntityManager em = JPAUtil.getInstancia().getEntidadeManager();
		String jpql = "SELECT a FROM AlunoPersistente a WHERE a.nomeCompleto LIKE :nomeCompleto";
		TypedQuery<AlunoPersistente> query = em.createQuery(jpql, AlunoPersistente.class);
		query.setParameter("nomeCompleto", termoPesquisa + "%");
		return query.getResultList();
	}

}
