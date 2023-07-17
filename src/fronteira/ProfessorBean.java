package fronteira;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import entidade.CursoPersistente;
import entidade.CursoPersistenteControlador;
import entidade.ProfessorPersistente;
import entidade.ProfessorPersistenteControlador;

@ManagedBean
@ViewScoped
public class ProfessorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// DECLARACAO DE VARIAVEL

	private ProfessorPersistente professor = new ProfessorPersistente();
	@SuppressWarnings("unused")
	private ProfessorPersistente professorIntermediario;
	private int cursoId;
	private ProfessorPersistente professorSelecionado = null;
	private List<ProfessorPersistente> listaProfessores;
	private String termoPesquisa;

	// METODOS

	// PESQUISA

	public void prepararNovoProfessor() {
		professor = new ProfessorPersistente();
		cursoId = 0;
	}

	public List<ProfessorPersistente> pesquisar() {
		listaProfessores = ProfessorPersistenteControlador
				.pesquisarTermo(termoPesquisa);
		return listaProfessores;
	}

	public List<ProfessorPersistente> getProfessorTodos() {
		new ProfessorPersistenteControlador();
		listaProfessores = ProfessorPersistenteControlador
				.getProfessoresTodos();
		return listaProfessores;
	}

	public List<CursoPersistente> getCursosSemProfessor() {
		new CursoPersistenteControlador();
		List<CursoPersistente> listaCursos = CursoPersistenteControlador
				.getCursosSemProfessores();
		return listaCursos;
	}

	// ADICIONA

	public void gravarProfessor() {
		new ProfessorPersistenteControlador().adiciona(this.professor);
		this.professor = new ProfessorPersistente();
		getProfessorTodos();
	}

	public void gravarCurso() {
		CursoPersistente cursoMinistrado = new ProfessorPersistenteControlador()
				.cursoPorId(this.cursoId);
		this.professor.setCursoMinistrados(cursoMinistrado);
	}

	public void gerarTabelaPdf() {
		ProfessorPersistenteControlador.gerarTabelaPdf();
	}
	
	private StreamedContent file;
	
	public StreamedContent getFile() throws FileNotFoundException {
	        InputStream stream = new FileInputStream("Trabalho/Workspace/Escola/downloads/tabela_professor.pdf");
	        file = new DefaultStreamedContent(stream, "application/pdf", "tabela_professor.pdf");
	        File delete = new File("Trabalho/Workspace/Escola/downloads/tabela_professor.pdf");
	        delete.delete(); //estudar sobre file biblioteca
	        return file;
	}

	// REMOVE

	public void excluirProfessor() {
		new ProfessorPersistenteControlador().remover(professor);
		;
		getProfessorTodos();
		this.professor = new ProfessorPersistente();
	}

	public void excluirTodos() {
		new ProfessorPersistenteControlador().removerTodos();
		getProfessorTodos();
		this.professor = new ProfessorPersistente();
	}

	// GETTERS E SETTERS

	public List<ProfessorPersistente> getListaProfessores() {
		return listaProfessores;
	}

	public int getCursoId() {
		return cursoId;
	}

	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}

	public void setListaProfessores(List<ProfessorPersistente> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public ProfessorPersistente getProfessorIntermediario() {
		return professor;
	}

	public void setProfessorIntermediario(ProfessorPersistente professor2) {
		this.professorSelecionado = professor;
		this.professor = professor2;
	}

	public ProfessorPersistente getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(
			ProfessorPersistente professorSelecionado) {

	}

	public ProfessorPersistente getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorPersistente professor) {
		this.professor = professor;
	}

}
