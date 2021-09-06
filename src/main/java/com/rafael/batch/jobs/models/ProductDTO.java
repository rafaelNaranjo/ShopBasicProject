package com.rafael.batch.jobs.models;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String nombre;
    private String marca;
    private String precio;
    private String cantidad;
    private String estado;
    private String descuento;
    
    public boolean isSomeFieldNull(){
        return this.getNombre().isEmpty() || this.getMarca().isEmpty() ||
                this.getPrecio().isEmpty() || this.getCantidad().isEmpty() ||
                this.getEstado().isEmpty() || this.getDescuento().isEmpty();
    }
}
