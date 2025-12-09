import java.util.Comparator;

public class ComparadorGols implements Comparator<Entidade> {
    @Override
    public int compare(Entidade e1, Entidade e2) {
        return Integer.compare(e2.getGols(), e1.getGols()); // Decrescente
    }
}