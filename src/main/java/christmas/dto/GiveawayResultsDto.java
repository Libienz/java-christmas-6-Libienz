package christmas.dto;

import java.util.List;

public class GiveawayResultsDto {
    private final List<GiveawayResultDto> giveawayResultDtos;

    private GiveawayResultsDto(List<GiveawayResultDto> giveawayResultDtos) {
        this.giveawayResultDtos = giveawayResultDtos;
    }

    public static GiveawayResultsDto from(List<GiveawayResultDto> giveawayResultDtos) {
        return new GiveawayResultsDto(giveawayResultDtos);
    }

    public List<GiveawayResultDto> getGiveawayResultDtos() {
        return giveawayResultDtos;
    }
}
