package me.hiramchavez.backend.dto.category;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link me.hiramchavez.backend.model.Category}
 */
public record CategoryBodyReqDto(
  Long id,
  @NotBlank(message = "Category Name is required")
  String name

) implements Serializable {
}