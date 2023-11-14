package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.dto.BenefitDetailDto;
import christmas.dto.BenefitDetailsDto;
import christmas.dto.FreeGiftDto;
import christmas.dto.FreeGiftsDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("적용 혜택 테스트")
class BenefitTest {
    private Benefit testBenefit;

    @BeforeEach
    public void setUp() {
        DiscountDetail discount1 = DiscountDetail.of("크리스마스 디데이 할인", 1200);
        DiscountDetail discount2 = DiscountDetail.of("평일 할인", 4046);
        DiscountDetail discount3 = DiscountDetail.of("특별 할인", 1000);
        FreeGift gift1 = FreeGift.of(MenuItem.CHAMPAGNE, 1, "증정 이벤트");

        DiscountDetails discountDetails = DiscountDetails.from(List.of(discount1, discount2, discount3));
        FreeGifts freeGifts = FreeGifts.from(List.of(gift1));
        testBenefit = Benefit.of(discountDetails, freeGifts);
    }

    @DisplayName("총혜택 금액을 계산할 수 있다")
    @Test
    void testCalculateTotalBenefitAmount() {
        assertThat(testBenefit.calculateTotalBenefitAmount()).isEqualTo(31246);
    }

    @DisplayName("할인이 적용된 예상 결제 금액을 계산할 수 있다")
    @Test
    void testCalculateBenefitAppliedPrice() {
        assertThat(testBenefit.calculateBenefitAppliedPrice(142000)).isEqualTo(135754);
    }

    @DisplayName("혜택 내역을 생성해낼 수 있다")
    @Test
    void testCreateBenefitDetails() {
        BenefitDetailsDto benefitDetailsDto = testBenefit.toBenefitDetailsDto();
        assertThat(benefitDetailsDto.getBenefitDetailDtos()).extracting(BenefitDetailDto::getDescription)
                .contains("크리스마스 디데이 할인", "평일 할인", "특별 할인", "증정 이벤트");
    }

    @DisplayName("증정 내역을 생성할 수 있다")
    @Test
    void testCreateFreeGiftsDto() {
        FreeGiftsDto freeGiftsDto = testBenefit.toFreeGiftsDto();
        assertThat(freeGiftsDto.getFreeGiftDtos()).extracting(FreeGiftDto::getMenuName)
                .contains("샴페인");
    }

    @DisplayName("받을 수 있는 배지를 알 수 있다")
    @Test
    void testAchievableBadge() {
        assertThat(testBenefit.calculateAchievableBadge(20000)).isEqualTo(DecemberEventBadge.SANTA);
    }
}