package dev.greencashew.linkshortener.scheduler;

import dev.greencashew.linkshortener.link.ExpiredLinksService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@AllArgsConstructor
class RemoveExpiredLinksCron {


   private final ExpiredLinksService expiredLinksService;

    @Scheduled(cron = "${expired.links.cron}")
    void removeExpiredLinks() {

        log.info("Expired links cron job started");
       expiredLinksService.removeExpiredLinks(LocalDate.now());
        log.info("Expired links cron job ended");
    }

}
