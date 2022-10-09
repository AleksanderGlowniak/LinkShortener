package dev.greencashew.linkshortener.link;

import dev.greencashew.linkshortener.dto.LinkDto;
import dev.greencashew.linkshortener.link.exceptions.LinkAlreadyExistsException;
import dev.greencashew.linkshortener.link.exceptions.LinkNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
class LinkServiceImpl implements LinkService {

    private final HashMap<String, LinkDto> linkRepository;

    LinkServiceImpl() {

        linkRepository = new HashMap<>();

    }

    @Override
    public LinkDto createLink(final LinkDto toDto) {
       if (linkRepository.get(toDto.id()) != null)
           throw new LinkAlreadyExistsException(toDto.id());
               linkRepository.put(toDto.id(), toDto);
        return toDto;
    }

    @Override
    public String gatherLink(final String id) {
        LinkDto linkDto = linkRepository.get(id);
        if (linkDto==null){
            throw new LinkNotFoundException(id);
        }
        return linkDto.targetUrl();

    }
}
