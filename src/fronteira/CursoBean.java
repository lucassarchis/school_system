package fronteira;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidade.CursoPersistente;
import entidade.CursoPersistenteControlador;

@ManagedBean
@ViewScoped
public class CursoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// DECLARACAO DE VARIAVEL
	private CursoPersistente curso = new CursoPersistente();
	private String termoPesquisa;
	private List<CursoPersistente> listaCursos;
	private CursoPersistente cursoSelecionado = null;

	// METODOS

	// PESQUISA
	public void prepararNovoCurso() {
		curso = new CursoPersistente();
	}

	public List<CursoPersistente> pesquisar() {
		this.listaCursos = CursoPersistenteControlador.pesquisarTermo(termoPesquisa);
		return listaCursos;
	}

	public List<CursoPersistente> getCursosTodos() {
		this.listaCursos = CursoPersistenteControlador.getCursosPersistentes();
		return listaCursos;
	}

	// ADICIONA

	public void gravarCurso() {
		CursoPersistenteControlador.adiciona(this.curso);
		getCursosTodos();
		this.curso = new CursoPersistente();
		termoPesquisa = "";
	}

	// REMOVE

	public void excluirCurso() {
		CursoPersistenteControlador.remover(curso);
		getCursosTodos();
		this.curso = new CursoPersistente();
	}

	public void excluirTodos() {
		CursoPersistenteControlador.removerTodos();
		getCursosTodos();
		this.curso = new CursoPersistente();
	}

	// GETTERS E SETTERS

	public CursoPersistente getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(CursoPersistente cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public List<CursoPersistente> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<CursoPersistente> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public CursoPersistente getCurso() {
		return curso;
	}

	public void setCurso(CursoPersistente curso) {

		this.curso = curso;
	}

	public CursoPersistente getCursinho() {
		return curso;
	}

	public void setCursinho(CursoPersistente CURSO) {
		this.cursoSelecionado = curso;
		this.curso = CURSO;
	}

}
