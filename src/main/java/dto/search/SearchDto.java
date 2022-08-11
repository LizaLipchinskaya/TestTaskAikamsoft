package dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchDto {
    private String type;

    private List<Result> results;
}
