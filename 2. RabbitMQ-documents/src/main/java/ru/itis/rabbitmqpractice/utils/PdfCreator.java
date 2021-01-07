package ru.itis.rabbitmqpractice.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.rabbitmqpractice.models.User;

import java.io.FileNotFoundException;
import java.util.UUID;

@Component
public class PdfCreator {
    private final static String DISMISSAL_FP = "C:\\Users\\rusel\\IdeaProjects\\RabbitMQ-documents\\pdfs\\dismissal\\";
    private final static String REFERENCE_FP = "C:\\Users\\rusel\\IdeaProjects\\RabbitMQ-documents\\pdfs\\reference\\";

    public static void createPdf(User user, String title) throws FileNotFoundException {
        String filePath = "";
        if (title.equals("Dismissal"))
            filePath = DISMISSAL_FP;
        else if (title.equals("Income reference") || title.equals("Medical reference"))
            filePath = REFERENCE_FP;
        String fp = filePath + title + "_" + user.getLastName() + UUID.randomUUID().toString() + ".pdf";
        System.out.println("Full filepath: " + fp);
        PdfDocument pdf = new PdfDocument(new PdfWriter(fp));
        Document document = new Document(pdf);

        Style style = new Style().setFontSize(20);

        document.add(new Paragraph(title).addStyle(style));
        document.add(new Paragraph("Name: " + user.getFirstName()));
        document.add(new Paragraph("Surname: " + user.getLastName()));
        document.add(new Paragraph("Password Number: " + user.getPassportNumber()));
        document.add(new Paragraph("Date of passport: " + user.getPassportDate()));
        document.add(new Paragraph("Age: " + user.getAge()));
        document.close();
    }
}
