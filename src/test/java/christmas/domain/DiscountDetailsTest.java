package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.dto.BenefitDetailDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("할인 내역 테스트")
class DiscountDetailsTest {
    @DisplayName("총 할인 금액을 계산할 수 있다")
    @Test
    void testCalculateTotalDiscountPrice() {
        DiscountDetail discount1 = DiscountDetail.of("크리스마스 디데이 할인", 1200);
        DiscountDetail discount2 = DiscountDetail.of("평일 할인", 4046);
        DiscountDetail discount3 = DiscountDetail.of("특별 할인", 1000);
        DiscountDetails discountDetails = DiscountDetails.from(List.of(discount1, discount2, discount3));

        assertThat(discountDetails.calculateDiscountedPrice()).isEqualTo(6246);
    }

    @DisplayName("혜택 내역 DTO로 변환 가능하다")
    @Test
    void testCreateBenefitDetailDto() {
        DiscountDetail discount1 = DiscountDetail.of("크리스마스 디데이 할인", 1200);
        DiscountDetail discount2 = DiscountDetail.of("평일 할인", 4046);
        DiscountDetail discount3 = DiscountDetail.of("특별 할인", 1000);
        DiscountDetails discountDetails = DiscountDetails.from(List.of(discount1, discount2, discount3));

        assertThat(discountDetails.toBenefitDetailDtos()).extracting(BenefitDetailDto::getDescription)
                .contains("크리스마스 디데이 할인", "평일 할인", "특별 할인");
    }
}