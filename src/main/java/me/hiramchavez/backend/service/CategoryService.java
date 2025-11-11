package me.hiramchavez.backend.service;

import lombok.RequiredArgsConstructor;
import me.hiramchavez.backend.dto.category.CategoryBodyResDto;
import me.hiramchavez.backend.dto.category.CategoryListResDto;
import me.hiramchavez.backend.dto.category.CategoryReqDto;
import me.hiramchavez.backend.dto.category.CategoryResDto;
import me.hiramchavez.backend.exception.category.CategoryEmptyException;
import me.hiramchavez.backend.exception.note.NoteNotFoundException;
import me.hiramchavez.backend.mapper.CategoryMapper;
import me.hiramchavez.backend.model.Category;
import me.hiramchavez.backend.model.Note;
import me.hiramchavez.backend.repository.CategoryRepository;
import me.hiramchavez.backend.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final NoteRepository noteRepository;
    private final CategoryMapper categoryMapper;


    public CategoryResDto createCategories(CategoryReqDto categoryReqDto) {

        if (categoryReqDto.categories().size() < 1)
            throw new CategoryEmptyException("The list of categories received to create is empty. Add at least one category");

        Note note = noteRepository.getNoteByIdAndDeletedFalse(categoryReqDto.note_id());

        if (note == null) {
            throw new NoteNotFoundException("Note not found");
        }

        note.clearCategories();

        List<CategoryBodyResDto> categoryBodyResDto = categoryReqDto.categories().stream()
          .map( categoryBodyDto -> {
              boolean exists = categoryRepository.existsByName(categoryBodyDto.name());

              Category category;

              //If the category does not exist, create it
              if (!exists) {
                  category = categoryMapper.categoryBodyReqDtoToCategory(categoryBodyDto);
                  category = categoryRepository.save(category);
                  category.setNotes( new HashSet<>());
              } else { //If the category exists, get it from the database
                    category = categoryRepository.getCategoryByName(categoryBodyDto.name());
              }

              note.addCategory(category);

              return categoryMapper.categoryToCategoryBodyResDto(category);
          })
          .toList();

        return new CategoryResDto(
            note.getId(),
            categoryBodyResDto
        );
    }

    public CategoryListResDto getCategories() {

            List<Category> categories = categoryRepository.findAll();

            List<CategoryBodyResDto> categoryBodyResDto = categories.stream()
              .map(categoryMapper::categoryToCategoryBodyResDto)
              .toList();

            return new CategoryListResDto(
                categoryBodyResDto
            );
    }
}