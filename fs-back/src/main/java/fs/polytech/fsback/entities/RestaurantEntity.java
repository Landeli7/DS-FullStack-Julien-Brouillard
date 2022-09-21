package fs.polytech.fsback.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

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
    int id;

    @Column(name = "nom", columnDefinition = "varchar(255)", nullable = false)
    String nom;

    @Column(name = "adresse", columnDefinition = "varchar(255)", nullable = false)
    String adresse;

    @Column(name = "evalListe", columnDefinition = "varchar(255)", nullable = false)
    List<EvalEntity> evalListe;

    @Column(name = "moyenne", nullable = false)
    int moyenne;
}
