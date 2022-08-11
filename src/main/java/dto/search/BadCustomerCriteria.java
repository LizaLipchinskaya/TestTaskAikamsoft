package dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadCustomerCriteria extends Criteria {
    private Integer badCustomers;
}
