package local.home.daytoday.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import local.home.daytoday.dto.LinkDto;
import local.home.daytoday.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Link controller", description = "Resource for link's communication")
@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
@ResponseBody
public class LinkResource {
    private final LinkService linkService;

    @Operation(summary = "Save link", description = "Method create short link of full url.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful link's creation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LinkDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Empty link", content = @Content),
            @ApiResponse(responseCode = "403", description = "Link already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<LinkDto> addLink(@RequestBody
                         @Parameter(name = "newUrl", description = "Link for encoding", required = true, example = "https://start.spring.io")
                         String newUrl) {
        LinkDto dto = linkService.save(newUrl);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "By cut url", description = "Method gets full link by short variant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Link is received", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LinkDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Link was not found", content = @Content)
    })
    @GetMapping("/{shortLink}")
    public ResponseEntity<LinkDto> getLinkByShortUrl(@PathVariable(value = "shortLink")
                         @Parameter(name = "shortLink", description = "Link for searching", required = true, example = "Uhn4Ghg9sl")
                         String shortUrl) {
        LinkDto dto = linkService.getByShort(shortUrl);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Link by id", description = "Method gets full link by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Link is received", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LinkDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Link was not found", content = @Content)
    })
    @GetMapping("/by/{id}")
    public ResponseEntity<LinkDto> getLinkById(@PathVariable(value = "id")
                                           @Parameter(name = "id", description = "Identifier for searching", required = true, example = "1")
                                           Long id) {
        LinkDto dto = linkService.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}