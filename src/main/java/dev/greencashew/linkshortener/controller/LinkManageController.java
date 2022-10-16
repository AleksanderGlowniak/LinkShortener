package dev.greencashew.linkshortener.controller;

import dev.greencashew.linkshortener.link.api.LinkDto;
import dev.greencashew.linkshortener.link.api.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/links")
class LinkManageController {
    private final LinkService linkService;



    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    LinkDto createLink(@Valid @RequestBody CreateLinkDto link) {
        return linkService.createLink(link.toDto());
        }

        @ResponseBody
        @ResponseStatus(HttpStatus.OK)
        @GetMapping("/visits/{visits}")
        List<LinkDto> getLinksForVisitsHigherThan(@PathVariable Integer visits) {
            return linkService.getLinksForVisitsHigherThan(visits);
    }

}
