package racingcar.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import racingcar.dao.entity.GameEntity;
import racingcar.dto.GameResultDto;

@Repository
public class GameDao {

	private final JdbcTemplate jdbcTemplate;

	public GameDao(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long save(final GameEntity gameEntity) {
		String sql = "insert into GAME (winners, trial_count) values (?, ?)";

		final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
			ps.setString(1, gameEntity.getWinners());
			ps.setInt(2, gameEntity.getTrialCount());
			return ps;
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	public List<GameResultDto> findGamePlayHistoryAll() {

		final String sql = "SELECT GAME.ID, GAME.WINNERS, PLAYER.NAME, PLAYER.POSITION FROM GAME LEFT JOIN PLAYER ON GAME.ID = PLAYER.GAME_ID";

		final RowMapper<GameResultDto> rowMapper = (resultSet, rowNum) -> new GameResultDto(
			resultSet.getLong("id"),
			resultSet.getString("WINNERS"),
			resultSet.getString("NAME"),
			resultSet.getInt("POSITION")
		);

		return jdbcTemplate.query(sql, rowMapper);
	}

}
