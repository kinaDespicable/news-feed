package dev.dani.strong.newsfeed.config.scheduler;

import dev.dani.strong.newsfeed.model.entity.NewsSource;
import dev.dani.strong.newsfeed.repository.NewsRepository;
import dev.dani.strong.newsfeed.repository.NewsSourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsStatisticsService {

    private final NewsSourceRepository sourceRepository;
    private final NewsRepository newsRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void generateStatistics(){
        List<NewsSource> sources = sourceRepository.findAll();
        Map<String, Integer> statistics = new HashMap<>();

        for (NewsSource source : sources) {
            int newsCount = newsRepository.countByNewsSource(source);
            statistics.put(source.getName(), newsCount);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("statistics.csv"))) {
            for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
