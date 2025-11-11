package me.hiramchavez.backend.model;

import jakarta.persistence.*;
import lombok.*;
import me.hiramchavez.backend.dto.note.NoteResponseDto;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "last_edited", columnDefinition = "TIMESTAMP")
    private Date lastEdited;

    @Enumerated(EnumType.STRING)
    private State state;

    private boolean deleted;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinTable(
      name = "notes_categories",
      joinColumns = @JoinColumn(name = "note_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;// = new HashSet<>();

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getNotes().add(this);
    }

    public void clearCategories() {
        for (Category category : categories) {
            category.getNotes().remove(this);
        }
        categories.clear();
    }

    public Note update(NoteResponseDto noteResponseDto) {
        if (noteResponseDto.title() != null) {
            this.title = noteResponseDto.title();
        }

        if (noteResponseDto.description() != null) {
            this.description = noteResponseDto.description();
        }


        if (noteResponseDto.state() != null) {
            this.state = noteResponseDto.state();
        }

        this.lastEdited = new Date();

        return this;
    }

    public void delete() {
        this.deleted = true;
    }

    public void archived() {
        this.state = State.ARCHIVED;
    }

    public void unarchived() {
        this.state = State.ACTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note note)) return false;
        return deleted == note.deleted && Objects.equals(id, note.id) && Objects.equals(title, note.title) && Objects.equals(description, note.description) && Objects.equals(lastEdited, note.lastEdited) && state == note.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, lastEdited, state, deleted);
    }

    @Override
    public String toString() {
        return "Note{" +
          "id=" + id +
          ", title='" + title + '\'' +
          ", description='" + description + '\'' +
          ", lastEdited=" + lastEdited +
          ", state=" + state +
          ", deleted=" + deleted +
          '}';
    }
}
