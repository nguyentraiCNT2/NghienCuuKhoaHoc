package com.KnowledgeHubbackend.algorithm;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;
import java.io.InputStream;

public class DocToPdf {
    public static void convertDocToPdf(InputStream docInputStream, String pdfFilePath) throws IOException {
        try (XWPFDocument doc = new XWPFDocument(docInputStream);
             PDDocument pdf = new PDDocument()) {

            PDPage page = new PDPage();
            pdf.addPage(page);

            try (PDPageContentStream contents = new PDPageContentStream(pdf, page)) {
                PDFont font = PDType1Font.HELVETICA;
                contents.setFont(font, 12);
                contents.setLeading(14.5f);
                for (XWPFParagraph paragraph : doc.getParagraphs()) {
                    String text = paragraph.getText();
                    contents.beginText();
                    contents.newLineAtOffset(100, 700);
                    contents.showText(text);
                    contents.newLine();
                    contents.endText();

                }

            }catch (Exception e) {
                e.printStackTrace();
            }
            pdf.save(pdfFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
