package core.fu4sbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAvatarDto implements Serializable {
    private String username;
    private MultipartFile image;
}
