package ind.br.vedax.api.entities;

/**
 *
 * @author GabrielOutor
 */
public class Temperature {
    private Double value;

    public Temperature() {
    }

    public Temperature(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Temperature{" + "value=" + value + '}';
    }

    
}
