package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.GamePlayResponseDto;
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

    public GamePlayResponseDto getWinner() {
        Car winner = cars.stream()
                .max(Car::compareTo)
                .orElse(null);

        return new GamePlayResponseDto(findWinnerNames(winner), findPlayers());
    }

    private List<String> findWinnerNames(Car winner) {
        return cars.stream()
                .filter(car -> car.isSamePosition(winner))
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private List<CarDto> findPlayers() {
        return cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}