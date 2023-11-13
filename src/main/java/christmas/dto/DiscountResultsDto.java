package christmas.dto;

import java.util.List;

public class DiscountResultsDto {

    private final List<DiscountResultDto> discountResultDtos;

    private DiscountResultsDto(List<DiscountResultDto> discountResultDtos) {
        this.discountResultDtos = discountResultDtos;
    }

    public static DiscountResultsDto from(List<DiscountResultDto> discountResultDtos) {
        return new DiscountResultsDto(discountResultDtos);
    }

    public List<DiscountResultDto> getDiscountResultDtos() {
        return discountResultDtos;
    }
}
