package christmas.domain;

import java.util.List;

public class FreeGifts {
    private final List<FreeGift> freeGifts;

    private FreeGifts(List<FreeGift> freeGifts) {
        this.freeGifts = freeGifts;
    }

    public static FreeGifts from(List<FreeGift> freeGifts) {
        return new FreeGifts(freeGifts);
    }

    public List<FreeGift> getGiveawayResultDtos() {
        return freeGifts;
    }
}
