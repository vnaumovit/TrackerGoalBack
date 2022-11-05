package com.veilas.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.veilas.model.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue()
    private UUID id;

    private String name;

    private String code;

    private ItemType itemType;

    private Long retailPrice;

    @OneToOne(cascade = REMOVE)
    private Image image;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = REMOVE, mappedBy = "item")
    private List<Size> sizes;
}
