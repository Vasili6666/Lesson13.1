package models.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLombokPostByIdTest {
    private int userId;
    private int id;
    private String title;
    private String body;
}
