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
import io.reactivex.schedulers.Schedulers;

/**
 * Project: CloudStation
 * FileName: CloudApi.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:50 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:50 PM
 * Remark:
 */
public class CloudApi {

    /**
     * 获取首页banner
     *
     * @param num
     * @return
     */
    public static Observable<Banner> getBanner(int num) {
        CloudClient.status = 0;
        String method = "baidu.ting.plaza.getFocusPic";
        Map<String, String> params = CloudClient.getParams("method", method, "num", num);
        return CloudClient.getService().getBanner(params).subscribeOn(Schedulers.io());
    }

    /**
     * 获取热门歌单
     *
     * @param num
     * @return
     */
    public static Observable<SongSheet> getHotSongSheet(int num) {
        CloudClient.status = 0;
        String method = "baidu.ting.diy.getHotGeDanAndOfficial";
        Map<String, String> params = CloudClient.getParams("method", method, "num", num);
        return CloudClient.getService().getHotSongSheet(params).subscribeOn(Schedulers.io());
    }

    /**
     * 获取推荐专辑
     *
     * @param offset
     * @param limit
     * @return
     */
    public static Observable<Album> getRecdAlbum(int offset, int limit) {
        CloudClient.status = 0;
        String method = "baidu.ting.plaza.getRecommendAlbum";
        Map<String, String> params = CloudClient.getParams("method", method, "offset", offset, "limit", limit);
        return CloudClient.getService().getRecdAlbum(params).subscribeOn(Schedulers.io());
    }

    /**
     * 获取推荐音乐
     *
     * @param num
     * @return
     */
    public static Observable<Music> getRecdMusic(int num) {
        CloudClient.status = 0;
        String method = "baidu.ting.song.getEditorRecommend";
        Map<String, String> params = CloudClient.getParams("method", method, "num", num);
        return CloudClient.getService().getRecdMusic(params).subscribeOn(Schedulers.io());
    }

    /**
     * 获取所有歌单
     *
     * @param page_size
     * @param page_no
     * @return
     */
    public static Observable<AllSongSheet> getAllSongSheet(int page_size, int page_no) {
        CloudClient.status = 0;
        String method = "baidu.ting.diy.gedan";
        Map<String, String> params = CloudClient.getParams("method", method, "page_size", page_size, "page_no", page_no);
        return CloudClient.getService().getAllSongSheet(params).subscribeOn(Schedulers.io());
    }

    /**
     * 获取歌单分类
     *
     * @return
     */
    public static Observable<SongSheetCategory> getSongSheetCategory() {
        CloudClient.status = 0;
        String method = "baidu.ting.diy.gedanCategory";
        Map<String, String> params = CloudClient.getParams("method", method);
        return CloudClient.getService().getSongSheetCategory(params).subscribeOn(Schedulers.io());
    }

    /**
     * 获取排行榜
     *
     * @param kflag
     * @return
     */
    public static Observable<RankBean> getAllRank(int kflag) {
        CloudClient.status = 0;
        String method = "baidu.ting.billboard.billCategory";
        Map<String, String> params = CloudClient.getParams("method", method);
        return CloudClient.getService().getAllRank(params).subscribeOn(Schedulers.io());
    }

    /**
     * 获取歌单详情
     *
     * @return
     */
    public static Observable<SongSheetDetail> getSongSheetDetail(String listid) {
        CloudClient.status = 0;
        String method = "baidu.ting.diy.gedanInfo";
        Map<String, String> params = CloudClient.getParams("method", method, "listid", listid);
        return CloudClient.getService().getSongSheetDetail(params).subscribeOn(Schedulers.io());
    }

    /**
     * 获取专辑详情
     *
     * @param albumId
     * @return
     */
    public static Observable<AlbumDetail> getAlbumDetail(String albumId) {
        CloudClient.status = 0;
        String method = "baidu.ting.album.getAlbumInfo";
        Map<String, String> params = CloudClient.getParams("method", method, "album_id", albumId);
        return CloudClient.getService().getAlbumDetail(params).subscribeOn(Schedulers.io());
    }

    /**
     * 获取排行榜详情
     *
     * @param type
     * @param offset
     * @param size
     * @return
     */
    public static Observable<RankDetail> getRankDetail(String type, int offset, int size) {
        CloudClient.status = 0;
        String method = "baidu.ting.billboard.billList";
        String fields = "song_id,title,author,album_title,pic_big,pic_small,havehigh,all_rate,charge,has_mv_mobile,learn,song_source,korean_bb_song";
        Map<String, String> params = CloudClient.getParams("method", method, "type", type, "offset", offset, "size", size, "fields", fields);
        return CloudClient.getService().getRankDetail(params).subscribeOn(Schedulers.io());
    }

    /**
     * 在线获取Artist信息
     *
     * @param artist
     * @return
     */
    public static Observable<LastfmArtist> getArtistInfo(String artist) {
        CloudClient.status = 1;
        String method = "artist.getinfo";
        String api_key = "fdb3a51437d4281d4d64964d333531d4";
        String format = "json";
        Map<String, String> params = CloudClient.getParams("method", method, "artist", artist, "api_key", api_key, "format", format);
        return CloudClient.getService().getArtistInfo(params).subscribeOn(Schedulers.io());
    }
}
