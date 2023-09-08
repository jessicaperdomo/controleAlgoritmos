public class Aplicacao {

    public static void main(String[] args) {
        Lista list = new Lista(null,null);
        list.insertList(1);
        list.insertList(4);
        list.insertList(3);
        list.insertList(2);
        list.insertList(5);
        list.showList();
        list.bucketSort(5);
        list.showList();
    }
}
