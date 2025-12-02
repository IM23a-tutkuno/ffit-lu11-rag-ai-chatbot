package ch.bzz.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.MalformedURLException;
import ch.bzz.rag.service.WikiPageDownloaderService;
import ch.bzz.rag.service.WikiPageCollectorService;

import java.util.Set;

@Slf4j
@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) {
        log.info("Application has been started");
        WikiPageDownloaderService downloader = new WikiPageDownloaderService();
        try {
            downloader.init("https://wiki.bzz.ch");
            String content = downloader.downloadPage("de:modul:ffit:3-jahr:java:learningunits:lu01:aufgaben:branching");
            log.info("content: '{}'", content);
        } catch (MalformedURLException e) {
            log.error("Error using url {}", e.getMessage(), e);
        }

        WikiPageCollectorService collector = new WikiPageCollectorService();
        String namespace = "de:modul:ffit:3-jahr:java:learningunits:lu11:";
        Set<String> pages = collector.collectPagesForNamespace("https://wiki.bzz.ch", namespace);
        log.info(pages.toString());
    }
}
