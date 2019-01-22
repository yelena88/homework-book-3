package ex7;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiverRowMapper implements RowMapper <Receiver> {
  @Override
  public Receiver mapRow(ResultSet resultSet, int i) throws SQLException {
    Receiver receiver = new Receiver();
    receiver.setNum(resultSet.getInt("num"));
    receiver.setName(resultSet.getString("name"));

    return receiver;
  }
}
