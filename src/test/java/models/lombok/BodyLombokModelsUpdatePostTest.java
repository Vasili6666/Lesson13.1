package models.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor // пустой конструктор
@AllArgsConstructor // конструктор со всеми полями
public class BodyLombokModelsUpdatePostTest {
    private int id;
    private String title;
    private String body;
    private int userId;
}
