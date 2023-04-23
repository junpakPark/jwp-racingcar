package racingcar.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.service.CarService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebCarControllerTest {

	@Value("${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CarService carService;

	@Test
	void playsTest() {
		// given
		final GamePlayRequestDto gamePlayRequestDto = new GamePlayRequestDto("준팍, 무민", 1);
		final String url = String.format("http://localhost:%d/plays", port);

		// when
		final ResponseEntity<GamePlayResponseDto> responseEntity = restTemplate.postForEntity(url, gamePlayRequestDto,
			GamePlayResponseDto.class);

		// then
		final List<GamePlayResponseDto> gamePlayHistoryAll = carService.findGamePlayHistoryAll();
		final String name = gamePlayHistoryAll.get(0).getRacingCars().get(0).getName();

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(name).isEqualTo("준팍");
	}
}
