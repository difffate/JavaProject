package org.zheng.convert.model;

import lombok.Data;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Data
public class Team {

    private int id;

    private List<String> names;

    private Member leader;

    private LocalDate createDate;

    private Member viceLeader;

    @NumberFormat(pattern = "c:0000")
    private int memberCount;

    private String logo;

    private List<String> subNames;
}
