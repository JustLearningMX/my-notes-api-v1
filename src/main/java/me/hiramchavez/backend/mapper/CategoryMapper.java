package me.hiramchavez.backend.mapper;

import me.hiramchavez.backend.dto.category.CategoryBodyReqDto;
import me.hiramchavez.backend.dto.category.CategoryBodyResDto;
import me.hiramchavez.backend.dto.category.CategoryReqDto;
import me.hiramchavez.backend.dto.category.CategoryResDto;
import me.hiramchavez.backend.model.Category;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category categoryBodyReqDtoToCategory(CategoryBodyReqDto categoryBodyReqDto);

    CategoryBodyReqDto categoryToCategoryBodyReqDto(Category category);

    CategoryBodyResDto categoryToCategoryBodyResDto(Category category);

}