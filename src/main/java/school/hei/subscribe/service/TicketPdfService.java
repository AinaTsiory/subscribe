package school.hei.subscribe.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

// Dépendance Gradle à ajouter :
// implementation 'io.github.openhtmltopdf:openhtmltopdf-pdfbox:1.1.40'
@Component
public class TicketPdfService {

    @SneakyThrows
    public File generatePdf(String html, String filePrefix) {
        File pdfFile = File.createTempFile(filePrefix, ".pdf");
        try (OutputStream os = new FileOutputStream(pdfFile)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, "");
            builder.toStream(os);
            builder.run();
        }
        return pdfFile;
    }
}
