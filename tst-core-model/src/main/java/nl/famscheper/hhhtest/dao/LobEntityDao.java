/*
 * Copyright 2009-2015 PIANOo; TenderNed programma.
 */
package nl.famscheper.hhhtest.dao;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.famscheper.hhhtest.model.LobEntity;
import nl.famscheper.hhhtest.util.Assert;

import org.slf4j.Logger;

/**
 * Simple DAO implementation for testing purposes.
 *
 * @author Erik-Berndt Scheper
 * @since 05-10-2015
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LobEntityDao {

    private static final Logger LOGGER = getLogger(LobEntityDao.class);
    private static final String PDF_FILE_NAME = "Kastelen_en_Landhuizenroute_Vorden_nl.pdf";

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Persist initial entity, without lob.
     *
     * @return {@code ID} of the persisted entity.
     */
    public Long persistEntityWithoutLob() {

        LobEntity lobEntity = new LobEntity();
        lobEntity.setName(PDF_FILE_NAME);
        lobEntity.setLob(null);

        entityManager.persist(lobEntity);

        Long lobEntityId = lobEntity.getId();
        Assert.notNull(lobEntityId);

        LOGGER.info("**** Persisted LobEntity with id = {}", lobEntityId);
        return lobEntityId;
    }

    /**
     * Update entity with a lob.
     *
     * @param lobEntityId {@code ID} of the persisted entity
     */
    public LobEntity updateEntityWithLob(Long lobEntityId) {
        Assert.notNull(lobEntityId);

        LobEntity found = entityManager.find(LobEntity.class, lobEntityId);
        Assert.notNull(found);

        LOGGER.info("**** Found LobEntity with id = {}", found.getId());

        Assert.isTrue(lobEntityId.equals(found.getId()));
        Assert.notNull(found.getName());
        Assert.isNull(found.getLob());

        found.setLob(loadBinaryFile(PDF_FILE_NAME));

        return found;
    }

    /**
     * Load entity with a lob.
     *
     * @param lobEntityId {@code ID} of the persisted entity
     */
    public LobEntity loadEntityWithLob(Long lobEntityId) {
        Assert.notNull(lobEntityId);

        LobEntity found = entityManager.find(LobEntity.class, lobEntityId);
        Assert.notNull(found);

        LOGGER.info("**** Found LobEntity with id = {}", found.getId());

        Assert.isTrue(lobEntityId.equals(found.getId()));
        Assert.notNull(found.getName());
        Assert.notNull(found.getLob());
        Assert.isTrue(found.getLob().length != 0);
        Assert.isTrue(found.getLob().length == loadBinaryFile(PDF_FILE_NAME).length);

        // detach entity
        entityManager.clear();

        // update name
        found.setName(found.getName() + "_updated");
        found = entityManager.merge(found);
        LOGGER.debug("Successfully merged entity {}", found);

        return found;
    }

    /**
     * Load a binary file and return it as a byte array.
     *
     * @param name file name of the file to load
     * @return the byte array containing the binary file.
     */
    private byte[] loadBinaryFile(String name) {
        byte[] data;

        try (
                InputStream inputStream = LobEntity.class.getClassLoader().getResourceAsStream(name);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ) {

            Assert.notNull(inputStream);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            data = outputStream.toByteArray();

        } catch (IOException ioex) {
            throw new IllegalStateException("Could not read binary file", ioex);
        }
        return data;
    }

}
