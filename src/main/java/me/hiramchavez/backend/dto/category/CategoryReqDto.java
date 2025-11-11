package me.hiramchavez.backend.dto.category;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link me.hiramchavez.backend.model.Category}
 */
public record CategoryReqDto(
  @NotNull(message = "Note id is required to add categories")
  Long note_id,
  @Valid
  List<CategoryBodyReqDto> categories

) implements Serializable {
}