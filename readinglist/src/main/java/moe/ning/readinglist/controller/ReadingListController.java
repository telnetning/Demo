package moe.ning.readinglist.controller;

import moe.ning.readinglist.dao.ReadingListRepository;
import moe.ning.readinglist.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController
{
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value = "/readinglist/{reader}", method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        /* @PathVariable 是指从 url 中取值进行填充
         * 对比 @RequestParam 从 request 中取值
         */
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList != null) {

            model.addAttribute("books", readingList);
        }

        return "readingList";
    }

    @RequestMapping(value = "/readinglist/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book); //持久化
        return "redirect:/readinglist/{reader}";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
