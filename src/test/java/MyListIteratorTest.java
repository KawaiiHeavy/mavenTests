import models.MyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

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

    static class IntArrayConverter implements ArgumentConverter {

        @Override
        public Object convert(Object source, ParameterContext context)
                throws ArgumentConversionException {
            if (!(source instanceof String)) {
                throw new IllegalArgumentException(
                        "The argument should be a string: " + source);
            }
            try {
                return Arrays.stream(((String) source).split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Failed to convert", e);
            }
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,1,1,1", "1,2,3,4,5"}, delimiterString = ";")
    public void testIterateAllElements(@ConvertWith(IntArrayConverter.class) Integer[] input) {

        List<Integer> list = Arrays.asList(input);
        MyList<Integer> myList = new MyList<>(input);

        Iterator<Integer> iterator = list.iterator();
        Iterator<Integer> myIterator = myList.iterator();

        while (myIterator.hasNext()) {
            Assertions.assertEquals(iterator.next(), myIterator.next());
        }
    }
}
