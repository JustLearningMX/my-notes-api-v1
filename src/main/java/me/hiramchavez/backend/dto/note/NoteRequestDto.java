package me.hiramchavez.backend.dto.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.hiramchavez.backend.dto.category.CategoryBodyReqDto;
import me.hiramchavez.backend.model.State;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link me.hiramchavez.backend.model.Note}
 */
public record NoteRequestDto(
      @NotBlank(message = "Title for note is required")
      String title,
      String description,
      @NotNull(message = "Last edited date is required")
      Date lastEdited,
      @NotNull(message = "State for the note is required")
      State state,
      @NotNull(message = "Availability for the note is required")
      Boolean deleted

) implements Serializable {
}