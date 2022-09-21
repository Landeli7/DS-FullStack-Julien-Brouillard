package fs.polytech.fsback.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import fs.polytech.fsback.entities.EvalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvalDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("nom")
    @Size(max = 90)
    private String nom;

    @JsonProperty("commentaire")
    @Size(max = 255)
    private String commentaire;

    @JsonProperty("note")
    @Max(3)
    @Min(0)
    private int note;

    public static EvalDto fromEntity(EvalEntity evalEntity) {
        return EvalDto.builder()
            .id(evalEntity.getId())
            .nom(evalEntity.getNom())
            .commentaire(evalEntity.getCommentaire())
            .note(evalEntity.getNote())
            .build();
    }
}
