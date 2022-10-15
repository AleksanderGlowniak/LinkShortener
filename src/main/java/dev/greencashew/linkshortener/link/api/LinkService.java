package dev.greencashew.linkshortener.link.api;

import javax.transaction.Transactional;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);


    String gatherLinkAndIncrementVisits(String id);
}
