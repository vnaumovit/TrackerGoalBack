package com.sunday.jewerly.model.sale;


import com.sunday.jewerly.model.enums.SalesChannel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameCustomer;

    private String description;

    private String phoneCustomer;

    @Enumerated(value = EnumType.STRING)
    private SalesChannel salesChannel;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_item_id")
    private List<SaleItem> saleItem;

    private Integer totalCost;

    private LocalDateTime dateTimePurchase;
}
