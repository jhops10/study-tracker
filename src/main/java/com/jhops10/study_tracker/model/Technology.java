package com.jhops10.study_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_technology")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private String shortDescription;
}
