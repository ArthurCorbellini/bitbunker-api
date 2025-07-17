package com.artcorb.bitbunker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.artcorb.bitbunker.models.AssetCategory;

@Repository
public interface AssetCategoryRepository extends JpaRepository<AssetCategory, Long> {

}
