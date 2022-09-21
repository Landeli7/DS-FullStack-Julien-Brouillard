package fs.polytech.fsback.controllers;

import fs.polytech.fsback.dto.EvalDto;
import fs.polytech.fsback.dto.RestaurantDto;
import fs.polytech.fsback.services.RestaurantService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
//@RequiredArgsConstructor
public class RestaurantController {
    public final RestaurantService restoService; //final : indiq pour dire que ça change pas plus tard (constante et pas variable)

    public RestaurantController(RestaurantService restoService) {
        this.restoService = restoService;
    }

    @GetMapping("/restaurants/{id}")
    public @ResponseBody RestaurantDto getRestoById(@PathVariable int id) {
        return RestaurantDto.fromEntity(this.restoService.getRestoById(id));
    }

    @GetMapping("/restaurants")
    public @ResponseBody List<RestaurantDto> getRestos() {
        System.out.println("retourne tous les restaurants");
        return this.restoService.getRestaurants().stream().map(entity -> RestaurantDto.fromEntity(entity)).collect(Collectors.toList());
    }

    @PostMapping("/restaurants")
    public RestaurantDto postResto(@Valid @RequestBody RestaurantDto resto) {
        return RestaurantDto.fromEntity(this.restoService.addRestaurant(resto.getNom()));
    }

    @PostMapping("/restaurants/{id}")
    public RestaurantDto postEvalOnResto(@PathVariable int id, @Valid @RequestBody EvalDto eval) {
        return RestaurantDto.fromEntity(this.restoService.addEvalOnResto(id, eval.getNom(), eval.getCommentaire(), eval.getNote()));
    }

    @GetMapping("/evaluations/{id}")
    public @ResponseBody EvalDto getEvalById(@PathVariable int id) {
        return EvalDto.fromEntity(this.restoService.getEvalById(id));
    }

    @DeleteMapping("/restaurants/{idResto}/{idEval}")
    public RestaurantDto deleteEval(@PathVariable int idResto, @PathVariable int idEval) {
        return RestaurantDto.fromEntity(this.restoService.delEval(idResto, idEval));
    }
}
