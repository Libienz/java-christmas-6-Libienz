package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.FreeGift;
import christmas.domain.benefit.FreeGifts;
import christmas.domain.menu.MenuItem;
import christmas.dto.BenefitDetailDto;
import christmas.dto.FreeGiftDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("증정품 테스트")
class FreeGiftsTest {
    private FreeGifts testFreeGifts;

    @BeforeEach
    void setUp() {
        FreeGift gift1 = FreeGift.of(MenuItem.CHAMPAGNE, 1, "증정 이벤트");
        testFreeGifts = FreeGifts.from(List.of(gift1));
    }

    @DisplayName("증정 품목들의 가격 합을 계산할 수 있다")
    @Test
    void testCalculateGiftedPrice() {
        assertThat(testFreeGifts.calculateFreeGiftsPrice()).isEqualTo(25000);
    }

    @DisplayName("증정 품목 DTO로 변환 가능하다")
    @Test
    void testCreateGiftDto() {
        assertThat(testFreeGifts.toFreeGiftsDto().getFreeGiftDtos()).extracting(FreeGiftDto::getMenuName)
                .contains("샴페인");
    }

    @DisplayName("혜택 내역 DTO로 변환 가능하다")
    @Test
    void testCreateBenefitDto() {
        assertThat(testFreeGifts.toBenefitDetailDtos()).extracting(BenefitDetailDto::getDescription).contains("증정 이벤트");
    }
}