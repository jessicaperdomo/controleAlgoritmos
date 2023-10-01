import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Principal {

    public void geraTabela() throws IOException{

        long tini, tfim;
        FileWriter arq = iniciaTabela();
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;

        // Gera arquivos

        Arquivo arqOrd = new Arquivo("arquivoOrdenado.dat");
        Arquivo arqRand = new Arquivo("arquivoRandomico.dat");
        Arquivo arqRev = new Arquivo("arquivoReverso.dat");

        Arquivo auxRand = new Arquivo("arquivoRandomicoAux.dat");
        Arquivo auxRev = new Arquivo("arquivoReversoAux.dat");

        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();

        /* ---------------------------------------- Inserção direta -------------------------------------------- */

        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.insercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.insercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        // Randômica
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.insercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        gravaLinhaTabela(arq, "Inserção Direta", compOrd, compRev, compRand, movOrd, movRev, movRand, calculaCompInsDirOrd(arqOrd.fileSize()), calculaCompInsDirRev(auxRev.fileSize()),
                calculaCompInsDirRand(auxRand.fileSize()), calculaMovInsDirOrd(arqOrd.fileSize()), calculaMovInsDirRev(auxRev.fileSize()), calculaMovInsDirRand(auxRand.fileSize()),
                ttotalOrd, ttotalRev, ttotalRand);



        arq.close();
        exibirTabela();
    }

    // Calcula comparações

    private double calculaCompInsDirOrd(int TL){
        return TL-1;
    }

    private double calculaCompInsDirRev(int TL){
        return ((Math.pow(TL, 2)) + (TL-2)) / 4;
    }

    private double calculaCompInsDirRand(int TL){
        return ((Math.pow(TL, 2)) + (TL-4)) / 4;
    }

    // Calcula movimentações

    private int calculaMovInsDirOrd(int TL){
        return 3 * (TL-1);
    }

    private double calculaMovInsDirRev(int TL){
        return ((Math.pow(TL, 2)) + (9 * TL) - 10) / 4;
    }

    private double calculaMovInsDirRand(int TL){
        return ((Math.pow(TL, 2)) + (3 * TL) - 4) / 2;
    }

    private FileWriter iniciaTabela() throws IOException{
        FileWriter arq = new FileWriter("Tabela.txt");

        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("__________________________________________________________________________________________________________________________________________________________________________________________________________________________%n");
        gravarArq.printf("|Métodos Ordenação | \t\t\tArquivo Ordenado\t\t\t | \t\t\tArquivo em Ordem Reversa\t\t\t | \t\t\tArquivo Randômico\t\t\t |%n");
        gravarArq.printf("_________________________________________________________________________________________________________________________________________________________________________________________________________________________|%n");
        gravarArq.printf("\t\t | Comp. Prog. *| Comp. Equa. #| Mov. Prog. +| Mov. Equa. -| Tempo | Comp. Prog. *| Comp. Equa. #| Mov. Prog. +| Mov. Equa. -| Tempo | Comp. Prog. *| Comp. Equa. #| Mov. Prog. +| Mov. Equa. -| Tempo |%n");
        gravarArq.printf("_________________________________________________________________________________________________________________________________________________________________________________________________________________________|%n");

        return arq;
    }

    private void gravaLinhaTabela(FileWriter arq, String metodo, int compOrd, int compRev, int compRand, int movOrd, int movRev, int movRand, double compEquaOrd, double compEquaRev,
                                  double compEquaRand, double movEquaOrd, double movEquaRev, double movEquaRand,int ttotalOrd, int ttotalRev, int ttotalRand) throws IOException{
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("" + metodo + "    |\t" + String.format("%-6.6s", compOrd) + "\t  |\t" + String.format("%-6.6s", compEquaOrd) + "\t |      " + String.format("%-6.6s", movOrd) + " |      " + String.format("%-2.6s", movEquaOrd) + " |\t" + String.format("%-2.6s", ttotalOrd) + "   |\t    " + String.format("%-6.6s", compRev) + "  |\t  " + String.format("%-6.6s", compEquaRev) + "   |\t " + String.format("%-6.6s", movRev) + "  | \t" + String.format("%-6.6s", movEquaRev) + " |  " + String.format("%-2.6s", ttotalRev) + "   |     " + String.format("%-6.6s", compRand) + "   |\t  " + String.format("%-6.6s", compEquaRand) + "     |\t    " + String.format("%-6.6s", movRand) + " |\t " + String.format("%-6.6s", movEquaRand) + "  |  " + String.format("%-4.6s", ttotalRand) + " |%n");
        gravarArq.printf("_________________________________________________________________________________________________________________________________________________________________________________________________________________________|%n");
    }

    private void exibirTabela() throws FileNotFoundException, IOException{

        FileInputStream stream = new FileInputStream("Tabela.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        while(linha != null) {
            System.out.println(linha);
            linha = br.readLine();
        }
    }

}
