package core.fu4sbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subject {
    @Id
    private String code;

    private String name;
    private int semester;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Collection<Post> posts;
}
