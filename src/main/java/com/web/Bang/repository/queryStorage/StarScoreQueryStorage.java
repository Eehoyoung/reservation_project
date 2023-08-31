package com.web.Bang.repository.queryStorage;

import org.springframework.stereotype.Component;

@Component
public class StarScoreQueryStorage {

    public String getAvgStarScoreByHouse(int houseId) {
        return "SELECT houseId, ROUND(AVG(starScore), 1) AS score "
                + "FROM review "
                + "WHERE houseId = " + houseId + " "
                + "GROUP BY houseId ";
    }
}
