/*
 * Copyright 2009-2015 PIANOo; TenderNed programma.
 */
package nl.famscheper.hhhtest.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import nl.famscheper.hhhtest.dao.LobEntityDao;
import nl.famscheper.hhhtest.util.ArquillianDeploymentSupport;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

/**
 * Arquillian integrationtest used to test the persist / merge of an entity on Postgres with a Lob.
 *
 * @author Erik-Berndt Scheper
 * @since 31-08-2015
 */
@RunWith(Arquillian.class)
public class LobEntityPersistIT {

    private static final Logger LOGGER = getLogger(LobEntityPersistIT.class);
    private static final String PDF_FILE_NAME = "Kastelen_en_Landhuizenroute_Vorden_nl.pdf";

    @Inject
    LobEntityDao lobEntityDao;

    @Deployment
    public static Archive<?> createDeploymentPackage() {
        WebArchive archive = ArquillianDeploymentSupport.createIntegrationTestArchive(LobEntityPersistIT.class.getSimpleName().toLowerCase() + ".war");
        archive.addAsResource(ArquillianDeploymentSupport.class.getClassLoader().getResource(PDF_FILE_NAME), PDF_FILE_NAME);
        return archive;
    }

    @Test
    public void runTests() {
        int size = loadBinaryFile(PDF_FILE_NAME).length;
        assertThat(size, is(not(0)));

        Long lobEntityId = persistEntityWithoutLob();
        updateEntityWithLob(lobEntityId, size);
        loadEntityWithLob(lobEntityId, size);
    }

    /**
     * Persist initial entity, without lob.
     *
     * @return {@code ID} of the persisted entity.
     */
    private Long persistEntityWithoutLob() {

        Long lobEntityId = lobEntityDao.persistEntityWithoutLob();
        assertThat(lobEntityId, notNullValue());

        LOGGER.info("**** Persisted LobEntity with id = {}", lobEntityId);

        return lobEntityId;
    }

    /**
     * Update entity with a lob.
     *
     * @param lobEntityId {@code ID} of the persisted entity
     * @param size        expected size of the lob
     */
    private void updateEntityWithLob(Long lobEntityId, int size) {
        assertThat(lobEntityId, notNullValue());

        LobEntity found = lobEntityDao.updateEntityWithLob(lobEntityId);
        assertThat(found, notNullValue());

        LOGGER.info("**** Found LobEntity with id = {}", found.getId());

        assertThat(found.getId(), is(lobEntityId));
        assertThat(found.getName(), notNullValue());
        assertThat(found.getLob(), notNullValue());
        assertThat(found.getLob().length, is(size));
    }

    /**
     * Load entity with a lob.
     *
     * @param lobEntityId {@code ID} of the persisted entity
     * @param size        expected size of the lob
     */
    private void loadEntityWithLob(Long lobEntityId, int size) {
        assertThat(lobEntityId, notNullValue());

        LobEntity found = lobEntityDao.loadEntityWithLob(lobEntityId);
        assertThat(found, notNullValue());

        LOGGER.info("**** Found LobEntity with id = {}", found.getId());

        assertThat(found.getId(), is(lobEntityId));
        assertThat(found.getName(), notNullValue());
        assertThat(found.getLob(), notNullValue());
        assertThat(found.getLob().length, is(size));
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

            assertThat(inputStream, notNullValue());

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
