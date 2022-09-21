package fs.polytech.fsback.entities;

import javax.persistence.*;
import java.util.List;

// import javax.persistence.Id;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurants")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "nom", columnDefinition = "varchar(255)", nullable = false)
    private String nom;

    @Column(name = "adresse", columnDefinition = "varchar(255)", nullable = false)
    private String adresse;

    @ManyToMany
    @JoinTable(
            name = "eval_in_resto",
            joinColumns = @JoinColumn(name = "id_resto"),
            inverseJoinColumns = @JoinColumn(name = "id_eval"))
    private List<EvalEntity> evalListe;

    @Column(name = "moyenne", nullable = false)
    private int moyenne;
}
