package fs.polytech.fsback.entities;

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
@Table(name = "evalutations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EvalEntity {
    @Id
    @GeneratedValue
    int id;

    @Column(name = "nom", columnDefinition = "varchar(255)", nullable = false)
    String nom;

    @Column(name = "commentaire", columnDefinition = "varchar(255)", nullable = false)
    String commentaire;

    @Column(name = "note")
    int note;
}
