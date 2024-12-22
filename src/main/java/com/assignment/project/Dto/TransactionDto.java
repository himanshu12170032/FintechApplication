package com.assignment.project.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
}
