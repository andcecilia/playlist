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

    @GeneratedValue
    @Id
    private Long id;

    @NotBlank(message = "Title not blank")
    private String title;

    private String artist;

    @ManyToOne(fetch = FetchType.LAZY)
    private User userEntity;

}
