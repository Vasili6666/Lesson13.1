package models.lombok;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel {

    private Integer id;
    private String title;
    private String body;
    private Integer userId;

}




