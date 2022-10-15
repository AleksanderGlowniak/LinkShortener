package dev.greencashew.linkshortener.link;

import java.time.LocalDate;

public interface ExpiredLinksService {


    void removeExpiredLinks(LocalDate now);

}
