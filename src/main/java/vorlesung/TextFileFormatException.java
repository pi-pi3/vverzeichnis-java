package vorlesung;

public class TextFileFormatException extends Exception {
    private static final long serialVersionUID = 1l;

    private String filename;
    private int line;
    private int position;

    public TextFileFormatException(String filename, int line) {
        this(filename, line, 0);
    }

    public TextFileFormatException(String filename, int line, int position) {
        this.filename = filename;
        this.line = line;
        this.position = position;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("in file: `");
        str.append(this.filename);
        str.append("` invalid line at: ");
        str.append(this.line);
        str.append(" at character: ");
        //str.append(this.position);
        str.append('?');

        return str.toString();
    }
}
