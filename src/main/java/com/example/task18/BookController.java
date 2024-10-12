package com.example.task18;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import java.util.Collections;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param; //привязка пераметров
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute; //связка параметра и метода из интерфейса
import org.springframework.web.bind.annotation.PathVariable; // получение парам. из адрес. строки браузера

import org.springframework.web.bind.annotation.RequestMapping; // создание адреса url

import org.springframework.web.bind.annotation.RequestMethod; // с помощью какого http-запроса передача данных
import org.springframework.web.servlet.ModelAndView;
import java.util.Comparator;


@Controller
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Book> listBook = service.ListAll(keyword);

        Map<Date, Integer> dateMap = new HashMap<>();

        for (Book book : listBook) {
            Date dateBook = book.getDateOfIssue();
            dateMap.put(dateBook, dateMap.getOrDefault(dateBook, 0) + 1);
        }

        List<List<Object>> dateCountMap = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Map.Entry<Date, Integer> entry : dateMap.entrySet()) {
            List<Object> subList = new ArrayList<>();
            subList.add(sdf.format(entry.getKey()));
            subList.add(entry.getValue());
            dateCountMap.add(subList);
        }

        Collections.sort(dateCountMap, new Comparator<List<Object>>() {
            public int compare(List<Object> o1, List<Object> o2) {
                try {
                    Date date1 = sdf.parse((String) o1.get(0));
                    Date date2 = sdf.parse((String) o2.get(0));
                    return date1.compareTo(date2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        model.addAttribute("chartData", dateCountMap);

        model.addAttribute("listBook", listBook);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "new_book";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("book") Book book) {
        service.save(book);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditBook(@PathVariable(name="id") Long id) {
        ModelAndView mav = new ModelAndView("edit_book");
        Book book = service.get(id);
        mav.addObject("book", book);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name="id") Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping("sort")
    public  String sortHomePage(Model model, @Param("keyword") String keyword){
        List<Book> listBook = service.ListAll(keyword);
        listBook.sort(Comparator.comparing(Book::getDateOfIssue));

        Map<Date, Integer> dateMap = new HashMap<>();

        for (Book book : listBook) {
            Date dateBook = book.getDateOfIssue();
            dateMap.put(dateBook, dateMap.getOrDefault(dateBook, 0) + 1);
        }

        List<List<Object>> dateCountMap = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Map.Entry<Date, Integer> entry : dateMap.entrySet()) {
            List<Object> subList = new ArrayList<>();
            subList.add(sdf.format(entry.getKey()));
            subList.add(entry.getValue());
            dateCountMap.add(subList);
        }

        Collections.sort(dateCountMap, new Comparator<List<Object>>() {
            public int compare(List<Object> o1, List<Object> o2) {
                try {
                    Date date1 = sdf.parse((String) o1.get(0));
                    Date date2 = sdf.parse((String) o2.get(0));
                    return date1.compareTo(date2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        model.addAttribute("chartData", dateCountMap);


        model.addAttribute("listBook", listBook);
        model.addAttribute("keyword", keyword);
        return "index";
    }
}
