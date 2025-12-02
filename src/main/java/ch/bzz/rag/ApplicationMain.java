package ch.bzz.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import java.net.MalformedURLException;
import ch.bzz.rag.service.StoreService;
import org.springframework.ai.document.Document;
import ch.bzz.rag.service.WikiPageDownloaderService;
import ch.bzz.rag.service.WikiPageCollectorService;
import ch.bzz.rag.service.WikiCrawlerPipelineService;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Set;
import java.util.List;

@Slf4j
@SpringBootApplication
public class ApplicationMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationMain.class, args);

        StoreService storeService = ctx.getBean(StoreService.class);
        storeService.updateIndex();
        int numberOfResults = 5;
        String query = "Was ist Lombok?";
        List<Document> docs = storeService.search(query, numberOfResults);
        for(Document doc : docs){
            log.info("Doc with id '{}' and metadata '{}'", doc.getId() , doc.getMetadata());
        }

        
        ctx.close();
    }
}

