import service.Book;
import service.impl.BookService;

/**
 * @author teng.long@hand-china.com
 * @Name main
 * @Description
 * @Date 2018/10/24
 */
public class main {

    private static Book book = new BookService();

    public static void main(String[] args) {
        book.deleteBook(10);
    }

}
