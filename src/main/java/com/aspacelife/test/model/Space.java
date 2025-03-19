package com.aspacelife.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Space {
    private String id =UUID.randomUUID().toString();
    private String name;
    private boolean available;

}
