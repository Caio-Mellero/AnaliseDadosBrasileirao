import java.util.Comparator;

public class ComparadorPontos implements Comparator<Entidade> {
    private int pesoOuro;
    private int pesoPrata;
    private int pesoBronze;

    // Construtor para receber os pesos (9, 4, 1)
    public ComparadorPontos(int pesoOuro, int pesoPrata, int pesoBronze) {
        this.pesoOuro = pesoOuro;
        this.pesoPrata = pesoPrata;
        this.pesoBronze = pesoBronze;
    }

    private int calculaPontos(Entidade e) {
        return (e.getOuro() * pesoOuro) + (e.getPrata() * pesoPrata) + (e.getBronze() * pesoBronze);
    }

    @Override
    public int compare(Entidade e1, Entidade e2) {
        int pontos1 = calculaPontos(e1);
        int pontos2 = calculaPontos(e2);
        return Integer.compare(pontos2, pontos1); // Decrescente
    }
}