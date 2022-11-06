package com.sunday.jewerly.model.sale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity()
@Table(name = "sale_sizes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleSize {
    @Id
    @GeneratedValue
    private UUID id;

    private Float size;

    private Integer quantity;

    @JsonIgnore
    @JoinColumn(name = "sale_item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SaleItem saleItem;
}
