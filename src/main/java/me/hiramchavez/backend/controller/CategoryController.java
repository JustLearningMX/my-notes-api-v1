package me.hiramchavez.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.hiramchavez.backend.dto.category.CategoryListResDto;
import me.hiramchavez.backend.dto.category.CategoryReqDto;
import me.hiramchavez.backend.dto.category.CategoryResDto;
import me.hiramchavez.backend.dto.note.NoteResponseDto;
import me.hiramchavez.backend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Manage all endpoints about Categories") //For OpenApi-Swagger
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(
      summary = "Create a Categories based on a list.",
      description = "Let a user create categories of a note from a list."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "201", description = "Categories created successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = NoteResponseDto.class))
        }),
      @ApiResponse(responseCode = "404", description = "Note not found.", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Server error.", content = {@Content})
    })
    @PostMapping
    @Transactional
    public ResponseEntity<CategoryResDto> createCategories(@RequestBody @Valid CategoryReqDto categoryReqDto) {

        CategoryResDto categoryResDto = categoryService.createCategories(categoryReqDto);

        return ResponseEntity
          .status(201)
          .body(categoryResDto);
    }

    @Operation(
      summary = "Get a list of all Categories.",
      description = "Let a user get all categories on a list."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "201", description = "Categories found successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = NoteResponseDto.class))
        }),
      @ApiResponse(responseCode = "500", description = "Server error.", content = {@Content})
    })
    @GetMapping
    public ResponseEntity<CategoryListResDto> getCategories() {

        CategoryListResDto categoryListResDto = categoryService.getCategories();

        return ResponseEntity
          .status(200)
          .body(categoryListResDto);
    }
}
