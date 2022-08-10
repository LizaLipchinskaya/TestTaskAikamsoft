package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductNameCriteria extends Criteria {
    String productName;

    Integer minTimes;
}
