package local.home.daytoday.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TimeZoneColumn;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Table(name = "links",
       indexes = @Index(unique = true, name = "full_url_index", columnList = "full_url"),
       uniqueConstraints = @UniqueConstraint(name = "shortcutted_url_constraint", columnNames = {"shortcut_url"}))
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_gen")
    @SequenceGenerator(name = "link_gen", sequenceName = "links_id_seq", allocationSize = 1)
    private Long id;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private ZonedDateTime createDate;

    @Column(name = "full_url", nullable = false)
    private String fullUrl;

    @Column(name = "shortcut_url", nullable = false)
    private String shortcutUrl;

    @Column(name = "redirect_url", nullable = false)
    private String redirectUrl;
}