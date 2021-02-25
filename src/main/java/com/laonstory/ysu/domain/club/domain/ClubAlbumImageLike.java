package com.laonstory.ysu.domain.club.domain;

import com.laonstory.ysu.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "T_CLUB_ALBUM_IMAGE_LIKE")
public class ClubAlbumImageLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubAlbumImage image;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
