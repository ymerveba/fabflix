package Dbfw;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParamMapper {
	void mapparam(PreparedStatement prestmt) throws SQLException;

}
