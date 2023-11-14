package christmas.domain;

public class Benefit {
    private final DiscountDetails discountDetails;
    private final FreeGifts freeGifts;

    public Benefit(DiscountDetails discountDetails, FreeGifts freeGifts) {
        this.discountDetails = discountDetails;
        this.freeGifts = freeGifts;
    }

    public static Benefit of(DiscountDetails discountDetails, FreeGifts freeGifts) {
        return new Benefit(discountDetails, freeGifts);
    }
}
