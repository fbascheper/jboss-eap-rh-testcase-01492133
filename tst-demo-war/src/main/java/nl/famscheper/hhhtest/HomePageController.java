package nl.famscheper.hhhtest;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import nl.famscheper.hhhtest.dao.LobEntityDao;

/**
 * Controller for the Home page (/home.xhtml).
 *
 * @author Erik-Berndt Scheper
 * @since 05-10-2015
 */
@Model
public class HomePageController {

    @Inject
    LobEntityDao lobEntityDao;

    public void testDaoMethods() {
        // create new entity without lob
        Long id = lobEntityDao.persistEntityWithoutLob();

        // add lob to entity
        lobEntityDao.updateEntityWithLob(id);

        // load entity with lob
        lobEntityDao.loadEntityWithLob(id);
    }


}
