import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

public class Arquivo {

    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public String getNomearquivo() {
        return nomearquivo;
    }

    public void setNomearquivo(String nomearquivo) {
        this.nomearquivo = nomearquivo;
    }

    public RandomAccessFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(RandomAccessFile arquivo) {
        this.arquivo = arquivo;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public void setMov(int mov) {
        this.mov = mov;
    }

    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
    }

    public Arquivo(String nomearquivo) {
        try {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e) {
        }
    }

    public void copiaArquivo(Arquivo aux1){
        Registro reg = new Registro();

        seekArq(0);
        aux1.truncate(0);
        aux1.seekArq(0);
        while(!eof()){
            reg.leDoArq(arquivo);
            reg.gravaNoArq(aux1.arquivo);
        }
    }

    public void truncate(long pos) {
        try {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc) {
        }
    }

    public boolean eof() {
        boolean retorno = false;

        try {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e) {
        }

        return (retorno);
    }

    public void seekArq(int pos) {
        try {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e) {
        }
    }

    public int fileSize() {
        try {
            return (int) arquivo.length() / Registro.length();
        } catch (IOException e) {
        }

        return 0;
    }

    public void initComp() {
        comp = 0;
    }

    public void initMov() {
        mov = 0;
    }

    public void exibirUmRegistro(int pos) {
        Registro aux = new Registro();

        seekArq(pos);

        System.out.println("Posicao " + pos);

        aux.leDoArq(arquivo);
        aux.exibirRegistro();
    }

    public void geraArquivoOrdenado() {
        truncate(0);

        for (int i = 0; i < 10; i++) {
            new Registro(i).gravaNoArq(arquivo);
        }
    }

    public void geraArquivoReverso() {
        truncate(0);

        for (int i = 10; i > 0; i--)
            new Registro(i).gravaNoArq(arquivo);
    }

    public void geraArquivoRandomico() {
        truncate(0);
        for (int i = 0; i < 10; i++)
            new Registro(new Random().nextInt(10100)).gravaNoArq(arquivo);

    }

    public void insercaoDireta() {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int j, aux;
        for(int i=1; i<fileSize(); i++) {

            seekArq(i);
            reg2.leDoArq(arquivo);
            seekArq(i-1);
            reg1.leDoArq(arquivo);

            j = i;
            comp++;
            while(j>0 && reg2.getCodigo() < reg1.getCodigo()) {
                mov++;

                seekArq(j);
                reg1.gravaNoArq(arquivo);

                j--;

                seekArq(j-1);
                reg1.leDoArq(arquivo);
            }

            seekArq(j);
            reg2.gravaNoArq(arquivo);
            mov++;
        }
    }

    public void bubbleSort(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int TL = fileSize();
        while(TL > 1){

            for(int i = 0; i<fileSize()-1; i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                comp++;

                if(reg1.getCodigo() > reg2.getCodigo()) {
                    mov+=2;

                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i+1);
                    reg1.gravaNoArq(arquivo);
                }
            }

            TL--;
        }
    }

    public void shakeSort(){
        int inicio = 0, fim = fileSize() - 1;

        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        while(inicio < fim){

            for(int i = 0; i < fileSize() - 1; i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                comp++;

                if(reg1.getCodigo() > reg2.getCodigo()) {
                    mov+=2;

                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i+1);
                    reg1.gravaNoArq(arquivo);
                }
            }

            fim--;

            for(int j=fileSize()-1; j>0; j--){
                seekArq(j-1);
                reg2.leDoArq(arquivo);
                reg1.leDoArq(arquivo);
                comp++;

                if(reg1.getCodigo() < reg2.getCodigo()) {
                    mov+=2;

                    seekArq(j-1);
                    reg1.gravaNoArq(arquivo);
                    seekArq(j);
                    reg2.gravaNoArq(arquivo);
                }
            }

            inicio++;
        }
    }

    public void quickSortComPivo(){
        quickSortComPivo(0, fileSize() - 1);
    }

    public void quickSortComPivo(int ini, int fim){
        int i = ini, j = fim, aux;

        Registro pivo = new Registro();
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        seekArq((i+fim)/2);

        pivo.leDoArq(arquivo);
        seekArq(i);
        reg1.leDoArq(arquivo);
        seekArq(j);
        reg2.leDoArq(arquivo);
        while(i<=j){
            comp++;

            while(reg1.getCodigo() < pivo.getCodigo()){
                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);
                comp++;
            }

            comp++;

            while(reg2.getCodigo() > pivo.getCodigo()) {
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
                comp++;
            }

            if(i<=j){
                mov+=2;

                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);

                i++;
                j--;

                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(j);
                reg2.leDoArq(arquivo);
            }
        }

        if(ini < j)
            quickSortComPivo(ini, j);

        if(i < fim)
            quickSortComPivo(i, fim);
    }

    public void mergeSort1(){
        merge1(0, 0, fileSize()-1);
    }

    public void merge1(int ini, int l, int r){

        Arquivo arquivo1 = new Arquivo("temp663.dat");
        Arquivo arquivo2 = new Arquivo("temp497.dat");

        int seq = 1;
        while(seq < fileSize()) {
            arquivo1.truncate(0);
            arquivo2.truncate(0);

            particao(arquivo1, arquivo2);
            fusao(arquivo1, arquivo2, seq);

            seq*=2;
        }
    }

    public void fusao(Arquivo arq1, Arquivo arq2, int seq){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int k = 0, aux = seq, i = 0, j = 0;
        while(k < fileSize()){

            while(i < seq && j < seq){

                arq1.seekArq(i);
                reg1.leDoArq(arq1.arquivo);
                arq2.seekArq(j);
                reg2.leDoArq(arq2.arquivo);

                comp++;
                if(reg1.getCodigo() < reg2.getCodigo()) {
                    seekArq(k++);
                    reg1.gravaNoArq(arquivo);
                    i++;
                }
                else{
                    seekArq(k++);
                    reg2.gravaNoArq(arquivo);
                    j++;
                }

                mov++;
            }

            while(i < seq){
                mov++;

                arq1.seekArq(i++);
                reg1.leDoArq(arq1.arquivo);
                seekArq(k++);
                reg1.gravaNoArq(arquivo);
            }

            while(j < seq){
                mov++;

                arq2.seekArq(j++);
                reg2.leDoArq(arq2.arquivo);
                seekArq(k++);
                reg2.gravaNoArq(arquivo);
            }

            seq+=aux;
        }
    }

    public void particao(Arquivo arq1, Arquivo arq2){
        Registro reg = new Registro();

        int tam = fileSize()/2;
        for(int i = 0; i<tam; i++){
            mov+=2;

            seekArq(i);
            reg.leDoArq(arquivo);
            arq1.seekArq(i);

            reg.gravaNoArq(arq1.arquivo);
            seekArq(i+tam);

            reg.leDoArq(arquivo);
            arq2.seekArq(i);

            reg.gravaNoArq(arq2.arquivo);
        }
    }

    public void countingSort(){
        int j;

        Arquivo temp = new Arquivo("temp.dat");

        Registro reg1 = new Registro();

        for(int i = 0; i < 101; i++){
            new Registro(0).gravaNoArq(temp.arquivo);
            mov++;
        }

        for(int i = 0; i < fileSize(); i++){
            mov++;

            seekArq(i);
            reg1.leDoArq(arquivo);
            temp.seekArq(reg1.getCodigo());
            j = reg1.getCodigo();
            reg1.leDoArq(temp.arquivo);
            reg1 = new Registro(reg1.getCodigo()+1);
            temp.seekArq(j);
            reg1.gravaNoArq(temp.arquivo);
        }

        int i = 0;
        for(j = 0; j < 101; j++){
            temp.seekArq(j);
            reg1.leDoArq(temp.arquivo);

            for(int k = reg1.getCodigo(); k > 0; k--){
                mov++;

                seekArq(i);
                reg1 = new Registro(j);
                reg1.gravaNoArq(arquivo);
                seekArq(j);
                reg1.leDoArq(arquivo);

                i++;
            }
        }
    }

    public int buscaMaior(){
        seekArq(0);
        int maior = 0;

        Registro reg = new Registro();
        while(!eof()){
            reg.leDoArq(arquivo);

            comp++;

            if(maior < reg.getCodigo())
                maior = reg.getCodigo();
        }

        return maior;
    }

    public void bucketSort() throws IOException {

        if(fileSize() > 0) {

            Registro reg1 = new Registro();

            int indice = 0;
            ArrayList<Arquivo> arquivos = new ArrayList();
            int maior = buscaMaior();
            seekArq(0);

            for(int j=0; indice<maior; j++){
                Arquivo arq = new Arquivo("temp" + j + ".dat");
                for(int i = 0; i<fileSize(); i++){

                    seekArq(i);
                    reg1.leDoArq(arquivo);
                    comp++;
                    if(reg1.getCodigo() >= indice && reg1.getCodigo() < indice+5){
                        reg1.gravaNoArq(arq.arquivo);
                        mov++;
                    }
                }

                indice+=5;

                if(arq.fileSize() > 0){
                    arquivos.add(arq);
                }
                //arq.arquivo.close();
            }

            for(Arquivo i : arquivos)
                i.insercaoDireta();

            truncate(0);
            seekArq(0);

            for(Arquivo i : arquivos){

                for(int j = 0; j<i.fileSize(); j++){
                    mov++;

                    i.seekArq(j);
                    reg1.leDoArq(i.arquivo);
                    reg1.gravaNoArq(arquivo);
                    reg1.leDoArq(arquivo);
                }
            }
        }
    }

    public int getNextGap(int gap){
        gap = (gap*10)/13;

        if(gap < 1)
            return 1;
        return gap;
    }

    public void combSort(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int TL = fileSize();
        int gap = TL;
        boolean trocou = true;

        while(gap != 1 || trocou){

            gap = getNextGap(gap);
            trocou = false;

            for(int i=0; i<TL-gap; i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(i+gap);
                reg2.leDoArq(arquivo);

                comp++;

                if(reg1.getCodigo() > reg2.getCodigo()){

                    seekArq(i+gap);
                    reg1.gravaNoArq(arquivo);
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    trocou = true;

                    mov+=2;
                }
            }
        }
    }

    public void gnomeSort(){
        int i = 0;

        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        while(i < fileSize()){

            if(i == 0)
                i++;

            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(i-1);
            reg2.leDoArq(arquivo);

            comp++;

            if(reg1.getCodigo() >= reg2.getCodigo())
                i++;
            else{
                seekArq(i-1);
                reg1.gravaNoArq(arquivo);
                seekArq(i);
                reg2.gravaNoArq(arquivo);

                i--;
                mov+=2;
            }
        }
    }

}
