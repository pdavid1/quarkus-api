package quarkus;

import java.util.List;
import java.util.Optional;

public interface ITemperaturaService {

    void addTemperatura(Temperatura t);

    List<Temperatura> obtenerTemperaturas();

    Optional<Temperatura> sacarTemperatura(String Ciudad);

    boolean isEmpty();

    int maxima();


}
