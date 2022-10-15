package dev.greencashew.linkshortener.controller;

import dev.greencashew.linkshortener.link.api.LinkDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

record CreateLinkDto(
        @Schema(description = "Identifier/alias to link. Used for redirection.", example = "link-alias", required = true)
    String id,
    String email,
    String targetUrl,
    LocalDate expirationDate){

    LinkDto toDto() {
            return new LinkDto(
                    id,
                    email,
                    targetUrl,
                    expirationDate,
                    0
            );
        }

}
