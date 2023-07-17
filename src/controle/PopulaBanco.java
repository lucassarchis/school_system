//package controle;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import entidade.AlunoPersistente;
//import entidade.CursoPersistente;
//import entidade.JPAUtil;
//import entidade.ProfessorPersistente;
//
//public class PopulaBanco {
//
//	public static void main(String[] args) {
////		CursoPersistente matematica = geraCurso("Matematica");
////		salvar(matematica);
////		CursoPersistente portugues = geraCurso("Português");
////		salvar(portugues);
////		CursoPersistente biologia = geraCurso("Biologia");
////		salvar(biologia);
////		CursoPersistente filosofia = geraCurso("Filosofia");
////		salvar(filosofia);
////		CursoPersistente geografia = geraCurso("Geografia");
////		salvar(geografia);
////		CursoPersistente historia = geraCurso("História");
////		salvar(historia);
////		CursoPersistente ingles = geraCurso("Inglês");
////		salvar(ingles);
////		CursoPersistente quimica = geraCurso("Química");
////		salvar(quimica);
////		CursoPersistente sociologia = geraCurso("Sociologia");
////		salvar(sociologia);
//		EntityManager em = new JPAUtil().getEntityManager();
//		em.getTransaction().begin();
//		
////		ProfessorPersistente prof1 = geraProfessor("Professor Matemática", new GregorianCalendar(2010, 01, 10),
////				"professor.matematica", "1234", new GregorianCalendar(2010, 01, 10), buscaPorId(1));
////
////		ProfessorPersistente prof2 = geraProfessor("Professor Português", new GregorianCalendar(2010, 01, 10),
////				"professor.Português", "1234", new GregorianCalendar(2010, 01, 10), buscaPorId(2));
////
////		ProfessorPersistente prof3 = geraProfessor("Professor Biologia", new GregorianCalendar(2010, 01, 10),
////				"professor.Biologia", "1234", new GregorianCalendar(2010, 01, 10), buscaPorId(3));
////
////
////		ProfessorPersistente prof4 = geraProfessor("Professor Filosofia", new GregorianCalendar(2010, 01, 10),
////				"professor.Filosofia", "1234", new GregorianCalendar(2010, 01, 10), buscaPorId(4));
////		
////
////		ProfessorPersistente prof5 = geraProfessor("Professor Geografia", new GregorianCalendar(2010, 01, 10),
////				"professor.Geografia", "1234", new GregorianCalendar(2010, 01, 10), buscaPorId(5));
////		
////
////		ProfessorPersistente prof6 = geraProfessor("Professor História", new GregorianCalendar(2010, 01, 10),
////				"professor.História", "1234", new GregorianCalendar(2010, 01, 10), buscaPorId(6));
////		
////
////		ProfessorPersistente prof7 = geraProfessor("Professor Inglês", new GregorianCalendar(2010, 01, 10),
////				"professor.Inglês", "1234", new GregorianCalendar(2010, 01, 10), buscaPorId(7));
////		
////
////		ProfessorPersistente prof8 = geraProfessor("Professor Química", new GregorianCalendar(2010, 01, 10),
////				"professor.Química", "1234", new GregorianCalendar(2010, 01, 10), buscaPorId(8));
////		
////
////		ProfessorPersistente prof9 = geraProfessor("Professor Sociologia", new GregorianCalendar(2010, 01, 10),
////				"professor.Sociologia", "1234", new GregorianCalendar(2010, 01, 10), buscaPorId(9));
////		
//		List<CursoPersistente> cursosMatriculados = new ArrayList<CursoPersistente>();
//		cursosMatriculados.add(buscaPorId(6));
//		cursosMatriculados.add(buscaPorId(7));
//		cursosMatriculados.add(buscaPorId(8));
//		cursosMatriculados.add(buscaPorId(9));
//		cursosMatriculados.add(buscaPorId(1));
//		AlunoPersistente aluno3 = geraAluno("Aluno 3", new GregorianCalendar(2010, 01, 10), "loginaluno", "senhaaluno",
//				"Quinto ano", "Pai teste", "Mae teste", cursosMatriculados);
//	
////		em.merge(prof1);
////		em.merge(prof2);
////		em.merge(prof3);
////		em.merge(prof4);
////		em.merge(prof5);
////		em.merge(prof6);
////		em.merge(prof7);
////		em.merge(prof8);
////		em.merge(prof9);
//		em.merge(aluno3);
//		em.getTransaction().commit();
//		em.close();
//
//	}
//
////	private static CursoPersistente geraCurso(String nome) {
////		CursoPersistente curso = new CursoPersistente();
////		curso.setNomeCurso(nome);
////		return curso;
////	}
//
//	private static ProfessorPersistente geraProfessor(String nome, Calendar datanascimento, String login, String senha,
//			Calendar dataContratacao, CursoPersistente cursoMinistrado) {
//		ProfessorPersistente professor = new ProfessorPersistente();
//		professor.setNomeCompleto(nome);
//		professor.setDataNascimento(datanascimento);
//		professor.setLogin(login);
//		professor.setSenha(senha);
//		professor.setDataContratacao(dataContratacao);
//		professor.setCursoMinistrados(cursoMinistrado);
//		cursoMinistrado.setProfessor(professor);
//		return professor;
//	}
//
//	private static AlunoPersistente geraAluno(String nome, Calendar datanascimento, String login, String senha,
//			String turma, String nomePai, String nomeMae, List<CursoPersistente> cursos) {
//		AlunoPersistente aluno = new AlunoPersistente();
//		aluno.setNomeCompleto(nome);
//		aluno.setDataNascimento(datanascimento);
//		aluno.setLogin(login);
//		aluno.setSenha(senha);
//		aluno.setTurma(turma);
//		aluno.setNomePai(nomePai);
//		aluno.setNomeMae(nomeMae);
//		aluno.setCursosMatriculados(cursos);
//		return aluno;
//	}
//	
////	private static void salvar (CursoPersistente c) {
////		EntityManager em = new JPAUtil().getEntityManager();
////		em.getTransaction().begin();
////		em.persist(c);
////		em.getTransaction().commit();
////		em.refresh(c);
////		em.close();
////	}
//	
//	public static CursoPersistente buscaPorId(Integer id) {
//		EntityManager em = new JPAUtil().getEntityManager();
//		CursoPersistente instancia = em.find(CursoPersistente.class, id);
//		em.close();
//		return instancia;
//	}
//
//}
