package fronteira;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidade.AlunoPersistente;
import entidade.AlunoPersistenteControlador;
import entidade.CursoPersistente;
import entidade.CursoPersistenteControlador;
import entidade.ProfessorPersistente;
import entidade.ProfessorPersistenteControlador;

@ManagedBean(name="graficosBean")
@ViewScoped
public class GraficosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<AlunoPersistente> listaAlunos;
	private List<ProfessorPersistente> listaProfessores;
	private List<CursoPersistente> listaCursos;
	
	public GraficosBean() {
		getCursosEAlunos();
	}
	
	public int getNumeroAlunos() {
		new AlunoPersistenteControlador();
		listaAlunos = AlunoPersistenteControlador.getAlunosTodos();
		return listaAlunos.size();
	}
	
	public String getCursosEAlunos(){
		new CursoPersistenteControlador();
		listaCursos = CursoPersistenteControlador.getCursosPersistentes();
		String stringFinal = "";
		int index = 0;
		for(CursoPersistente c : listaCursos){
			if(index==0){
				stringFinal = "['"+c.getNomeCurso()+"', " + c.getAlunos().size()+"]" ;
				index=1;
			} else {
				stringFinal = stringFinal + ", ['"+c.getNomeCurso()+"', " + c.getAlunos().size()+"]";
			}
			
		}
		System.out.println(stringFinal);
		return stringFinal;
	}
	
	public int getNumeroProfessores() {
		new ProfessorPersistenteControlador();
		listaProfessores = ProfessorPersistenteControlador.getProfessoresTodos();
		return listaProfessores.size();
	}
	
	
}
