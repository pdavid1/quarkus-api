package quarkus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import io.vertx.core.cli.annotations.ParsedAsList;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class TemperaturaService implements ITemperaturaService{

    private List<Temperatura> valores = new ArrayList<>();

    @Override
    public void addTemperatura(Temperatura t) {
        valores.add(t);
    }
    
    @Override
    public List<Temperatura> obtenerTemperaturas() {
        return Collections.unmodifiableList(valores);
    }

    @Override
    public int maxima() {
        return valores.stream().mapToInt(Temperatura::getMaxima).max().getAsInt();
    }

    @Override
    public boolean isEmpty() {
        return valores.isEmpty();
        //throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public Optional<Temperatura> sacarTemperatura(String ciudad) {
        return valores.stream()
                .filter(t -> t.getCiudad().equals(ciudad))
                .findAny();
    }


}
