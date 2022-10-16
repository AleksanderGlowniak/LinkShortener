package dev.greencashew.linkshortener.link;

import dev.greencashew.linkshortener.link.api.LinkService;
import dev.greencashew.linkshortener.link.api.exceptions.LinkNotFoundException;
import io.swagger.v3.oas.models.links.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class LinkServiceDeleteTest {

    private LinkService linkService;

    LinkRepository linkRepository ;

    @BeforeEach
    void setUp() {
        linkRepository = mock(LinkRepository.class);
        linkService = new LinkServiceImpl(linkRepository);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            id, email, id, email,
            id, email, ID, email,
            id, email, id, EMAIL,
                   
            """)
    void shouldDeleteLinkWhenFound(String entityId, String entityEmail, String deleteLinkId, String deleteLinkEmail) {
        //given
        LinkEntity createdLink = LinkEntity
                .builder()
                .id(entityId)
                .email(entityEmail)
                .build();
        given(linkRepository.findById(entityId)).willReturn(Optional.of(createdLink));

        //when
        final boolean isLinkDeleted = linkService.deleteLink(deleteLinkId, deleteLinkEmail);

        //then
        assertTrue(isLinkDeleted, "link should be deleted");
        then(linkRepository).should().deleteById(entityId);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            id, email, id, email1
            id, email, id, 
             
                              
            """)
    void shouldNotDeleteLinkWhenFound(String entityId, String entityEmail, String deleteLinkId, String deleteLinkEmail) {
        //given
        LinkEntity createdLink = LinkEntity
                .builder()
                .id(entityId)
                .email(entityEmail)
                .build();
        given(linkRepository.findById(entityId)).willReturn(Optional.of(createdLink));

        //when
        final boolean isLinkDeleted = linkService.deleteLink(deleteLinkId, deleteLinkEmail);

        //then
        assertFalse(isLinkDeleted, "link shouldn't be deleted");
        then(linkRepository).should().findById(any());
        then(linkRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldNotDeleteLinkWhenNotFound() {
        //given

        given(linkRepository.findById("id")).willReturn(Optional.empty());

        //when


        //then
        assertThrows(LinkNotFoundException.class, () -> linkService.deleteLink("id", null));

    }
}