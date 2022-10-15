package dev.greencashew.linkshortener.link.api;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);

    String gatherLink(String id);
}
