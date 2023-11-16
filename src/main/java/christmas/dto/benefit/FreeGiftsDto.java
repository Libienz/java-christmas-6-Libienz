package christmas.dto.benefit;

import java.util.List;

public class FreeGiftsDto {
    private final List<FreeGiftDto> freeGiftDtos;

    public FreeGiftsDto(List<FreeGiftDto> freeGiftDtos) {
        this.freeGiftDtos = freeGiftDtos;
    }

    public static FreeGiftsDto of(List<FreeGiftDto> freeGiftDtos) {
        return new FreeGiftsDto(freeGiftDtos);
    }

    public List<FreeGiftDto> getFreeGiftDtos() {
        return freeGiftDtos;
    }
}
