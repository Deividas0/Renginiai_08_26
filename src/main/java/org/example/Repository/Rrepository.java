package org.example.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public class Rrepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void saveEvent(String eventName, String eventType, Date eventDate, double eventPrice, byte[] eventImage) {
        String sql = "INSERT INTO renginiai (pavadinimas, tipas, data, kaina, foto) VALUES (:pavadinimas, :tipas, :data, :kaina, :foto)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("pavadinimas", eventName)
                .addValue("tipas", eventType)
                .addValue("data", eventDate)
                .addValue("kaina", eventPrice)
                .addValue("foto", eventImage);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<Map<String, Object>> getAllEvents() {
        String sql = "SELECT * FROM renginiai";
        return namedParameterJdbcTemplate.queryForList(sql, new MapSqlParameterSource());
    }

    public Map<String, Object> getEventById(Long id) {
        String sql = "SELECT * FROM renginiai WHERE id = :id";
        return namedParameterJdbcTemplate.queryForMap(sql, new MapSqlParameterSource("id", id));
    }

    public void updateEvent(Long id, String eventName, String eventType, Date eventDate, double eventPrice, byte[] eventImage) {
        String sql = "UPDATE renginiai SET pavadinimas = :pavadinimas, tipas = :tipas, data = :data, kaina = :kaina, foto = :foto WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("pavadinimas", eventName)
                .addValue("tipas", eventType)
                .addValue("data", eventDate)
                .addValue("kaina", eventPrice)
                .addValue("foto", eventImage);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void deleteEvent(Long id) {
        String sql = "DELETE FROM renginiai WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }
}
