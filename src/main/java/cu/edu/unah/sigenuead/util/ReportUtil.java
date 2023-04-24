package cu.edu.unah.sigenuead.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

public class ReportUtil {

    /*
    Cargar los datos principales de un reporte y mostrarlo en pantalla
    */
    public void PDF(java.util.Map<String, Object> params, String jasperpath, String fileName) throws JRException, IOException, NullPointerException {
        try {
            if (params == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Debe seleccionar o introducir un valor", "Error"));
            }
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperpath);
            File file = new File(relativeWebPath);
            Connection con = connection.getConnectionInstance("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/SIGENU_EaD", "postgres", "postgres");
            String filename = file.getPath();
            JasperPrint print = JasperFillManager.fillReport(filename, params, con);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(print, stream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: El elemento seleccionado no presenta la infomación solicitada", "Error"));
        }
    }

    public void RTF(java.util.Map<String, Object> params, String jasperpath, String fileName) throws JRException, IOException, NullPointerException {
        try {
            if (params == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Debe seleccionar o introducir un valor", "Error"));
            }
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperpath);
            File file = new File(relativeWebPath);
            Connection con = connection.getConnectionInstance("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/SIGENU_EaD", "postgres", "postgres");
            String filename = file.getPath();
            JasperPrint print = JasperFillManager.fillReport(filename, params, con);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment;filename=" + fileName + ".rtf");
            ServletOutputStream stream = response.getOutputStream();
            JRRtfExporter exporter = new JRRtfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
            exporter.exportReport();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: El elemento seleccionado no presenta la infomación solicitada", "Error"));
        }
    }

    public void DOC(java.util.Map<String, Object> params, String jasperpath, String fileName) throws JRException, IOException, NullPointerException {
        try {
            if (params == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Debe seleccionar o introducir un valor", "Error"));
            }
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperpath);
            File file = new File(relativeWebPath);
            Connection con = connection.getConnectionInstance("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/SIGENU_EaD", "postgres", "postgres");
            String filename = file.getPath();
            JasperPrint print = JasperFillManager.fillReport(filename, params, con);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment;filename=" + fileName + ".doc");
            ServletOutputStream stream = response.getOutputStream();
            JRDocxExporter exporter = new JRDocxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
            exporter.exportReport();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: El elemento seleccionado no presenta la infomación solicitada", "Error"));
        }
    }
}
