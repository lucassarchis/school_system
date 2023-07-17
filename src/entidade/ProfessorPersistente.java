package entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "professor")
@DiscriminatorValue("PROFESSOR")
public class ProfessorPersistente extends UsuarioPersistente implements Serializable {

	public int getCursoId() {
		return cursoId;
	}

	public void setCursoId(int cursoId) {
		this.cursoId = cursoId;
		CursoPersistente cursoMinistrado = new ProfessorPersistenteControlador().cursoPorId(this.cursoId);
		setCursoMinistrados(cursoMinistrado);
		
	}

	private static final long serialVersionUID = 1L;
	
	
	//DECLARACAO
	
	public ProfessorPersistente() {

	}
	
	@Temporal(TemporalType.DATE)
	private Date dataContratacao;
	
	@Transient
	private int cursoId;
	
	@JoinColumn(name="id_curso")
	@OneToOne
	private CursoPersistente cursoMinistrados;
	
	
	//GETTER E SETTER
	
	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public CursoPersistente getCursoMinistrados() {
		return cursoMinistrados;
	}

	public void setCursoMinistrados(CursoPersistente cursoMinistrados) {
		this.cursoMinistrados = cursoMinistrados;
	}


	//METODOS
	
	@Override
	public String toString() {
		return this.getNomeCompleto();
	}
}
