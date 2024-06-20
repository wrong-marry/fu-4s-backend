package core.fu4sbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
    private String content;

    @OneToMany(mappedBy = "learningMaterial", cascade = CascadeType.ALL)
    private Collection<MaterialFile> files;
}
