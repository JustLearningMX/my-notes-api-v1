package me.hiramchavez.backend.service;

import lombok.RequiredArgsConstructor;
import me.hiramchavez.backend.dto.ResponseDeleteDto;
import me.hiramchavez.backend.dto.note.NoteRequestDto;
import me.hiramchavez.backend.dto.note.NoteResponseDto;
import me.hiramchavez.backend.exception.note.NoteNotFoundException;
import me.hiramchavez.backend.mapper.NoteMapper;
import me.hiramchavez.backend.model.Note;
import me.hiramchavez.backend.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteResponseDto create(NoteRequestDto noteRequestDto) {
        return noteMapper.noteToNoteResponseDto(noteRepository.save(noteMapper.noteRequestDtoToNote(noteRequestDto)));
    }

    public NoteResponseDto update(NoteResponseDto noteResponseDto) {
        Note note = noteRepository.getNoteByIdAndDeletedFalse(noteResponseDto.id());

        if (note == null) {
            throw new NoteNotFoundException("Note not found");
        }

        return noteMapper.noteToNoteResponseDto(note.update(noteResponseDto));
    }

    public ResponseDeleteDto delete(Long id) {

        Note note = noteRepository.getNoteByIdAndDeletedFalse(id);

        if (note == null) {
            throw new NoteNotFoundException("Note not found");
        }

        note.delete();

        return new ResponseDeleteDto(false, "Note deleted successfully");
    }

    public NoteResponseDto archived(Long id) {
        Note note = noteRepository.getNoteByIdAndDeletedFalse(id);

        if (note == null) {
            throw new NoteNotFoundException("Note not found");
        }

        note.archived();

        return noteMapper.noteToNoteResponseDto(note);
    }

    public NoteResponseDto unarchived(Long id) {
        Note note = noteRepository.getNoteByIdAndDeletedFalse(id);

        if (note == null) {
            throw new NoteNotFoundException("Note not found");
        }

        note.unarchived();

        return noteMapper.noteToNoteResponseDto(note);
    }

    public NoteResponseDto getNoteById(Long id) {
        Note note = noteRepository.getNoteByIdAndDeletedFalse(id);

        if (note == null) {
            throw new NoteNotFoundException("Note not found");
        }

        return noteMapper.noteToNoteResponseDto(note);
    }

    public Page<NoteResponseDto> getNotes(Pageable pageable) {
        return noteRepository
          .findAllByDeletedFalse(pageable)
          .map(noteMapper::noteToNoteResponseDto);
    }
}
