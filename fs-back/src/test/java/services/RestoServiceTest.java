package services;
import fs.polytech.fsback.entities.RestaurantEntity;
import fs.polytech.fsback.exceptions.InvalidValueException;
import fs.polytech.fsback.exceptions.ResourceDoesntExistException;
import fs.polytech.fsback.repositories.RestaurantRepository;
import fs.polytech.fsback.services.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RestoServiceTest {
    @InjectMocks
    private RestaurantService restoService;

    @Mock
    private RestaurantRepository restoRepository;

    @Test()
    public void shouldThrowAInvalidValueException() {
        assertThrows(InvalidValueException.class, () -> this.restoService.updateRestaurant(1, null, null));
    }

    @Test()
    public void shouldThrownAResourceDoesntExist() {
        when(restoRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceDoesntExistException.class, () -> this.restoService.updateRestaurant(199, "nouveau nom", "nouvelle adresse"));
    }

    @Test()
    public void shouldReturnARestoEntity() {
        RestaurantEntity expected = RestaurantEntity.builder().id(3).build();
        when(restoRepository.findById(any())).thenReturn(Optional.of(expected));
        RestaurantEntity result = this.restoService.getRestoById(3);
        assertEquals(expected, result);
    }
}
