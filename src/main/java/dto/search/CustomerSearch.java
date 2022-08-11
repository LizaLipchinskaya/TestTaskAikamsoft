package dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerSearch {
    private String lastName;

    private String firstName;
}
