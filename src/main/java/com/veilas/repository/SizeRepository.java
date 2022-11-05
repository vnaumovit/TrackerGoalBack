package com.veilas.repository;

import com.veilas.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SizeRepository extends JpaRepository<Size, UUID> {

    List<Size> findByItemId(UUID itemId);

}
