package core.fu4sbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class LearningMaterial extends Post {
    @Lob
    @Column
    private String content;

    @OneToMany(mappedBy = "learningMaterial", cascade = CascadeType.ALL)
    private Collection<MaterialFile> files;
}
