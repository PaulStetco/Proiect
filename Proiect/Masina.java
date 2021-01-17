import java.lang.invoke.BootCartestrapMethodInvoker;eCarte;

public class Carte {
    private String gen;
    private String scriitor;
    private String numarPagini;
    private int an;

    public Carte(String gen, String scriitor, String numarPagini, int an) {
        this.gen = gen;
        this.scriitor = scriitor;
        this.numarPagini = numarPagini;
        this.an = an;
    }

    public Carte() {
        super();
    }

    @Override
    public String toString() {
        return  Carte{" +
                "gen='" + gen + '\'' +
                ", scriitor='" + scriitor + '\'' +
                ", numarPagini='" + numarPagini + '\'' +
                ", an=" + an +
                '}';
    }

    public String getgen() {
        return gen;
    }

    public void setgen() {
        this.gen = gen;
    }

    public String getscriitor() {
        return scriitor;
    }

    public void setscriitor() {
        this.scriitor = scriitor;
    }

    public String getnumarPagini() {
        return numarPagini;
    }

    public void setnumarPagini() {
        this.numarPagini = numarPagini;
    }

    public int getAn() {
        return an;
    }

    public void setAn() {
        this.an = an;
    }
}
