package local.home.daytoday.service.impl;

import local.home.daytoday.dao.LinkRepository;
import local.home.daytoday.dto.LinkDto;
import local.home.daytoday.exception.BadLinkException;
import local.home.daytoday.exception.NotFoundLinkException;
import local.home.daytoday.mapper.LinkMapper;
import local.home.daytoday.model.Link;
import local.home.daytoday.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class LinkServiceImpl implements LinkService {
    @Value("${spring.settings.link.length}")
    private Integer urlLength;

    @Value("${spring.settings.link.prefix}")
    private String urlPrefix;

    private final LinkRepository linkRepository;

    @Override
    public LinkDto save(String url) {
        if (!StringUtils.hasText(url)) {
            throw new BadLinkException("Validation error: url is empty");
        }

        String generatedUrl = generateUrlPath();

        Link link = new Link();
        link.setFullUrl(url);
        link.setShortcutUrl(generatedUrl);
        link.setRedirectUrl(urlPrefix + "/" + generatedUrl);

        Link saved = linkRepository.save(link);
        return LinkMapper.INSTANCE.toDto(saved);
    }

    @Override
    public LinkDto getByShort(String shortUrl) {
        Optional<Link> linkOpt = linkRepository.findByShortcutUrl(shortUrl);
        if (linkOpt.isEmpty()) {
            throw new NotFoundLinkException("Not found by url: " + shortUrl);
        }

        return LinkMapper.INSTANCE.toDto(linkOpt.get());
    }

    @Override
    public LinkDto getById(Long id) {
        Optional<Link> linkOpt = linkRepository.findById(id);
        if (linkOpt.isEmpty()) {
            throw new NotFoundLinkException("Not found by id: " + id);
        }

        return LinkMapper.INSTANCE.toDto(linkOpt.get());
    }

    private String generateUrlPath() {
        String template = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < urlLength; i++) {
            int index = (int)(template.length() * Math.random());
            sb.append(template.charAt(index));
        }
        return sb.toString();
    }
}