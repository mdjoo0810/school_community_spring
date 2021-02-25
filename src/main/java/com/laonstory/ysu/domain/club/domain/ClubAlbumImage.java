package com.laonstory.ysu.domain.club.domain;

import com.laonstory.ysu.domain.common.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "T_CLUB_ALBUM_IMAGE")
public class ClubAlbumImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubAlbum album;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ClubAlbumImageLike> likes = new ArrayList<>();

}
