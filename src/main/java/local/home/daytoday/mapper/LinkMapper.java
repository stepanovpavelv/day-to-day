package local.home.daytoday.mapper;

import local.home.daytoday.dto.LinkDto;
import local.home.daytoday.model.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LinkMapper {
    LinkMapper INSTANCE = Mappers.getMapper(LinkMapper.class);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.createDate", target = "createDate")
    @Mapping(source = "entity.fullUrl", target = "fullUrl")
    @Mapping(source = "entity.shortcutUrl", target = "shortcutUrl")
    LinkDto toDto(Link entity);
}