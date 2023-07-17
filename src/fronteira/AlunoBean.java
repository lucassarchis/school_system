package fronteira;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import entidade.AlunoPersistente;
import entidade.AlunoPersistenteControlador;
import entidade.CursoPersistente;
import entidade.CursoPersistenteControlador;
import entidade.util.JavaScriptUtil;

@ManagedBean
@ViewScoped
public class AlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// DECLARACAO DE VARIAVEL
	private AlunoPersistente alunoSelecionado = null;
	@SuppressWarnings("unused")
	private AlunoPersistente alunoIntermediario;
	private AlunoPersistente aluno = new AlunoPersistente();
	private int cursoId;
	private String termoPesquisa;
	private List<AlunoPersistente> listaAlunos;
	private List<CursoPersistente> lista;
	private List<CursoPersistente> listaCursos;
	private StreamedContent file;

	// METODOS

	// PESQUISA

	public AlunoBean() {
		carregaCursos();
	}

	public void carregaCursos() {
		listaCursos = CursoPersistenteControlador.getCursosPersistentes();
	}

	public List<AlunoPersistente> getAlunoTodos() {
		new AlunoPersistenteControlador();
		listaAlunos = AlunoPersistenteControlador.getAlunosTodos();
		return listaAlunos;
	}

	public List<AlunoPersistente> pesquisar() {
		listaAlunos = AlunoPersistenteControlador.pesquisarTermo(termoPesquisa);
		return listaAlunos;
	}

	public void prepararNovoAluno() {
		aluno = new AlunoPersistente();
		cursoId = 0;
	}
	
	public StreamedContent getFile() throws FileNotFoundException {
	        InputStream stream = new FileInputStream("Trabalho/Workspace/Escola/downloads/tabela_alunos.pdf");
	        file = new DefaultStreamedContent(stream, "application/pdf", "tabela_alunos.pdf");
	        File delete = new File("Trabalho/Workspace/Escola/downloads/tabela_alunos.pdf");
	        delete.delete();
	        return file;
	}

	// ADICIONA

	public void gravarAluno() {
		if(validarCampos()==false) {
			return;
		}
		aluno.setCursosMatriculados(getLista());
		new AlunoPersistenteControlador().adiciona(this.aluno);
		this.aluno = new AlunoPersistente();
		getAlunoTodos();
		JavaScriptUtil.hideDialogo("alunoDialogWidgetVar");
	}
	
	public boolean validarCampos() {
		if(aluno.getNomeCompleto()==null || aluno.getNomeCompleto().trim().length()==0) {
			return false;
		}
		return true;
	}

	public void gravarCursos() {
		CursoPersistente cursoMatriculado = new CursoPersistenteControlador().cursoPorId(this.cursoId);
		this.adicionaCurso(cursoMatriculado);
		this.cursoId = 0;
	}

	public void adicionaCurso(CursoPersistente curso) {
		int index = getLista().indexOf(curso);
		if (index < 0) {
			List<CursoPersistente> list = new ArrayList<CursoPersistente>();
			for (CursoPersistente c : getLista()) {
				list.add(c);
			}
			list.add(curso);
			setLista(list);
		} else {
			getLista().set(index, curso);
		}
	}
	
	public void gerarTabelaPdf() {
		AlunoPersistenteControlador.gerarTabelaPdf();
	}

	// REMOVE

	public void excluirAluno() {
		new AlunoPersistenteControlador().remover(aluno);
		getAlunoTodos();
		this.aluno = new AlunoPersistente();
	}

	public void excluirTodos() {
		new AlunoPersistenteControlador().removerTodos();
		getAlunoTodos();
		this.aluno = new AlunoPersistente();
	}

	public void removeCurso() {
		CursoPersistente cursoPesquisa = new CursoPersistenteControlador().cursoPorId(this.cursoId);
		if (getLista().size()==0) {
			return;
		} else {
			int index = getLista().indexOf(cursoPesquisa);
			System.out.println("Index: " + index);
			getLista().remove(index);
		}
		this.cursoId = 0;
	}

	// GETTERS E SETTERS

	public AlunoPersistente getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(AlunoPersistente alunoSelecionado) {
	}

	public AlunoPersistente getAlunoIntermediario() {
		return aluno;
	}

	public void setAlunoIntermediario(AlunoPersistente aluno2) {
		this.alunoSelecionado = aluno;
		this.aluno = aluno2;
		setLista(aluno.getCursosMatriculados());
	}

	public AlunoPersistente getAluno() {
		return aluno;
	}

	public void setAluno(AlunoPersistente aluno) {
		this.aluno = aluno;
		setLista(aluno.getCursosMatriculados());
	}

	public int getCursoId() {
		return cursoId;
	}

	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public List<AlunoPersistente> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<AlunoPersistente> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public List<CursoPersistente> getLista() {
		if (lista==null) { 
			lista = new ArrayList<CursoPersistente>();
		}
		return lista;
	}

	public void setLista(List<CursoPersistente> lista) {
		this.lista = lista;
	}

	public List<CursoPersistente> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<CursoPersistente> listaCursos) {
		this.listaCursos = listaCursos;
	}
}
