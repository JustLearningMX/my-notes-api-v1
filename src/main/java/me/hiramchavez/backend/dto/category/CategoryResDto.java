package me.hiramchavez.backend.dto.category;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link me.hiramchavez.backend.model.Category}
 */
public record CategoryResDto(
  Long note_id,
  List<CategoryBodyResDto> categories

) implements Serializable {
}