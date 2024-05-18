package core.fu4sbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LearningMaterial extends Post {
    private String content;

    @OneToMany(mappedBy = "learningMaterial", cascade = CascadeType.ALL)
    private ArrayList<MaterialImage> images;
}
