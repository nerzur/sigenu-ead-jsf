package cu.edu.unah.sigenuead.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

import java.io.*;
import java.sql.Connection;
import java.util.Map;

/**
 * This class contains the functions necessary to process a JasperReports report.
 * @version 1.0
 * @author Nerzur
 */
public class ReportUtil {

    /**
     * This method converts a ByteArrayOutputStream to a StreamContent and gives it a file name.
     * @param outputStream OutputStream container for file information
     * @param fileName Final file name.
     * @return Return a StreamContent with the report content.
     * @throws IOException Launched when input/output errors exist.
     */
    private StreamedContent getStreamedcontent(ByteArrayOutputStream outputStream, String fileName) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        SerializableSupplier<InputStream> supplier = () -> inputStream;
        StreamedContent streamedContent = DefaultStreamedContent.builder()
                .contentType("application/octet-stream")
                .name(fileName)
                .stream(supplier)
                .build();
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return streamedContent;
    }

    /**
     * This method generates an object of type StreamedContent that contains the result of the report processing indicated in the jasperpath parameter.
     * @param params Parameters used in the report.
     * @param jasperpath Path where the report is located.
     * @param fileName Name of the file to generate.
     * @param fileType Type of file to generate
     * @return Stream containing the document.
     * @throws IOException Launched when input/output errors exist.
     * @throws JRException Launched when there are errors when processing the file with Jasper Reports.
     */
    public StreamedContent processReport(Map<String, Object> params, String jasperpath, String fileName, FileType fileType) throws IOException, JRException {
        if (params == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Debe seleccionar o introducir un valor", "Error"));
        }
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperpath);
        File file = new File(relativeWebPath);
        Connection con = connection.getConnectionInstance("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/SIGENU_EaD", "postgres", "postgres");
        String filename = file.getPath();
        JasperPrint print = JasperFillManager.fillReport(filename, params, con);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        switch (fileType) {
            case PDF -> JasperExportManager.exportReportToPdfStream(print, outputStream);
            case RTF -> {
                JRRtfExporter exporter = new JRRtfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                exporter.exportReport();
            }
            case DOCX -> {
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
                exporter.exportReport();
            }
        }
        return getStreamedcontent(outputStream, fileName);
    }
}
