package models.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyLombokModelsUpdatePostTest {
    private int id;
    private String title;
    private String body;
    private int userId;
}
