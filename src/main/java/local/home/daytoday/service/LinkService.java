package local.home.daytoday.service;

import local.home.daytoday.dto.LinkDto;

public interface LinkService {
    LinkDto save(String url);

    LinkDto getByShort(String shortUrl);

    LinkDto getById(Long id);
}