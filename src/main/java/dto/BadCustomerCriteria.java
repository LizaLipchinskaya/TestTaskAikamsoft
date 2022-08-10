package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadCustomerCriteria extends Criteria {
    Integer badCustomers;
}
