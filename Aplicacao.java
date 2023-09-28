import java.util.Date;

public class Aplicacao {

    public static void main(String[] args) {
        Lista list = new Lista(null,null);
        for(int i=7;i>=0;i--)
            list.insertList(i);
        list.showList();
        long tempoInicial = System.currentTimeMillis();
        //list.quickSortPivo();
        long tempoFinal = System.currentTimeMillis();
        long tempoExecucao = tempoFinal - tempoInicial;
        System.out.println("Tempo de execução: " + tempoExecucao + " milissegundos");
        list.showList();
    }
}

//bubble e bucket