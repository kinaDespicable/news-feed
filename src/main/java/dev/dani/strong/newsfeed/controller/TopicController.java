package dev.dani.strong.newsfeed.controller;

import dev.dani.strong.newsfeed.model.dto.request.topic.CreateTopicRequest;
import dev.dani.strong.newsfeed.model.dto.request.topic.UpdateTopicRequest;
import dev.dani.strong.newsfeed.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody @Valid CreateTopicRequest createTopicRequest) {
        return new ResponseEntity<>(topicService.create(createTopicRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(topicService.fetchAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(topicService.fetchById(id), HttpStatus.OK);
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<?> getByTopic(@PathVariable String topic){
        return new ResponseEntity<>(topicService.fetchByTopicName(topic), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id,
                                        @RequestBody @Valid UpdateTopicRequest updateTopicRequest) {
        return new ResponseEntity<>(topicService.updateById(id, updateTopicRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(topicService.deleteById(id), HttpStatus.OK);
    }

}
