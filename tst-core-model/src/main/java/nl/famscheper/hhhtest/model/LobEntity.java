package nl.famscheper.hhhtest.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Sample entity with a Lob.
 *
 * @author Erik-Berndt Scheper
 * @since 31-8-2015
 */
@javax.persistence.Entity
@Table(name = "TST_LOB_ENTITY")
public class LobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tst_lob_entity_seq_gen")
    @SequenceGenerator(name = "tst_lob_entity_seq_gen", sequenceName = "tst_lob_entity_id_seq")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Lob
    @Column(name = "LOB_FIELD")
    @Basic(fetch = FetchType.LAZY)
    private byte[] lob;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLob() {
        return lob;
    }

    public void setLob(byte[] lob) {
        this.lob = lob;
    }

    @Override
    public String toString() {
        return "LobEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
