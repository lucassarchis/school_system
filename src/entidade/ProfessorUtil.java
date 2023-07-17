package entidade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ProfessorUtil {
	private static Font fonteCelula = new Font(FontFamily.TIMES_ROMAN, 8);
	private static Font fonteColuna = new Font(FontFamily.TIMES_ROMAN, 12);

	public static Document adicionarHeaderNoPdf(Document pdf, PdfWriter writer) {
		Font fontNomeRelatorio = new Font(Font.FontFamily.TIMES_ROMAN, 14,
				Font.BOLDITALIC);
		Phrase titulo = new Phrase("Lista de Professores", fontNomeRelatorio);
		return adicionarParteComunHeader(pdf, writer, titulo);
	}

	public static Document adicionarCorpoAoPdf(
			List<ProfessorPersistente> listaProfessores, Document pdf) {
		try {
			PdfPTable tabela = construirEstruturaDaTabela();
			tabela = adicionarConteudoParaTabela(listaProfessores, tabela);
			pdf.add(tabela);

			return pdf;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PdfPTable construirEstruturaDaTabela() {
		PdfPTable tabela = new PdfPTable(3);
		tabela.setWidthPercentage(100f);
		tabela = adicionarTituloATabela(tabela, "Tabela de professores");
		tabela = adicionarColuna("Nome Professor", 1, tabela);
		tabela = adicionarColuna("Curso Ministrado", 1, tabela);
		tabela = adicionarColuna("Contratação", 1, tabela);
		return tabela;
	}

	public static PdfPTable adicionarConteudoParaTabela(
			List<ProfessorPersistente> listaObjetos, PdfPTable tabela) {

		for (ProfessorPersistente p : listaObjetos) {
			tabela = adicionarCelula(p.getNomeCompleto(), tabela);
			tabela = adicionarCelula(p.getCursoMinistrados().getNomeCurso(), tabela);
			tabela = adicionarCelula(new SimpleDateFormat("dd/MM/yyyy").format(p.getDataContratacao()), tabela);
		}
		return tabela;
	}

	public static PdfPTable adicionarCelula(String conteudoDaCelula,
			PdfPTable tabela) {
		PdfPCell cell;
		cell = new PdfPCell(new Phrase(conteudoDaCelula, fonteCelula));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tabela.addCell(cell);
		return tabela;
	}

	public static PdfPTable adicionarColuna(String tituloColuna, int i,
			PdfPTable tabela) {
		PdfPCell cell;
		cell = new PdfPCell(new Phrase(tituloColuna, fonteColuna));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBackgroundColor(new GrayColor(0.8f));
		tabela.addCell(cell);

		return tabela;
	}

	public static PdfPTable adicionarTituloATabela(PdfPTable tabela,
			String titulo) {

		PdfPCell celula = new PdfPCell(new Phrase(titulo));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celula.setColspan(3);
		celula.setRowspan(1);
		celula.setBackgroundColor(new GrayColor(0.7f));
		tabela.addCell(celula);

		return tabela;
	}

	public static Document adicionarParteComunHeader(Document pdf,
			PdfWriter writer, Phrase titulo) {

		PdfContentByte cb = writer.getDirectContent();
		// Passo 2 ----> determinada a data que a verificação foi finalizada.
		Phrase data = new Phrase("Data:  "
				+ new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		// Passo 3 ----> adiciona a logo no pdf.
		//		pdf = adicionarLogo(pdf);

		// Passo 4 ----> define a posição do título do pdf.
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, titulo,
				pdf.left() + 320, pdf.top() + 85, 0);
		// Passo 5 ----> define a posição da data no pdf.
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, data,
				pdf.left() + 320, pdf.top() + 60, 0);

		// Passo 6 ----> adiciona box que ficará envolta da logo e do título.
		PdfContentByte canvas = writer.getDirectContent();
		GrayColor preto = new GrayColor(1);
		canvas.setColorStroke(preto);
		canvas.moveTo(pdf.left(), pdf.top() + 30);
		canvas.lineTo(pdf.right(), pdf.top() + 30);
		canvas.closePathStroke();

		return pdf;
	}
}
