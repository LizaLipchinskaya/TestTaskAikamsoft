package dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerStat {
    private String name;

    private List<Purchase> purchases;

    private Integer totalExpenses;
}
