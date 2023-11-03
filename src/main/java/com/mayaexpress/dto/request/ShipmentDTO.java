package com.mayaexpress.dto.request;

import com.mayaexpress.entity.Branch;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShipmentDTO {

    @NotBlank(message = "branchId es obligatorio")
    private Integer branchId;

    @NotBlank(message = "clientSendingName es obligatorio")
    private String clientSendingName;

    @NotBlank(message = "clientReceiveName es obligatorio")
    private String clientReceiveName;

    @NotBlank(message = "isPaid es obligatorio")
    private Boolean isPaid;

    @NotBlank(message = "total es obligatorio")
    private BigDecimal total;

    @NotBlank(message = "sendDate es obligatorio")
    private Date sendDate;

    @NotBlank(message = "address es obligatorio")
    private String address;

    @NotBlank(message = "receiveBranchId es obligatorio")
    private Integer receiveBranchId;

    @NotBlank(message = "payDate es obligatorio")
    private Date payDate;

    private PackageDTO[] packages;







}
