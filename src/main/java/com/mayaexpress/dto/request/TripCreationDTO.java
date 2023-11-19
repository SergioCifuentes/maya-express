package com.mayaexpress.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.util.Calendar;

@Data
public class TripCreationDTO {

    @JsonFormat(pattern="yyyy-MM-dd")
    private Calendar startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Calendar endDate;
}
