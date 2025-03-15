package com.example.kidsnesia.repository

import com.example.kidsnesia.entity.Produk
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdukRepository : JpaRepository<Produk, Long>
