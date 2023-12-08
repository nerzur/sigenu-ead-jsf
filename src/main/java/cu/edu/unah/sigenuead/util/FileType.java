package cu.edu.unah.sigenuead.util;

/**
 * This enum contains the possible report output filetypes.
 * @author Gabriel
 * @version 1.0
 */
public enum FileType {

    PDF("PDF"),
    DOCX("DOCX"),
    RTF("RTF");

    private final String text;

    FileType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
