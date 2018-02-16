package com.bulkprocess.bulkprocess.readers;

import com.bulkprocess.bulkprocess.pojo.RAndWFromDBPOJO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RAndWFromDBPOJORowMapper implements RowMapper<RAndWFromDBPOJO>{

    @Override
    public RAndWFromDBPOJO mapRow(ResultSet resultSet, int i) throws SQLException{
        return new RAndWFromDBPOJO(
                resultSet.getInt("id"),
                resultSet.getString("f_name"),
                resultSet.getString("l_name"),
                resultSet.getLong("phone")
        );
    }

}
