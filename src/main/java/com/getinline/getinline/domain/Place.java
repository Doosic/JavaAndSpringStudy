package com.getinline.getinline.domain;

import com.getinline.getinline.constant.PlaceType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@AllArgsConstructor
@Builder
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, columnDefinition = "varchar(20) default 'COMMON'")
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @Setter
    @Column(nullable = false)
    private String placeName;

    @Setter
    @Column(nullable = false)
    private String address;

    @Setter
    @Column(nullable = false)
    private String phoneNumber;

    @Setter
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer capacity;


    @Setter
    private String memo;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "place")
    private final Set<Event> events = new LinkedHashSet<>();

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private final Set<AdminPlaceMap> adminPlaceMaps = new LinkedHashSet<>();


    protected Place() {}

}
