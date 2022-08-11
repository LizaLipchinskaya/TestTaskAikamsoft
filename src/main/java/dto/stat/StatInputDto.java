package dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatInputDto {
    private String startDate;

    private String endDate;
}
