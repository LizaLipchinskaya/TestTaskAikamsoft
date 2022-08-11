package dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductNameCriteria extends Criteria {
    private String productName;

    private Integer minTimes;
}
