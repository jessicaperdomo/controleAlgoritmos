public class Node {
    private int value;
    private Node prox,ant;

    public Node(int value, Node prox, Node ant) {
        this.value = value;
        this.prox = prox;
        this.ant = ant;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getProx() {
        return prox;
    }

    public void setProx(Node prox) {
        this.prox = prox;
    }

    public Node getAnt() {
        return ant;
    }

    public void setAnt(Node ant) {
        this.ant = ant;
    }
}
