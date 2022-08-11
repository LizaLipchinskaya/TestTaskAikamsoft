package dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Purchase {
    private String name;

    private Integer expenses;
}
