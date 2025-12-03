package net.ausiasmarch.persutil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.persutil.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
