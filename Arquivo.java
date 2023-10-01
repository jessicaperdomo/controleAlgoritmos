import java.io.IOException;
import java.io.RandomAccessFile;
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
}
