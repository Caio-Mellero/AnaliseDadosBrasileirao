public class Estado implements Entidade {
    private String nome; // Ex: SP, RJ
    private int gols = 0;
    private int ouro = 0;
    private int prata = 0;
    private int bronze = 0;

    public Estado(String nome) {
        this.nome = nome;
    }

    public void adicionaGols(int gols) {
        this.gols += gols;
    }

    public void adicionaOuro() { this.ouro++; }
    public void adicionaPrata() { this.prata++; }
    public void adicionaBronze() { this.bronze++; }

    @Override
    public String getNome() { return nome; }
    @Override
    public int getGols() { return gols; }
    @Override
    public int getOuro() { return ouro; }
    @Override
    public int getPrata() { return prata; }
    @Override
    public int getBronze() { return bronze; }

    @Override
    public String toString() {
        return "Estado: " + nome + " | Gols: " + gols + " | Títulos: " + ouro;
    }
}