package com.aspacelife.test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String id = UUID.randomUUID().toString();
    private String name;

}
