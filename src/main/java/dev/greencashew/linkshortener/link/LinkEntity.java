package dev.greencashew.linkshortener.link;

import dev.greencashew.linkshortener.link.api.LinkDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
class LinkEntity {

    @Id
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String targetUrl;
    private LocalDate expirationDate;

    private int visits;


    LinkDto toDto(){
        return new LinkDto(
                id,
                email,
                targetUrl,
                expirationDate,
                visits
        );
    }
    static LinkEntity fromDto(LinkDto linkDto){
        return new LinkEntity(
                linkDto.id(),
                linkDto.email(),
                linkDto.targetUrl(),
                linkDto.expirationDate(),
                linkDto.visits()
        );
    }
}
