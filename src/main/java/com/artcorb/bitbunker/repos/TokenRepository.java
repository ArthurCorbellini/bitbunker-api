package com.artcorb.bitbunker.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.artcorb.bitbunker.models.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

  Optional<Token> findByUcid(long ucid);

}
