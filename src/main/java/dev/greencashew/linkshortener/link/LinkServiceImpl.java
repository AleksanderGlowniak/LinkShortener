package dev.greencashew.linkshortener.link;

import dev.greencashew.linkshortener.link.api.LinkDto;
import dev.greencashew.linkshortener.link.api.LinkService;
import dev.greencashew.linkshortener.link.api.exceptions.LinkAlreadyExistsException;
import dev.greencashew.linkshortener.link.api.exceptions.LinkNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
class LinkServiceImpl implements LinkService {


    private final LinkRepository linkRepository;


    @Override
    public LinkDto createLink(final LinkDto toDto) {
        final String lowerCaseID = toDto.id().toLowerCase();
        if (linkRepository.findById(lowerCaseID).isPresent()) {
            throw new LinkAlreadyExistsException(toDto.id());
        }
        LinkEntity entity = LinkEntity.fromDto(toDto);
        entity.setId(lowerCaseID);
        linkRepository.save(entity);
        return toDto;
    }


    @Override
    @Transactional
    public String gatherLinkAndIncrementVisits(final String id){
        final  LinkEntity linkEntity = linkRepository.findById(id)
        .orElseThrow(() -> new LinkNotFoundException(id));
        linkEntity.setVisits(linkEntity.getVisits() + 1);
        return linkEntity.getTargetUrl();
    }

    @Override
    public List<LinkDto> getLinksForVisitsHigherThan(final Integer visits) {
        return linkRepository.findAllByVisitsGreaterThan(visits)
                .stream()
                .map(linkEntity -> linkEntity.toDto())
                .collect(Collectors.toList());
    }

    @Override
    public LinkDto getLinkById(final String id) {
        final String lowerCaseId = id.toLowerCase();
        return linkRepository.findById(lowerCaseId)
                .orElseThrow(() -> new LinkNotFoundException(lowerCaseId))
                .toDto();
    }

    @Override
public boolean deleteLink(final String id, final String email) {
        final String lowerCaseId = id.toLowerCase();
        final LinkDto linkDto = getLinkById(lowerCaseId);
        if (linkDto.email().equalsIgnoreCase(email)){
            linkRepository.deleteById(lowerCaseId);
            return true;

        }
        return false;
    }


}
