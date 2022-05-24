package com.letscode.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Music {

    @Id
    private Long id;

    @NotBlank
    private String title;

    private String musicalGender;

    @ManyToMany(fetch = FetchType.LAZY)  //FetchType.EAGER
    private User userEntity;

}
