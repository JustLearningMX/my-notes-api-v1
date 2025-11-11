package me.hiramchavez.backend.mapper;

import me.hiramchavez.backend.dto.note.NoteRequestDto;
import me.hiramchavez.backend.dto.note.NoteResponseDto;
import me.hiramchavez.backend.model.Note;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteMapper {
    Note noteRequestDtoToNote(NoteRequestDto noteRequestDto);

    NoteRequestDto noteToNoteRequestDto(Note note);

    Note noteResponseDtoToNote(NoteResponseDto noteResponseDto);

    NoteResponseDto noteToNoteResponseDto(Note note);

}