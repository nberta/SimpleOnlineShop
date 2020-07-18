package edu.miu.simpleshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdHolder {

    private Long id;
    private String message;

    public IdHolder(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}
