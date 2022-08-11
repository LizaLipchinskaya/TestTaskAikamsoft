package dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StatDto {
    private String type;

    private Integer totalDays;

    private List<CustomerStat> customers;

    private Integer totalExpenses;

    private Double avgExpenses;
}
