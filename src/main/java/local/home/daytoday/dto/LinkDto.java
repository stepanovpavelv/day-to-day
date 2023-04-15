package local.home.daytoday.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;

@Schema(description = "Detail link's information")
public record LinkDto(
        @Schema(name = "id", description = "Unique int64 identifier", example = "1")
        Long id,
        @Schema(name = "createDate", description = "Creation date of link", example = "2023-04-12 22:28:40.602085+04")
        ZonedDateTime createDate,
        @Schema(name = "fullUrl", description = "Initial full url address", example = "https://start.spring.io")
        String fullUrl,
        @Schema(name = "shortcutUrl", description = "Cut url after inner algorithm", example = "shJhAb1")
        String shortcutUrl,
        @Schema(name = "redirectUrl", description = "Full url for redirection", example = "http://localhost:8080/link/shJhAb1")
        String redirectUrl) { }
