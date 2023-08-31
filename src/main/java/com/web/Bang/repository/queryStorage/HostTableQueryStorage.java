package com.web.Bang.repository.queryStorage;

import org.springframework.stereotype.Component;

@Component
public class HostTableQueryStorage {

    public String getlist(int hostid, int houseid, int month) {
        return "SELECT r.id as id,username, headCount, checkInDate, checkOutDate, price ,phoneNumber, request, approvalStatus, h.id as houseId, h.name as houseName\r\n"
                + "FROM reservation as r\r\n"
                + "INNER JOIN house as h\r\n"
                + "ON r.houseId = h.id\r\n"
                + "INNER JOIN user as u\r\n"
                + "ON r.guestId = u.id\r\n"
                + "where r.hostId = " + hostid + " and checkIndate like \"%-0" + month + "-%\"\r\n"
                + "and h.id = " + houseid + "\r\n"
                + "order by id desc\r\n"
                + "limit 31";

    }

    public String getlist(int hostid, int month) {
        return "SELECT r.id as id,username, headCount, checkInDate, checkOutDate, phoneNumber, request, approvalStatus, h.id as houseId, h.name as houseName\r\n"
                + "FROM reservation as r\r\n"
                + "INNER JOIN house as h\r\n"
                + "ON r.houseId = h.id\r\n"
                + "INNER JOIN user as u\r\n"
                + "ON r.guestId = u.id\r\n"
                + "where r.hostId = " + hostid + " and checkIndate like \"%-0" + month + "-%\"\r\n"
                + "order by id desc;";

    }

    public String getWaitCount(int id) {
        return "select count(id) as wait, houseId\r\n"
                + "from reservation\r\n"
                + "where hostId =" + id + "\r\n"
                + "and approvalStatus like 'WAITING'\r\n"
                + "group by houseId";
    }
}
