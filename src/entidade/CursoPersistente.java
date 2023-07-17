package entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cursos")
@Cacheable(false)
public class CursoPersistente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	//DECLARACAO
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(mappedBy="cursosMatriculados", cascade = { CascadeType.MERGE, CascadeType.PERSIST } )
	@JoinTable(name = "aluno_curso", joinColumns = @JoinColumn(name = "id_curso"), inverseJoinColumns = @JoinColumn(name = "id_aluno"))
	private List<AlunoPersistente> alunos = new ArrayList<AlunoPersistente>();
	
	@OneToOne(mappedBy="cursoMinistrados")
	private ProfessorPersistente professor;
	
	private String nomeCurso;
	
	
	//GETTER E SETTER
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public List<AlunoPersistente> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<AlunoPersistente> alunos) {
		this.alunos = alunos;
	}

	public ProfessorPersistente getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorPersistente professor) {
		this.professor = professor;
	}
	
	
	
	//METODOS
	
	@Override
	public String toString() {
		return this.nomeCurso;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CursoPersistente other = (CursoPersistente) obj;
		return id == other.id;
	}
	
}
