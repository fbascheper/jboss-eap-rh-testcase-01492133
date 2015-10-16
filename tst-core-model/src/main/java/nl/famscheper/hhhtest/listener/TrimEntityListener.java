package nl.famscheper.hhhtest.listener;

/**
 * Simple entity listener which trims the name of the entity
 *
 * @author Frans Oosterhof
 * @since 16-10-2015
 */

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import nl.famscheper.hhhtest.model.LobEntity;

public class TrimEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersist(final LobEntity entity) {
        entity.setName(entity.getName().trim());
    }

}

