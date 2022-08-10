package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MinMaxExpensesCriteria extends Criteria {
    Integer minExpenses;

    Integer maxExpenses;
}
