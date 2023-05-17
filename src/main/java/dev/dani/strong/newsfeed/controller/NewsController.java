package dev.dani.strong.newsfeed.controller;

import dev.dani.strong.newsfeed.model.dto.request.news.CreateNewsRequest;
import dev.dani.strong.newsfeed.model.dto.request.news.UpdateNewsRequest;
import dev.dani.strong.newsfeed.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<?> create(@RequestBody @Valid CreateNewsRequest createRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(newsService.create(createRequest, authentication), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(newsService.fetchById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getByAll(@RequestParam(name = "page", required = false) Optional<Integer> page,
                                      @RequestParam(name = "size", required = false) Optional<Integer> size,
                                      @RequestParam(name = "sort", required = false) Optional<String> sort) {
        return new ResponseEntity<>(newsService.fetchAll(page, size, sort), HttpStatus.OK);
    }

    @GetMapping("/by-source-id/{id}")
    public ResponseEntity<?> getBySourceId(@PathVariable("id") Long id,
                                           @RequestParam(name = "page", required = false) Optional<Integer> page,
                                           @RequestParam(name = "size", required = false) Optional<Integer> size,
                                           @RequestParam(name = "sort", required = false) Optional<String> sort) {
        return new ResponseEntity<>(newsService.fetchBySourceId(id, page, size, sort), HttpStatus.OK);
    }

    @GetMapping("/by-topic-id/{id}")
    public ResponseEntity<?> getByTopicId(@PathVariable("id") Long id,
                                          @RequestParam(name = "page", required = false) Optional<Integer> page,
                                          @RequestParam(name = "size", required = false) Optional<Integer> size,
                                          @RequestParam(name = "sort", required = false) Optional<String> sort) {
        return new ResponseEntity<>(newsService.fetchByTopicId(id, page, size, sort), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id,
                                        @RequestBody @Valid UpdateNewsRequest updateRequest){
        return new ResponseEntity<>(newsService.updateById(id, updateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        return new ResponseEntity<>(newsService.deleteById(id), HttpStatus.OK);
    }

}
