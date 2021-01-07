package services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import dto.User;

import java.io.FileNotFoundException;
import java.util.UUID;

public class PdfService {
    public static void createPdf(String filePath, User user, String title) throws FileNotFoundException {
        String fp = filePath + title + " " + user.getLastName() + ".pdf";
        System.out.println("Full filepath: " + fp);
        PdfDocument pdf = new PdfDocument(new PdfWriter(fp));
        Document document = new Document(pdf);

        Style style = new Style().setFontSize(20);

        document.add(new Paragraph(title).addStyle(style));
        document.add(new Paragraph("Name: " + user.getFirstName()));
        document.add(new Paragraph("Surname: " + user.getLastName()));
        document.add(new Paragraph("Password Number: " + user.getPasswordNumber()));
        document.add(new Paragraph("Date of passport: " + user.getDate()));
        document.add(new Paragraph("Age: " + user.getAge()));
        document.close();
    }
}
