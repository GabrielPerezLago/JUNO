package com.gabriel.juno.domain.port;

import com.gabriel.juno.domain.models.Centro;
import java.util.List;

public interface CentroRepository {
    List<Centro> findByAll();
}
