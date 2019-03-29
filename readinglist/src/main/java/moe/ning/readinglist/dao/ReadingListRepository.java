package moe.ning.readinglist.dao;

import moe.ning.readinglist.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<Book, Long>
{
    /*
     * 谁来实现接口？
     * Spring Data 的魔法，只需要定义仓库接口，
     * 应用启动时，会自动实现该接口
     */
    List<Book> findByReader(String reader);
}
