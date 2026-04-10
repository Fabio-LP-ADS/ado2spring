package br.com.carstore.dto;

import java.util.List;

public class CarResponseBody {

    private List<CarDTO> cars;

    public CarResponseBody(List<CarDTO> cars) {
        this.cars = cars;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }
}
