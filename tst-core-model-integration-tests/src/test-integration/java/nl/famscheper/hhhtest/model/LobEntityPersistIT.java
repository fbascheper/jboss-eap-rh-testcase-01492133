/*
 * Copyright 2009-2015 PIANOo; TenderNed programma.
 */
package nl.famscheper.hhhtest.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.famscheper.hhhtest.util.ArquillianDeploymentSupport;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
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

    @PersistenceContext
    private EntityManager entityManager;

    private static Long lobEntityId;

    @Deployment
    public static Archive<?> createDeploymentPackage() {
        WebArchive archive = ArquillianDeploymentSupport.createIntegrationTestArchive(LobEntityPersistIT.class.getSimpleName().toLowerCase() + ".war");
        archive.addAsResource(ArquillianDeploymentSupport.class.getClassLoader().getResource(PDF_FILE_NAME), PDF_FILE_NAME);
        return archive;
    }

    /**
     * Persist initial entity, without lob.
     */
    @Test
    //    @UsingDataSet(value = {"data/entity/LobEntityPersistIT-data.xml"})
    //    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    @Transactional(TransactionMode.COMMIT)
    @InSequence(1)
    public void testPersistEntityWithoutLob() {

        LobEntity lobEntity = new LobEntity();
        lobEntity.setName(PDF_FILE_NAME);
        lobEntity.setLob(null);

        entityManager.persist(lobEntity);

        lobEntityId = lobEntity.getId();
        assertThat(lobEntityId, notNullValue());

        LOGGER.info("**** Persisted LobEntity with id = {}", lobEntityId);
    }

    /**
     * Update entity with a lob.
     */
    @Test
    //    @UsingDataSet(value = {"data/entity/LobEntityPersistIT-data.xml"})
    //    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    @Transactional(TransactionMode.COMMIT)
    @InSequence(2)
    public void testUpdateEntityWithLob() {
        assertThat(lobEntityId, notNullValue());

        LobEntity found = entityManager.find(LobEntity.class, lobEntityId);
        assertThat(found, notNullValue());

        LOGGER.info("**** Found LobEntity with id = {}", found.getId());

        assertThat(found.getId(), is(lobEntityId));
        assertThat(found.getName(), notNullValue());
        assertThat(found.getLob(), nullValue());

        found.setLob(loadBinaryFile(PDF_FILE_NAME));
    }

    /**
     * Load entity with a lob.
     */
    @Test
    //    @UsingDataSet(value = {"data/entity/LobEntityPersistIT-data.xml"})
    //    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    @Transactional(TransactionMode.COMMIT)
    @InSequence(3)
    public void loadEntityWithLob() {
        assertThat(lobEntityId, notNullValue());

        LobEntity found = entityManager.find(LobEntity.class, lobEntityId);
        assertThat(found, notNullValue());

        LOGGER.info("**** Found LobEntity with id = {}", found.getId());

        assertThat(found.getId(), is(lobEntityId));
        assertThat(found.getName(), notNullValue());
        assertThat(found.getLob(), notNullValue());
        assertThat(found.getLob().length, is(not(0)));
        assertThat(found.getLob().length, is(loadBinaryFile(PDF_FILE_NAME).length));

        // detach entity
        entityManager.clear();

        // update name
        found.setName(found.getName() + "_updated");
        found = entityManager.merge(found);
        LOGGER.debug("Successfully merged entity {}", found);
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
                InputStream inputStream = LobEntityPersistIT.class.getClassLoader().getResourceAsStream(name);
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
