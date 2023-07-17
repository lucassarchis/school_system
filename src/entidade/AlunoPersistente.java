package entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "alunos")
@DiscriminatorValue("ALUNO")
@Cacheable(false)
public class AlunoPersistente extends UsuarioPersistente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	
	// DECLARACAO
	
	@ManyToMany
	@JoinTable(name = "aluno_curso", joinColumns = @JoinColumn(name = "id_aluno"), inverseJoinColumns = @JoinColumn(name = "id_curso"))
	private List<CursoPersistente> cursosMatriculados;
	
	private String turma;
	
	private String nomePai;
	
	private String nomeMae;
	
	
	
	//GET E SETTERS
	
	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public List<CursoPersistente> getCursosMatriculados() {
		return cursosMatriculados;
	}

	public void setCursosMatriculados(List<CursoPersistente> cursosMatriculados) {
		this.cursosMatriculados = cursosMatriculados;
	}
	
	
	
	//METODOS
	
	@Override
	public String toString() {
		return this.getNomeCompleto();
	}
	
	
	
}
