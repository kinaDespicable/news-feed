package dev.dani.strong.newsfeed.controller;

import dev.dani.strong.newsfeed.model.dto.request.newsSource.CreateNewsSourceRequest;
import dev.dani.strong.newsfeed.model.dto.request.newsSource.UpdateNewsSourceRequest;
import dev.dani.strong.newsfeed.service.NewsSourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news-source")
public class NewsSourceController {

    private final NewsSourceService newsSourceService;

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody @Valid CreateNewsSourceRequest createRequest) {
        return new ResponseEntity<>(newsSourceService.create(createRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(newsSourceService.fetchById(id), HttpStatus.OK);
    }

    @GetMapping("/source/{sourceName}")
    public ResponseEntity<?> getById(@PathVariable String sourceName) {
        return new ResponseEntity<>(newsSourceService.fetchBySourceName(sourceName), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(newsSourceService.fetchAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id,
                                        @RequestBody @Valid UpdateNewsSourceRequest updateRequest) {
        return new ResponseEntity<>(newsSourceService.updateById(id, updateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(newsSourceService.deleteById(id), HttpStatus.OK);
    }
}
