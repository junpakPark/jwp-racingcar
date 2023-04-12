package racingcar.model;

import racingcar.dto.CarDto;
import racingcar.dto.WinnerCarDto;
import racingcar.utils.RacingNumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void race(RacingNumberGenerator generator) {
        cars.forEach(car -> car.race(generator));
    }

    public List<CarDto> getCarsDto() {
        return cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    public List<WinnerCarDto> getWinner() {
        Car winner = cars.stream()
                .max(Car::compareTo)
                .orElse(null);

        return sort(winner);
    }

    private List<WinnerCarDto> sort(Car winner) {
        return cars.stream()
                .filter(car -> car.isSamePosition(winner))
                .map(car -> new WinnerCarDto(car.getName(), convertDto()))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<CarDto> convertDto() {
        return cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
