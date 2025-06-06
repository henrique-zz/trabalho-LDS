package org.lojadediscos.services;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.lojadediscos.dao.DiscoDAO;
import org.lojadediscos.model.Disco;

import java.io.IOException;
import java.util.List;

/**
 * Classe com a lógica implementada para a geração do relatório de estoque de discos em um arquivo PDF, usando a bibliote
 * ca iText do Java.
 */

public class RelatorioDiscos {

    public void gerarPDF() {
        String destino = "./relatorio_estoque_discos.pdf";

        try {
            // cria o PDF
            PdfWriter writer = new PdfWriter(destino);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // título do PDF
            PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            Paragraph titulo = new Paragraph("Relatório de Estoque de Discos")
                    .setFont(bold)
                    .setFontSize(16);
            document.add(titulo);

            document.add(new Paragraph("\n")); // espaço

            // cabeçalho
            float[] colunas = {50, 120, 100, 80, 60, 60};
            Table tabela = new Table(colunas);

            tabela.addCell("ID");
            tabela.addCell("Título");
            tabela.addCell("Artista");
            tabela.addCell("Gênero");
            tabela.addCell("Preço");
            tabela.addCell("Estoque");

            // dados da tabela
            DiscoDAO dao = new DiscoDAO();
            List<Disco> discos = dao.listarTodos();

            for (Disco disco : discos) {
                tabela.addCell(String.valueOf(disco.getId()));
                tabela.addCell(disco.getTitulo());
                tabela.addCell(disco.getArtista());
                tabela.addCell(disco.getGenero());
                tabela.addCell(String.format("R$ %.2f", disco.getPreco()));
                tabela.addCell(String.valueOf(disco.getEstoque()));
            }

            document.add(tabela);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Total de discos cadastrados: " + discos.size()));

            document.close();
            System.out.println("Relatório gerado em: " + destino);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

