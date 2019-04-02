package moe.ning.readinglist.dao;

import moe.ning.readinglist.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, String>
{
    List<Reader> findByUsername(String username);
}
