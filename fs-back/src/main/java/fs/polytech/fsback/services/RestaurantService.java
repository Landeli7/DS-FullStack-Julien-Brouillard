package fs.polytech.fsback.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fs.polytech.fsback.entities.EvalEntity;
import fs.polytech.fsback.entities.RestaurantEntity;
import fs.polytech.fsback.exceptions.InvalidValueException;
import fs.polytech.fsback.exceptions.ResourceDoesntExistException;
import fs.polytech.fsback.repositories.EvaluationRepository;
import fs.polytech.fsback.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final EvaluationRepository evaluationRepository;

    //Retourne une liste de restaurants
    public List<RestaurantEntity> getRestaurants() {
        log.info("Get Restaurants");

        return this.restaurantRepository.findAll();
    }

    public RestaurantEntity getRestoById(int id) {
        log.info("Get Restaurant");

        RestaurantEntity resto = this.restaurantRepository.findById(id)
            .orElseThrow(() -> new ResourceDoesntExistException("resto with id " + id + " doesn't exists"));
    
        resto.setMoyenne(getMoyenne(resto));

        return resto;
    }

    public RestaurantEntity addRestaurant(final String nom) {
        log.info("Add Restaurant");

        final RestaurantEntity newResto = RestaurantEntity.builder().nom(nom).build();
        return this.restaurantRepository.save(newResto);
    }

    public RestaurantEntity updateRestaurant(int id, String nouveauNom, String nouvelleAdresse) {
        if (nouveauNom == null) {
            throw new InvalidValueException("le nouveau nom ne doit pas être null");
        }
        if (nouvelleAdresse == null) {
            throw new InvalidValueException("la nouvelle adresse ne doit pas être null");
        }
        log.info("Update Restaurant");
        final RestaurantEntity RestoToUpdate = this.restaurantRepository.findById(id).orElseThrow(() -> new ResourceDoesntExistException("le resto d'id " + id + " n'existe pas"));
        RestoToUpdate.setNom(nouveauNom);
        RestoToUpdate.setAdresse(nouvelleAdresse);
        restaurantRepository.save(RestoToUpdate);
        return RestoToUpdate;
    }

    public RestaurantEntity addEvalOnResto(int idResto, String nom, String commentaire, int note) {
        log.info("Add eval on resto");

        RestaurantEntity resto = getRestoById(idResto);
        List<EvalEntity> evalListe = resto.getEvalListe();

        final EvalEntity newEval = EvalEntity.builder().nom(nom).commentaire(commentaire).note(note).build();

        evalListe.add(newEval);

        return this.restaurantRepository.save(resto);
    }

    public EvalEntity getEvalById(int id) {
        log.info("Get Eval");

        return this.evaluationRepository.findById(id)
            .orElseThrow(() -> new ResourceDoesntExistException("eval with id " + id + " doesn't exists"));
    }

    public RestaurantEntity delEval(int idResto, int idEval) {
        log.info("Del eval on resto");

        RestaurantEntity resto = getRestoById(idResto);
        List<EvalEntity> evalListe = resto.getEvalListe();

        EvalEntity evalToDelete = getEvalById(idEval);

        evalListe.remove(evalToDelete);

        return this.restaurantRepository.save(resto);
    }

    public int getMoyenne(RestaurantEntity resto) {
        List<EvalEntity> evalListe = resto.getEvalListe();

        int moyenne = -1;

        int notesAdditionnees = 0;
        if (evalListe.size() > 0 ) {
            for (int i = 0; i < evalListe.size(); i++) {
                notesAdditionnees += evalListe.get(i).getNote();
            }
            moyenne = notesAdditionnees/evalListe.size();
        }

        return moyenne;
    }
}