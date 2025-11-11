package me.hiramchavez.backend.dto.category;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link me.hiramchavez.backend.model.Category}
 */
public record CategoryBodyResDto(
  Long id,
  String name

) implements Serializable {
}