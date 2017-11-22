package com.cloud.api;

import com.cloud.model.music.Album;
import com.cloud.model.music.AlbumDetail;
import com.cloud.model.music.AllSongSheet;
import com.cloud.model.music.Banner;
import com.cloud.model.music.LastfmArtist;
import com.cloud.model.music.Music;
import com.cloud.model.music.RankBean;
import com.cloud.model.music.RankDetail;
import com.cloud.model.music.SongSheet;
import com.cloud.model.music.SongSheetCategory;
import com.cloud.model.music.SongSheetDetail;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Project: CloudStation
 * FileName: CloudService.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:48 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:48 PM
 * Remark:
 */
public interface CloudService {

    @GET("ting")
    Observable<Banner> getBanner(@QueryMap Map<String, String> params);

    @GET("ting")
    Observable<SongSheet> getHotSongSheet(@QueryMap Map<String, String> params);

    @GET("ting")
    Observable<Album> getRecdAlbum(@QueryMap Map<String, String> params);

    @GET("ting")
    Observable<Music> getRecdMusic(@QueryMap Map<String, String> params);

    @GET("ting")
    Observable<AllSongSheet> getAllSongSheet(@QueryMap Map<String, String> params);

    @GET("ting")
    Observable<SongSheetCategory> getSongSheetCategory(@QueryMap Map<String, String> params);

    @GET("ting")
    Observable<RankBean> getAllRank(@QueryMap Map<String, String> params);

    @GET("ting")
    Observable<SongSheetDetail> getSongSheetDetail(@QueryMap Map<String, String> params);

    @GET("ting")
    Observable<AlbumDetail> getAlbumDetail(@QueryMap Map<String, String> params);

    @GET("ting")
    Observable<RankDetail> getRankDetail(@QueryMap Map<String, String> params);

    @GET("2.0/")
    Observable<LastfmArtist> getArtistInfo(@QueryMap Map<String, String> params);
}
