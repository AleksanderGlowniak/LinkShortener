package dev.greencashew.linkshortener.controller;

import dev.greencashew.linkshortener.link.api.LinkDto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.time.LocalDate;

record CreateLinkDto(
        @Schema(description = "Identifier/alias to link. Used for redirection.", example = "link-alias", required = true)
                @NotBlank (message = "must contain alias")
        @Size(min = 1, max = 60)
    String id,
    @Schema(description = "User email required for shortened link management (deletion, updating)", example = "test@greencashnew.dev", required = true)
            @NotBlank @Email
    String email,
    @Schema(description = "Destination url we would like to", example = "https://wp.pl/", required = true)
            @NotBlank
    String targetUrl,
    @Schema(description = "Link expiration time. If would like to have shortened link forever do not fill this field", example = "2022-01-01", required = false)
            @Future
            @NotNull
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
