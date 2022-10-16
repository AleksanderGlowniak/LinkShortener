package dev.greencashew.linkshortener.link.api;

import java.util.List;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);


    String gatherLinkAndIncrementVisits(String id);

    List<LinkDto> getLinksForVisitsHigherThan(Integer visits);

    LinkDto getLinkById(String id);


    /**
     * @param id
     * @param email
     * @return returns {@code true} when link was found and deleted
     */
    boolean deleteLink(String id, String email);
}
