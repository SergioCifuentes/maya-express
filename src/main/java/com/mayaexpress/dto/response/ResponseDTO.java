package com.mayaexpress.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private boolean success;
    private int status_code;
    private String message;
    private Object[] data;


}

