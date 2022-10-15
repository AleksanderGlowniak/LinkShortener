package dev.greencashew.linkshortener.link;

import dev.greencashew.linkshortener.link.api.LinkDto;

import dev.greencashew.linkshortener.link.api.LinkService;
import dev.greencashew.linkshortener.link.api.exceptions.LinkAlreadyExistsException;
import dev.greencashew.linkshortener.link.api.exceptions.LinkNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkServiceTest {

    private LinkService linkService;

    @BeforeEach
    void setUp() {
        linkService = new LinkServiceImpl(new LinkInMemoryRepository());
    }

    @Test // 2 testy w jednym a powinny być 2 osobne
    void shouldSaveAndRetrieveLink(){
        //given

        final LinkDto linkDto = new LinkDto("id","email", "targetUrl", null,0);

        //when
        final LinkDto resultLink = linkService.createLink(linkDto);
        final String resultTargetUrl = linkService.gatherLinkAndIncrementVisits(linkDto.id());
        //then
        assertEquals(linkDto.id(),resultLink.id());
        assertEquals(linkDto.targetUrl(),resultTargetUrl);

    }

    @Test
    void shouldThrowExceptionIfLinkNotFound() {
        //given
        String notExistingId = "something";

        //when


        //then
        assertThrows(LinkNotFoundException.class, () -> linkService.gatherLinkAndIncrementVisits(notExistingId));
    }

    @Test
    @DisplayName("Should throw link not found exception when link with same id already added")
    void shouldThrowLinkAlreadyExistsExceptionWhenLinkWithSameIdAlreadyAdded() {
        //given
        String duplicatedId = "id";
        linkService.createLink(new LinkDto(duplicatedId, "email", "target", null, 0));
        //when //then
        assertThrows(LinkAlreadyExistsException.class, () -> linkService.createLink(new LinkDto(duplicatedId, "another email", "another target", null, 0)));
    }
}