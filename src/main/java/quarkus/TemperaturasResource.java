package quarkus;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/Temperaturas")
public class TemperaturasResource {

    private TemperaturaService temperaturas;
    int temperaturaMaxima;
            
    @Inject
    public TemperaturasResource(TemperaturaService temperaturas) {
        this.temperaturas = temperaturas;
    }
    
    @POST
    public Temperatura nueva(Temperatura temp) {
        temperaturas.addTemperatura(temp);
        return temp;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperatura> list() {

        return temperaturas.obtenerTemperaturas();
    }

    @GET
    @Path("/maxima")
    @Produces(MediaType.TEXT_PLAIN)
    public Response maxima() {
        if(temperaturas.isEmpty()) {
            return Response.status(404).entity("No hay temperaturas").build();
        } else {
            return Response.ok(Integer.toString(temperaturaMaxima)).header("X-Hola","Buenos dias").build();
        }

    }

    @GET
    @Path("{ciudad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura sacar(@PathParam("ciudad")String ciudad) {
        return temperaturas.sacarTemperatura(ciudad)
            .orElseThrow(() ->
            new NoSuchElementException("No hay registro para la ciudad " + ciudad));
    }

}
