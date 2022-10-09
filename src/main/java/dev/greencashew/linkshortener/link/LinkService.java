package dev.greencashew.linkshortener.link;

import dev.greencashew.linkshortener.dto.LinkDto;

public interface LinkService {
    LinkDto createLink(LinkDto toDto);

    String gatherLink(String id);
}
