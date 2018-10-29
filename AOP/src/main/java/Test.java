import service.IBook;
import service.impl.BookServiceImpl;

/**
 * @author teng.long@hand-china.com
 * @Name Test
 * @Description
 * @Date 2018/10/26
 */
public class Test {

    private static IBook iBook = new BookServiceImpl();

    /**
     * 没有AOP的时候最业务逻辑
     */
    public static void test1(){
        iBook.deleteBook(10);
        iBook.insertBook(10);
    }

    public static void main(String[] args) {
        test1();
    }



}
