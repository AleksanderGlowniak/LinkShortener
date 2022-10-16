package dev.greencashew.linkshortener.link.api;

import javax.transaction.Transactional;
import java.util.List;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);


    String gatherLinkAndIncrementVisits(String id);

    List<LinkDto> getLinksForVisitsHigherThan(Integer visits);
}
