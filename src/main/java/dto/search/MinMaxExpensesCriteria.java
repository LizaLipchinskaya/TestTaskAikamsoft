package dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MinMaxExpensesCriteria extends Criteria {
    private Integer minExpenses;

    private Integer maxExpenses;
}
