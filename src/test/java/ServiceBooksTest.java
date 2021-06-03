import com.alpha.model.ServiceBooks;
import com.alpha.model.SourceBooks;
import com.alpha.model.entity.Book;
import com.alpha.view.BooksView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class ServiceBooksTest {

    @Parameterized.Parameter
    public double price;

    @Parameterized.Parameter(1)
    public int percent;

    @Parameterized.Parameter(2)
    public double expected;

    @Parameterized.Parameters
    public static Collection<Object[]> getData(){
        return Arrays.asList(new Object[][]{
                {10.0, 30, 13},
                {456.5, -50, 228.25},
                {987.26, 50, 1480.89},
                {123.25, 100, 246.50},
                {90.0, -30, 63}
        });
    }

    private ServiceBooks serviceBooks;

    @Before
    public void init() {
        serviceBooks = new ServiceBooks();
    }

    @Test
    public void testChangePriceBook() {
        System.out.println("------------------------- testChangePriceBook -------------------");
        System.out.println("Начальное количество книг: " + serviceBooks.getBookList().size());
        for (Book book : SourceBooks.generateBooks(1)) {
            serviceBooks.addBook(
                    book.getName(),
                    book.getAuthor(),
                    book.getPublish(),
                    book.getYear(),
                    book.getCountPages(),
                    price
            );
        }
        System.out.println("Добавлено книг: " + serviceBooks.getBookList().size());
        System.out.println("Стоимость: " + serviceBooks.getBookList().get(0).getCost());
        System.out.println("Процент изменения стоимости: " + percent);
        serviceBooks.changePriceBook(percent);
        System.out.println("Результат: " + serviceBooks.getBookList().get(0).getCost());
        System.out.println("-----------------------------------------------------------------");
    }
}
