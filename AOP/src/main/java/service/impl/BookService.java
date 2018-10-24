package service.impl;

import monitor.BookMonitor;
import service.Book;

/**
 * @author teng.long@hand-china.com
 * @Name BookService
 * @Description
 * @Date 2018/10/24
 */
public class BookService implements Book{
    public void deleteBook(int count) {
        BookMonitor.begin(Thread.currentThread().getStackTrace()[1].getMethodName().toString());

        System.out.println("刪除书籍"+count+"本。");

        BookMonitor.end();
    }

    public void insertBook(int count) {
        System.out.println("插入数据"+count+"本。");
    }
}
