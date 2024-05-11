package com.dc.DexterCooke.Model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dc.DexterCooke.Model.Category;

@Data
@AllArgsConstructor


@NoArgsConstructor


	@Entity
	public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    
    

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    
    private Date updatedAt;
    
    

    

	    
	}



