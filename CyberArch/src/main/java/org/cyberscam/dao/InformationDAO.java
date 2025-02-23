package org.cyberscam.dao;

import org.cyberscam.config.DatabaseConfig;
import org.cyberscam.models.Information;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformationDAO {

    public List<Information> getAllInformation() {
        List<Information> infoList = new ArrayList<>();
        String sql = "SELECT * FROM Information";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Information info = new Information();
                info.setInfoId(rs.getInt("info_id"));
                info.setCategoryId(rs.getInt("category_id"));
                info.setTitle(rs.getString("title"));
                info.setContent(rs.getString("content"));
                info.setSourceLink(rs.getString("source_link"));
                info.setPostedBy(rs.getString("posted_by"));
                info.setPostedAt(rs.getString("posted_at"));
                infoList.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoList;
    }
}
