package local.home.daytoday;

import local.home.daytoday.dto.LinkDto;
import local.home.daytoday.exception.BadLinkException;
import local.home.daytoday.service.LinkService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.stream.Stream;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "classpath:db/delete-all-links.sql")
@ActiveProfiles("test")
public class LinkUnitTest {
    @Value("${spring.settings.link.length}")
    private Integer requiredLength;

    private static final String TEMPLATE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

    @Autowired
    private LinkService linkService;

    @ParameterizedTest
    @MethodSource("getParametersSource")
    @DisplayName("Test successfully generates short links by full urls")
    public void shouldGenerate_successfullyGet(String url) {
        LinkDto dto = linkService.save(url);
        String shortUrl = dto.shortcutUrl();
        Assertions.assertNotNull(shortUrl, "Short link must have value");
        Assertions.assertEquals(requiredLength, shortUrl.length());
        for (int i = 0; i < requiredLength; i++) {
            Assertions.assertTrue(StringUtils.containsAny(TEMPLATE_CHARS, shortUrl.charAt(i)), "Short link must contain chars only from template");
        }
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void shouldNotGenerate_throw_if_empty(String url) {
        Assertions.assertThrows(BadLinkException.class, () -> linkService.save(url));
    }

    @ParameterizedTest
    @MethodSource("getParametersSource")
    public void shouldGenerate_save_and_successfullyGetLinkByShort(String url) {
        LinkDto dto = linkService.save(url);
        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.shortcutUrl());
        LinkDto foundDto = linkService.getByShort(dto.shortcutUrl());
        Assertions.assertNotNull(foundDto);
        Assertions.assertNotNull(foundDto.shortcutUrl());
        Assertions.assertEquals(dto.id(), foundDto.id());
        Assertions.assertEquals(dto.fullUrl(), foundDto.fullUrl());
        Assertions.assertEquals(dto.shortcutUrl(), foundDto.shortcutUrl());
        Assertions.assertEquals(dto.redirectUrl(), foundDto.redirectUrl());
    }

    private static Stream<String> getParametersSource() {
        return Stream.of(
                "https://start.spring.io",
                "https://www.baeldung.com",
                "https://www.coursera.org",
                "https://www.udemy.com",
                "https://www.learnjavaonline.org");
    }
}