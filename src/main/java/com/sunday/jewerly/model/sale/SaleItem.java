package com.sunday.jewerly.model.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sale_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleItem {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sale_size_id")
    private List<SaleSize> saleSize;
}
