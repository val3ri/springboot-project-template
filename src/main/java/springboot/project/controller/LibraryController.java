package springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.project.core.RestService;
import springboot.project.model.BookEntity;

import java.util.List;

@RestController
public class LibraryController {
    private final RestService restService;

    @Autowired
    public LibraryController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping(value = "/books")
    public List<BookEntity> getEmployees() {
        return restService.findAll();
    }
}