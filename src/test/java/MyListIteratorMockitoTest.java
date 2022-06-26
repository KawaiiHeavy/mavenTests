import models.MyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Iterator;

import static org.mockito.Mockito.*;

public class MyListIteratorMockitoTest {

    @Test
    public void testHasNextIfEmptyList() {

        Iterator it = mock(Iterator.class);
        when(it.hasNext()).thenReturn(false);

        Assertions.assertFalse(it.hasNext());
    }

    @Test
    public void testNextIfEmptyList() {

        Iterator it = mock(Iterator.class);
        when(it.next()).thenThrow(ArrayIndexOutOfBoundsException.class);

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, it::next);
    }

    @Test
    public void testHasNextIfOneElemInList() {

        Iterator it = mock(Iterator.class);
        when(it.hasNext()).thenReturn(true);

        Assertions.assertTrue(it.hasNext());
        Assertions.assertTrue(it.hasNext());
    }

    @Test
    public void testNextAndHasNextWithOneElem() {

        Iterator it = mock(Iterator.class);

        when(it.hasNext()).thenReturn(true);
        when(it.next()).thenReturn(1);

        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(1, it.next());

        when(it.hasNext()).thenReturn(false);
        when(it.next()).thenThrow(ArrayIndexOutOfBoundsException.class);

        Assertions.assertFalse(it.hasNext());
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, it::next);

    }

    @Test
    public void testRemoveInEmptyList() {

        Iterator it = mock(Iterator.class);
        it.remove();

        Mockito.verify(it, times(1)).remove();
    }

    @Test
    public void testIterateAllElements() {

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        MyList<Integer> list = new MyList<>(arr);
        Iterator<Integer> myIterator = list.iterator();
        Iterator<Integer> spyIterator = Mockito.spy(myIterator);

        while (spyIterator.hasNext()) {
            spyIterator.next();
        }

        verify(spyIterator, times(arr.length)).next();
    }
}
