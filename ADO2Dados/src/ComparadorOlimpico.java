import java.util.Comparator;

public class ComparadorOlimpico implements Comparator<Entidade> {
    @Override
    public int compare(Entidade e1, Entidade e2) {
        // Compara Ouro
        int diffOuro = e2.getOuro() - e1.getOuro();
        if (diffOuro != 0) return diffOuro;

        // Compara Prata
        int diffPrata = e2.getPrata() - e1.getPrata();
        if (diffPrata != 0) return diffPrata;

        // Compara Bronze
        int diffBronze = e2.getBronze() - e1.getBronze();
        if (diffBronze != 0) return diffBronze;

        return 0;
    }
}