package monitoring.controller;

import monitoring.core.RestService;
import monitoring.model.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {
    private final RestService restService;

    @Autowired
    public LibraryController(RestService restService) {
        this.restService = restService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/books")
    public List<BookEntity> getEmployees() {
        return restService.findAll();
    }
}