package ind.br.vedax.api.entities;

/**
 *
 * @author GabrielOutor
 */
public class Place {
    private String long_name;

    public Place() {
    }

    public Place(String long_name) {
        this.long_name = long_name;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    @Override
    public String toString() {
        return "Place{" + "long_name=" + long_name + '}';
    }

}
