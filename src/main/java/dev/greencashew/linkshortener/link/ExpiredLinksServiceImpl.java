package dev.greencashew.linkshortener.link;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;



@AllArgsConstructor
@Service
@Slf4j
class ExpiredLinksServiceImpl implements ExpiredLinksService{

    private final LinkRepository linkRepository;

    @Override
    public void removeExpiredLinks(final LocalDate currentDate) {
        final List<LinkEntity> expiredLinksImpl = linkRepository.findLinksBeforeDate(currentDate);
        linkRepository.deleteAll(expiredLinksImpl);
        log.info((long) expiredLinksImpl.size() + " items with passed time " + currentDate + " has been deleted.");
    }


}
