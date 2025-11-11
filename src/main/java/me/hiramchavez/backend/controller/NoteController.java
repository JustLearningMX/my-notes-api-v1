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
import me.hiramchavez.backend.dto.ResponseDeleteDto;
import me.hiramchavez.backend.dto.note.NoteRequestDto;
import me.hiramchavez.backend.dto.note.NoteResponseDto;
import me.hiramchavez.backend.service.NoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
@Tag(name = "Notes", description = "Manage all endpoints about Notes") //For OpenApi-Swagger
public class NoteController {

    private final NoteService noteService;

    @Operation(
      summary = "Create a Note.",
      description = "Let a user create a note with title and description."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "201", description = "Note created successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = NoteResponseDto.class))
        }),
      @ApiResponse(responseCode = "500", description = "Server error.", content = {@Content})
    })
    @PostMapping
    @Transactional
    public ResponseEntity<NoteResponseDto> createNote(@RequestBody @Valid NoteRequestDto noteRequestDto, UriComponentsBuilder uriComponentsBuilder) {

        NoteResponseDto noteResponseDto = noteService.create(noteRequestDto);

        URI location = uriComponentsBuilder
          .path("/notes/{id}")
          .buildAndExpand(noteResponseDto.id())
          .toUri();

        return ResponseEntity
          .created(location)
          .body(noteResponseDto);
    }

    @Operation(
      summary = "Update a Note.",
      description = "Let a user update a note total o partially."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "Note updated successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = NoteResponseDto.class))
        }),
      @ApiResponse(responseCode = "404", description = "Note not found.", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Server error.", content = {@Content})
    })
    @PutMapping
    @Transactional
    public ResponseEntity<NoteResponseDto> updateNote(@RequestBody @Valid NoteResponseDto noteResponseDto) {
        return ResponseEntity
          .status(200)
          .body(noteService.update(noteResponseDto));
    }

    @Operation(
      summary = "Delete a Note.",
      description = "Let a user apply a logical delete of a Note."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "Note deleted successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = ResponseDeleteDto.class))
        }),
      @ApiResponse(responseCode = "404", description = "Note Not Found", content = {@Content}),
        @ApiResponse(responseCode = "500", description = "Server error.", content = {@Content})
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseDeleteDto> deleteNote(@PathVariable Long id) {
        return ResponseEntity
          .status(200)
          .body(noteService.delete(id));
    }

    @Operation(
      summary = "Archived a Note.",
      description = "Let a user archived a Note."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "Note archived successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = ResponseDeleteDto.class))
        }),
      @ApiResponse(responseCode = "404", description = "Note Not Found", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Server error.", content = {@Content})
    })
    @PutMapping("/archived/{id}")
    @Transactional
    public ResponseEntity<NoteResponseDto> archivedNote(@PathVariable Long id) {
        return ResponseEntity
          .status(200)
          .body(noteService.archived(id));
    }

    @Operation(
      summary = "Unarchived a Note.",
      description = "Let a user unarchived a Note."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "Note unarchived successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = ResponseDeleteDto.class))
        }),
      @ApiResponse(responseCode = "404", description = "Note Not Found", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Server error.", content = {@Content})
    })
    @PutMapping("/unarchived/{id}")
    @Transactional
    public ResponseEntity<NoteResponseDto> unarchivedNote(@PathVariable Long id) {
        return ResponseEntity
          .status(200)
          .body(noteService.unarchived(id));
    }

    @Operation(
      summary = "Get a Note by its ID.",
      description = "Let a user get a Note using the ID."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "Note found successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = NoteResponseDto.class))
        }),
      @ApiResponse(responseCode = "404", description = "Note Not Found", content = {@Content}),
        @ApiResponse(responseCode = "500", description = "Server error.", content = {@Content})
    })
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDto> getNoteById(@PathVariable Long id) {
        return ResponseEntity
          .status(200)
          .body(noteService.getNoteById(id));
    }

    @Operation(
      summary = "Get all Notes.",
      description = "Let a user geta list with all Notes available."
    )
    @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "Notes found successfully.",
        content = {
          @Content(mediaType = "application/json",
            schema = @Schema(implementation = NoteResponseDto.class))
        }),
      @ApiResponse(responseCode = "404", description = "Notes Not Found", content = {@Content}),
      @ApiResponse(responseCode = "500", description = "Server error.", content = {@Content})
    })
    @GetMapping
    public ResponseEntity<Page<NoteResponseDto>> getNotes(Pageable pageable) {
        return ResponseEntity
          .status(200)
          .body(noteService.getNotes(pageable));
    }

}
