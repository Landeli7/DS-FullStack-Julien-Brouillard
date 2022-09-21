package fs.polytech.fsback.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import fs.polytech.fsback.entities.RestaurantEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("nom")
    @Size(max = 90)
    private String nom;

    @JsonProperty("adresse")
    @Size(max = 255)
    private String adresse;

    @JsonProperty("evalListe")
    private List<EvalDto> evalListe;

    @JsonProperty("moyenne")
    private int moyenne;

    public static RestaurantDto fromEntity(RestaurantEntity restoEntity) {
        return RestaurantDto.builder()
            .id(restoEntity.getId())
            .nom(restoEntity.getNom())
            .adresse(restoEntity.getAdresse())
            .evalListe(restoEntity.getEvalListe().stream().map(eval -> EvalDto.fromEntity(eval)).collect(Collectors.toList()))
            .moyenne(-1)
            .build();
    }
}
