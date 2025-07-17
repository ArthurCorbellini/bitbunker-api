package com.artcorb.bitbunker.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.artcorb.bitbunker.models.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

  Optional<Asset> findByUcid(long ucid);

}
