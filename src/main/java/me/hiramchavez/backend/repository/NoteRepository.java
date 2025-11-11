package me.hiramchavez.backend.repository;

import me.hiramchavez.backend.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note getNoteByIdAndDeletedFalse(Long id);

    Page<Note> findAllByDeletedFalse(Pageable pageable);
}
