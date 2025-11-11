package me.hiramchavez.backend.dto.note;

import me.hiramchavez.backend.dto.category.CategoryBodyResDto;
import me.hiramchavez.backend.dto.category.CategoryResDto;
import me.hiramchavez.backend.model.State;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link me.hiramchavez.backend.model.Note}
 */
public record NoteResponseDto(
      Long id,
      String title,
      String description,
      Date lastEdited,
      State state,
      Boolean deleted,
      List<CategoryBodyResDto> categories

) implements Serializable {
}