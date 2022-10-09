package dev.greencashew.linkshortener.controller;

import dev.greencashew.linkshortener.link.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@AllArgsConstructor
class RedirectController {

    private final LinkService linkService;


    @GetMapping(path = "/s/{id}")
    public void redirectLink(
            @PathVariable String id,
            HttpServletResponse httpServletResponse

    ) throws IOException {

        String targetUrl = linkService.gatherLink(id);

        httpServletResponse.sendRedirect(targetUrl);
    }
}
