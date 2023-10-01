public class Lista {
    Node start,end;

    public Lista(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public Lista() {
        this.start = null;
        this.end = null;
    }

    public Node getStart() {
        return start;
    }

    public void insertList(int value){
        Node novo = new Node(value,null,null);
        if(start==null){
            start = end = novo;
        }
        else{
            novo.setAnt(end);
            end.setProx(novo);
            end = novo;
        }
    }

    public Node getIndex(int n){
        int i=0;
        Node aux = start;
        while(i!=n && aux!=null){
            i++;
            aux = aux.getProx();
        }
        return aux;
    }

    public void showList(){
        Node aux = start;
        while(aux != null){
            System.out.print(aux.getValue() + " ");
            aux = aux.getProx();
        }
        System.out.println();
    }

    public Node getNode(Node ini, int TL){

        for(int i=0; i<TL && ini != null; i++)
            ini = ini.getProx();

        return ini;
    }

    public Node getNodeAnt(Node ini, int contador){
        for(int i=0; i<contador; i++)
            ini = ini.getAnt();

        return ini;
    }

    public int getTL(Node ini){
        int res = 0;

        while(ini != null){
            res++;
            ini = ini.getProx();
        }

        return res;
    }

    public void inicializaLista(int i, int TL, Lista lista){
        while(i<TL)
            lista.insertList(getNode(start,i++).getValue());
    }

    public int buscaMaior(){
        int maior = 0;

        for(Node i = start; i != end.getProx(); i = i.getProx()) {
            if(i.getValue() > maior){
                maior = i.getValue();
            }
        }
        return maior;
    }

    public Node getMeio(Node pIni, int TL){

        for(int i = 0; i < TL; i++)
            pIni = pIni.getProx();

        return pIni;
    }

    public void insertionSort(){
        Node aux = start,aux2,aux3;
        int value;
        aux = aux.getProx();
        while (aux != null){
            aux2 = aux;
            value = aux.getValue();
            while(aux2 != start && aux2.getAnt().getValue() > value){
                aux2.setValue(aux2.getAnt().getValue());
                aux2 = aux2.getAnt();
            }
            aux2.setValue(value);
            aux = aux.getProx();
        }
    }

    public Node buscaBinariaInsercaoBinaria(int info, int TL){
        int ini = 0;
        int fim = TL-1;
        int meio = fim / 2;

        Node pIni = start;
        Node pMeio = getMeio(pIni, meio);

        while(ini < fim && pMeio.getValue() != info){

            if(info > pMeio.getValue()){
                ini = meio + 1;
                pIni = pMeio.getProx();
            }
            else
                fim = meio - 1;

            meio = (ini+fim)/2;
            pMeio = getMeio(pIni, meio-ini);
        }

        if(info > pMeio.getValue())
            return pMeio.getProx();
        return pMeio;
    }

    public void insercaoBinaria(){
        int aux, TL = 1;

        Node pos, i = start.getProx();
        while(i != end.getProx()){

            aux = i.getValue();
            pos = buscaBinariaInsercaoBinaria(aux, TL++);

            for(Node j = i; j != pos.getAnt() && j.getAnt() != null; j = j.getAnt())
                j.setValue(j.getAnt().getValue());

            pos.setValue(aux);

            i = i.getProx();
        }
    }

    public void selectionSort() {
        Node aux = start;

        while (aux != null) {
            Node minNo = aux;
            Node temp = aux.getProx();

            while (temp != null) {
                if (temp.getValue() < minNo.getValue()) {
                    minNo = temp;
                }

                temp = temp.getProx();
            }

            int valor = aux.getValue();
            aux.setValue(minNo.getValue());
            minNo.setValue(valor);

            aux = aux.getProx();
        }
    }

    public void heapSort(){
        int pai, TL2=0, FE, FD, aux;
        Node aux2;
        Node noPai, noTL2 = end, noFE, noFD, noMaiorF;

        aux2 = start;
        while(aux2 != null){
            TL2++;
            aux2 = aux2.getProx();
        }

        while(TL2 > 0){
            pai = (TL2/2)-1;
            noPai = getNode(start, pai);

            while(pai >= 0){
                FE = (pai*2) + 1;
                FD = FE + 1;

                noFE = getNode(start, FE);
                noFD = getNode(start, FD);

                noMaiorF = noFE;
                if(FD < TL2 && noFD.getValue() > noFE.getValue())
                    noMaiorF = noFD;

                if(noPai.getValue() < noMaiorF.getValue()) {
                    aux = noPai.getValue();
                    noPai.setValue(noMaiorF.getValue());
                    noMaiorF.setValue(aux);
                }

                pai--;
                noPai = noPai.getAnt();
            }
            TL2--;

            aux = noTL2.getValue();
            noTL2.setValue(start.getValue());
            start.setValue(aux);

            noTL2 = noTL2.getAnt();
        }
    }

    public void shellSort() {
        int aux, aux2, k;
        Node noJ, noK;

        int TL = getTL(start);

        for (int dist = 4; dist > 0; dist /= 2) {

            for (int i = 0; i < dist; i++) {

                for (int j = 0; j + dist < TL; j += dist) {
                    noJ = getNode(start, j);

                    if (noJ.getValue() > getNode(start, j + dist).getValue()) {
                        aux = getNode(start, j + dist).getValue();
                        getNode(start, j + dist).setValue(noJ.getValue());

                        noJ.setValue(aux);
                        noK = noJ;
                        aux2 = noK.getValue();

                        k = j;
                        while (k - dist >= 0 && aux2 < getNodeAnt(noK, dist).getValue()) {
                            aux = getNodeAnt(noK, dist).getValue();
                            getNodeAnt(noK, dist).setValue(noK.getValue());

                            noK.setValue(aux);
                            noK = noK.getAnt();

                            k -= dist;
                        }
                    }
                }
            }
        }
    }

    public void countingSortForRadix(int d) {
        int limit = 10, TL = getTL(start);
        int[] count = new int[limit];
        Node[] output = new Node[TL];

        for (int i = 0; i < TL; i++) {
            count[i] = 0;
        }

        for (Node i = start; i != null; i = i.getProx()) {
            count[(i.getValue() / d) % limit]++;
        }

        for (int i = 1; i < limit; i++) {
            count[i] += count[i - 1];
        }

        for (Node i = end; i != null; i = i.getAnt()) {
            output[count[(i.getValue() / d) % limit] - 1] = i;
            count[(i.getValue() / d) % limit]--;
        }

        Node temp = output[0];
        start = temp;

        for (int i = 1; i < TL; i++) {
            temp.setProx(output[i]);
            output[i].setAnt(temp);
            temp = temp.getProx();
        }

        end = output[TL - 1];
        end.setProx(null);
    }

    public void radixSort() {
        int maior = buscaMaior();

        for (int i = 1; maior / i > 0; i *= 10) {
            countingSortForRadix(i);
        }
    }

    public void mergeTim(int ini, int meio, int fim){
        int i,j,k;
        int n1 = meio - ini + 1;
        int n2 = fim - meio;

        Lista lista = new Lista();
        Lista lista2 = new Lista();

        inicializaLista(0, n1, lista);
        inicializaLista(0, n2, lista2);

        for(i = 0; i < n1; i++)
            lista.getNode(lista.start, i).setValue(getNode(start, ini+i).getValue());

        for(j = 0; j < n2; j++)
            lista2.getNode(lista2.start, j).setValue(getNode(start, meio + 1 + j).getValue());

        i = j = 0;
        k = ini;
        while(i < n1 && j < n2){
            if(lista.getNode(lista.start, i).getValue() <= lista2.getNode(lista2.start, j).getValue())
                getNode(start, k).setValue(lista.getNode(lista.start, i++).getValue());
            else
                getNode(start, k).setValue(lista2.getNode(lista2.start, j++).getValue());
            k++;
        }

        while(i<n1)
            getNode(start, k++).setValue(lista.getNode(lista.start, i++).getValue());
        while(j<n2)
            getNode(start, k++).setValue(lista2.getNode(lista2.start, j++).getValue());
    }

    public void insercaoDiretaTim(Node ini, Node fim){
        int temp;
        Node j, aux = null;

        for(Node i = ini.getProx(); i != fim.getProx(); i = i.getProx()){
            temp = i.getValue();
            j = i.getAnt();

            while(j != start && temp <= j.getValue()){
                j.getProx().setValue(j.getValue());
                j = j.getAnt();
            }

            if(temp <= j.getValue()){
                j.getProx().setValue(j.getValue());
                j.setValue(temp);
            }
            else
                j.getProx().setValue(temp);
        }
    }

    public void timSort(){
        int divisor = 32, TL = getTL(start);

        for(int i = 0; i<TL; i+=divisor)
            insercaoDiretaTim(getNode(start, i), getNode(start, Math.min(i+divisor-1, (TL-1))));

        for(int n = divisor; n < TL; n = 2*n){

            for(int ini = 0; ini < TL; ini += 2*n){

                int meio = ini + n - 1;
                int fim = Math.min(ini + 2 * n - 1, TL-1);

                if(meio < fim)
                    mergeTim(ini, meio, fim);
            }
        }
    }

    public void mergeSort2(){
        Lista lista = new Lista();
        int TL = getTL(start);

        inicializaLista(0,TL,lista);
        merge2(0,TL-1,lista);
    }

    public void merge2(int esq, int dir, Lista lista){
        if(esq<dir){

            int meio = (esq+dir)/2;

            merge2(esq, meio, lista);
            merge2(meio+1, dir, lista);

            fusao2(esq, meio, meio+1, dir, lista);
        }
    }

    public void fusao2(int ini1, int fim1, int ini2, int fim2, Lista lista){
        int i = ini1, j = ini2, k = 0;

        while(i<=fim1 && j<=fim2){

            if(getNode(start,i).getValue() < getNode(start, j).getValue())
                lista.getNode(lista.start, k++).setValue(getNode(start, i++).getValue());
            else
                lista.getNode(lista.start, k++).setValue(getNode(start, j++).getValue());

        }

        while(i<=fim1)
            lista.getNode(lista.start, k++).setValue(getNode(start, i++).getValue());

        while(j<=fim2)
            lista.getNode(lista.start, k++).setValue(getNode(start, j++).getValue());

        i=0;
        while (i < k)
            getNode(start, ini1 + i).setValue(lista.getNode(lista.start, i++).getValue());
    }

    public void quickSort(){
        int TL = getTL(start);

        quickSortSP(0, TL-1);
    }

    private void quickSortSP(int ini, int fim){
        int i = ini, j = fim;
        int aux;

        Node pont1 = getNode(start, ini), pont2 = getNode(start, fim);

        while(i < j){
            while(i < j && pont1.getValue() <= pont2.getValue()){
                i++;
                pont1 = pont1.getProx();
            }

            aux = pont2.getValue();
            pont2.setValue(pont1.getValue());
            pont1.setValue(aux);

            while(j > i && pont2.getValue() >= pont1.getValue()){
                j--;
                pont2 = pont2.getAnt();
            }

            aux = pont2.getValue();
            pont2.setValue(pont1.getValue());
            pont1.setValue(aux);
        }

        if(ini < i-1)
            quickSortSP(ini, i-1);

        if(j+1 < fim)
            quickSortSP(j+1, fim);
    }

    public void bubbleSort(){
        Node aux = start,aux2;
        int valor;
        while(aux != null){
            aux2 = start;
            while(aux2.getProx() != null){
                if(aux2.getValue() > aux2.getProx().getValue()){
                    valor = aux2.getValue();
                    aux2.setValue(aux2.getProx().getValue());
                    aux2.getProx().setValue(valor);
                }
                aux2 = aux2.getProx();
            }
            aux = aux.getProx();
        }
    }

    public void coutingSort(){
        if(start != null){
            int maxValue = start.getValue();
            Node aux = start.getProx();
            while(aux != null){
                if(maxValue < aux.getValue()){
                    maxValue = aux.getValue();
                }
                aux = aux.getProx();
            }
            int cont[] = new int[maxValue+1];
            aux = start;
            while(aux != null){
                cont[aux.getValue()]++;
                aux = aux.getProx();
            }
            aux = start;
            for(int a = 0;a <= maxValue;a++){
                while(cont[a] != 0){
                    aux.setValue(a);
                    aux = aux.getProx();
                    cont[a]--;
                }
            }
        }
    }

    public void shakeSort(){
        boolean swapped;
        Node bottom = start,top = end,aux,aux2;
        int value;
        do{
            swapped = false;
            aux = bottom;
            while(aux != top){
                if(aux.getValue() > aux.getProx().getValue()){
                    value = aux.getValue();
                    aux.setValue(aux.getProx().getValue());
                    aux.getProx().setValue(value);
                    swapped = true;
                }
                aux = aux.getProx();
            }
            top = top.getAnt();
            if(swapped){
                swapped = false;
                aux = top;
                while(aux != bottom){
                    if(aux.getValue() < aux.getAnt().getValue()){
                        value = aux.getValue();
                        aux.setValue(aux.getAnt().getValue());
                        aux.getAnt().setValue(value);
                        swapped = true;
                    }
                    aux = aux.getAnt();
                }
                bottom = bottom.getProx();
            }
        }while(swapped);
    }

    public void gnomeSort(){
        Node aux = start;
        int value;
        while(aux != null){
            if(aux==start || aux.getAnt().getValue()<= aux.getValue()){
                aux = aux.getProx();
            }
            else{
                value = aux.getAnt().getValue();
                aux.getAnt().setValue(aux.getValue());
                aux.setValue(value);
                aux = aux.getAnt();
            }
        }
    }

    public void bucketSort(int n){
        if(start != null){
            int maxValue = start.getValue();
            Node aux = start.getProx(),aux2;
            while(aux != null){
                if(maxValue < aux.getValue()){
                    maxValue = aux.getValue();
                }
                aux = aux.getProx();
            }
            Lista balde[] = new Lista[n];
            for (int i = 0; i < n; i++) {
                balde[i] = new Lista();
            }
            aux = start;
            while(aux != null){
                int pos = (int) ((aux.getValue()*1.0)/maxValue)*(n-1);
                balde[pos].insertList(aux.getValue());
                aux = aux.getProx();
            }
            for(int a=0;a < n; a++){
                balde[a].bubbleSort();
            }
            aux = start;
            for(int a=0;a < n; a++){
                aux2 = balde[a].getStart();
                while(aux2!=null){
                    aux.setValue(aux2.getValue());
                    aux = aux.getProx();
                    aux2 = aux2.getProx();
                }
            }
        }
    }

    public void combSort(){
        int gap = 0,tl;
        boolean trocas = true;
        Lista ajd = new Lista();
        Node aux = start,aux2;
        while(aux != null){
            gap++;
            aux = aux.getProx();
        }
        tl = gap;
        while (gap > 1 || trocas) {
            if (gap > 1){
                gap = (int) (gap / 1.3);
            }
            int i = 0;
            trocas = false;
            aux = getIndex(0);
            aux2 = getIndex(gap);
            while (i + gap < tl) {
                if(aux.getValue() > aux2.getValue()){
                    int swap = aux.getValue();
                    aux.setValue(aux2.getValue());
                    aux2.setValue(swap);
                    trocas = true;
                }
                aux = aux.getProx();
                aux2 = aux2.getProx();
                i++;
            }
        }
    }

    private void fusao(Lista list1, Lista list2, int seq,int TL) {
        int i=0, j=0, k=0, tam=seq;
        Node aux = start, aux1 = list1.getStart(), aux2 = list2.getStart() ;
        while(k < TL) {
            while (i < seq && j < seq) {
                if (aux1.getValue() < aux2.getValue()) {
                    aux.setValue(aux1.getValue());
                    aux = aux.getProx();
                    aux1 = aux1.getProx();
                    k++; i++;
                } else {
                    aux.setValue(aux2.getValue());
                    aux = aux.getProx();
                    aux2 = aux2.getProx();
                    k++; j++;
                }
            }

            while (i < seq) {
                aux.setValue(aux1.getValue());
                aux = aux.getProx();
                aux1 = aux1.getProx();
                k++; i++;
            }
            while (j < seq) {
                aux.setValue(aux2.getValue());
                aux = aux.getProx();
                aux2 = aux2.getProx();
                k++; j++;
            }
            seq = seq + tam;
        }
    }

    private Lista particao(int i, int TL) {
        Lista lista = new Lista();
        Node aux=getIndex(i);
        while(TL>0){
            lista.insertList(aux.getValue());
            TL--;
            aux = aux.getProx();
        }
        return lista;
    }
    public void mergeSort1(){
        Lista list1,list2;
        Node aux=start;
        int seq=1,TL=0;
        while(aux!=null){
            TL++;
            aux = aux.getProx();
        }
        while(seq < TL){
            list1 = particao(0,TL/2);
            list2 = particao(TL/2,TL/2);
            fusao(list1, list2, seq,TL);
            seq = seq*2;
        }
    }

    public int particaoQuick(int esq,int dir){
        int x = getIndex((esq+dir)/2).getValue();
        int i = esq,j = dir,valor;
        Node aux = getIndex(i), aux2 = getIndex(j);
        do{
            while(x > aux.getValue()){
                i++;
                aux = aux.getProx();
            }
            while(x < aux2.getValue()){
                j--;
                aux2 = aux2.getAnt();
            }
            if(i<=j){
                valor = aux.getValue();
                aux.setValue(aux2.getValue());
                aux2.setValue(valor);
                i++;
                j--;
                aux = aux.getProx();
                aux2 = aux2.getAnt();
            }
        }while(i<=j);
        return i;
    }

    public void ordena(int esq,int dir){
        if(esq<dir){
            int m = particaoQuick(esq, dir);
            ordena(esq,m-1);
            ordena(m+1,dir);
        }
    }

    public void quickSortPivo(){
        int TL=0;
        Node aux = start;
        while(aux!=null){
            TL++;
            aux = aux.getProx();
        }
        ordena(0,TL-1);
    }

}
