package prog.lab5.excpt;
public class MatrixException extends RuntimeException {

    private String errorStr;

    public MatrixException (String string) {
        this.errorStr = string;
        System.out.println("This is a MatrixException...");
    }

    public void getMassage() {
        System.err.println("Error: " + errorStr);
    }
}