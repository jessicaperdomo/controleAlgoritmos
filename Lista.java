public class Lista {
    Node start,end;

    public Lista(Node start, Node end) {
        this.start = start;
        this.end = end;
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

    public void showList(){
        Node aux = start;
        while(aux != null){
            System.out.print(aux.getValue() + " ");
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
        int value;
        while(aux != null){
            aux2 = aux;
            while(aux2.getProx() != null){
                if(aux2.getValue() > aux2.getProx().getValue()){
                    value = aux2.getValue();
                    aux2.setValue(aux2.getProx().getValue());
                    aux2.getProx().setValue(value);
                }
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
                    if(aux.getValue() > aux.getAnt().getValue()){
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
}
