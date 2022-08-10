package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LastNameCriteria extends Criteria {
    String lastName;
}
