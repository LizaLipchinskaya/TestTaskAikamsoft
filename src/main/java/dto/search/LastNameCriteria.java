package dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LastNameCriteria extends Criteria {
    private String lastName;
}
