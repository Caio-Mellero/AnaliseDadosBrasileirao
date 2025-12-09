import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Map<String, Clube> mapaClubes = new HashMap<>();
        Map<String, Estado> mapaEstados = new HashMap<>();

        Map<String, String> clubeParaEstado = lerArquivoDeEstados("dados/estados.txt");

        for (int ano = 2003; ano <= 2023; ano++) {
            File arquivo = new File("dados/" + ano + ".txt");

            if (arquivo.exists()) {
                Scanner sc = new Scanner(arquivo, StandardCharsets.UTF_8);

                while (sc.hasNextLine()) {
                    String linha = sc.nextLine();
                    String[] dados = linha.split(",");

                    if (dados.length < 8) continue;

                    try {
                        int posicao = Integer.parseInt(dados[0]);
                        String nomeClube = dados[1];
                        int gols = Integer.parseInt(dados[7]);

                        // -- Atualiza CLUBE --
                        if (!mapaClubes.containsKey(nomeClube)) {
                            mapaClubes.put(nomeClube, new Clube(nomeClube));
                        }
                        Clube clube = mapaClubes.get(nomeClube);
                        clube.adicionaGols(gols);

                        if (posicao == 1) clube.adicionaOuro();
                        if (posicao == 2) clube.adicionaPrata();
                        if (posicao == 3) clube.adicionaBronze();

                        // -- Atualiza ESTADO (Exercício 6) --
                        String nomeEstado = clubeParaEstado.get(nomeClube);
                        if (nomeEstado != null) {
                            if (!mapaEstados.containsKey(nomeEstado)) {
                                mapaEstados.put(nomeEstado, new Estado(nomeEstado));
                            }
                            Estado estado = mapaEstados.get(nomeEstado);
                            estado.adicionaGols(gols);

                            if (posicao == 1) estado.adicionaOuro();
                            if (posicao == 2) estado.adicionaPrata();
                            if (posicao == 3) estado.adicionaBronze();
                        }

                    } catch (NumberFormatException e) {
                        // Ignora linhas de cabeçalho (ex: "Posicao,Clube...")
                        continue;
                    }
                }
                sc.close();
            } else {
                System.out.println("ERRO! O Java procurou aqui e não achou: " + arquivo.getAbsolutePath());
            }
        }

        List<Clube> listaClubes = new ArrayList<>(mapaClubes.values());
        List<Estado> listaEstados = new ArrayList<>(mapaEstados.values());

        // --- IMPRESSÕES DOS RESULTADOS ---

        System.out.println("\n--- EXERCÍCIO 2: Ranking de Gols (Clubes) ---");
        imprimirRanking(listaClubes, new ComparadorGols(), 5); // Top 5

        System.out.println("\n--- EXERCÍCIO 4: Ranking Olímpico (Clubes) ---");
        imprimirRanking(listaClubes, new ComparadorOlimpico(), 5);

        System.out.println("\n--- EXERCÍCIO 5: Ranking por Pontos (9, 4, 1) (Clubes) ---");
        imprimirRanking(listaClubes, new ComparadorPontos(9, 4, 1), 5);

        System.out.println("\n--- EXERCÍCIO 6: Ranking de Gols (Estados) ---");
        imprimirRanking(listaEstados, new ComparadorGols(), 5);

        System.out.println("\n--- EXERCÍCIO 6: Ranking Olímpico (Estados) ---");
        imprimirRanking(listaEstados, new ComparadorOlimpico(), 5);
    }

    public static <T extends Entidade> void imprimirRanking(List<T> lista, Comparator<? super T> comparador, int top) {        lista.sort(comparador);
        for (int i = 0; i < Math.min(top, lista.size()); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    private static Map<String, String> lerArquivoDeEstados(String caminhoArquivo) throws IOException {
        Map<String, String> mapa = new HashMap<>();
        File file = new File(caminhoArquivo);

        if (!file.exists()) return mapa;

        Scanner sc = new Scanner(file, StandardCharsets.UTF_8);
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] partes = linha.split(",");
            if (partes.length >= 2) {
                String clube = partes[0].trim();
                String estado = partes[1].trim();
                mapa.put(clube, estado);
            }
        }
        sc.close();
        return mapa;
    }
}