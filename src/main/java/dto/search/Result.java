package dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result {
    private Criteria criteria;

    private List<CustomerSearch> results;
}
