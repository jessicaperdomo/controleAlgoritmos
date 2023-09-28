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

}
