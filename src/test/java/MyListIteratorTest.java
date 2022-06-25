import models.MyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyListIteratorTest {

    @Test
    public void testHasNextIfEmptyList() {

        Integer[] arr = new Integer[]{};
        MyList<Integer> list = new MyList<>(arr);

        Iterator<Integer> it = list.iterator();
        Assertions.assertFalse(it.hasNext());
    }

    @Test
    public void testNextIfEmptyList() {

        Integer[] arr = new Integer[]{};
        MyList<Integer> list = new MyList<>(arr);
        Iterator<Integer> it = list.iterator();

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, it::next);

    }

    @Test
    public void testHasNextIfOneElemInList() {

        Integer[] arr = new Integer[]{1};
        MyList<Integer> list = new MyList<>(arr);
        Iterator<Integer> it = list.iterator();

        Assertions.assertTrue(it.hasNext());
        Assertions.assertTrue(it.hasNext());
    }

    @Test
    public void testNextAndHasNextWithOneElem() {

        Integer[] arr = new Integer[]{1};
        MyList<Integer> list = new MyList<>(arr);
        Iterator<Integer> it = list.iterator();

        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(1, it.next());
        Assertions.assertFalse(it.hasNext());
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, it::next);

    }

    @Test
    public void testRemoveInEmptyList() {

        Integer[] arr = new Integer[]{};
        MyList<Integer> list = new MyList<>(arr);
        Iterator<Integer> it = list.iterator();

        Assertions.assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test
    public void testIterateAllElements() {

        Integer[] mas = new Integer[]{1, 2, 3, 4, 5};

        List<Integer> list = Arrays.asList(mas);
        MyList<Integer> myList = new MyList<>(mas);


        Iterator<Integer> iterator = list.iterator();
        Iterator<Integer> myIterator = myList.iterator();

        while (myIterator.hasNext()) {
            Assertions.assertEquals(iterator.next(), myIterator.next());
        }

    }

}
