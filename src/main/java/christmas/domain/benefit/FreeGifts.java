package christmas.domain.benefit;

import christmas.dto.benefit.BenefitDetailDto;
import christmas.dto.benefit.FreeGiftsDto;
import java.util.List;

public class FreeGifts {
    private final List<FreeGift> freeGifts;

    private FreeGifts(List<FreeGift> freeGifts) {
        this.freeGifts = freeGifts;
    }

    public static FreeGifts from(List<FreeGift> freeGifts) {
        return new FreeGifts(freeGifts);
    }

    public Integer calculateFreeGiftsPrice() {
        return freeGifts.stream()
                .mapToInt(FreeGift::calculateFreeGiftPrice)
                .sum();
    }

    public FreeGiftsDto toFreeGiftsDto() {
        return FreeGiftsDto.of(freeGifts.stream()
                .map(FreeGift::toFreeGiftDto)
                .toList());
    }

    public List<BenefitDetailDto> toBenefitDetailDtos() {
        return freeGifts.stream()
                .map(gift -> BenefitDetailDto.of(gift.getDescription(), gift.calculateFreeGiftPrice()))
                .toList();
    }
}
