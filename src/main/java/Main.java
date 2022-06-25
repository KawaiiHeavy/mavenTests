import models.MyList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        Integer[] mas = new Integer[]{1, 2, 3, 4, 5};
        MyList<Integer> myList = new MyList<>(mas);
        Iterator<Integer> iterator = myList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
