package fs.polytech.fsback.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evalutations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvalEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "nom", columnDefinition = "varchar(255)", nullable = false)
    private String nom;

    @Column(name = "commentaire", columnDefinition = "varchar(255)", nullable = false)
    private String commentaire;

    @Column(name = "note")
    private int note;

    @ManyToMany(mappedBy = "evaluations")
    private List<RestaurantEntity> evalListe;
}
