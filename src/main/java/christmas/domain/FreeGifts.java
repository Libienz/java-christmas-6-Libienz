package christmas.domain;

import christmas.dto.BenefitDetailDto;
import christmas.dto.FreeGiftsDto;
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
                .mapToInt(FreeGift::getDiscountAmount)
                .sum();
    }

    public FreeGiftsDto toFreeGiftsDto() {
        return FreeGiftsDto.of(freeGifts.stream()
                .map(FreeGift::toFreeGiftDto)
                .toList());
    }

    public List<BenefitDetailDto> toBenefitDetailDtos() {
        return freeGifts.stream()
                .map(gift -> BenefitDetailDto.of(gift.getDescription(), gift.getDiscountAmount()))
                .toList();
    }
}
