package core.bean;

/**
 * 功能描述：音乐类基类
 *
 * @Author: wuyachong
 * @Date: 2020/10/28
 */
public class SongsBean {

    private String songName;

    private String singerName;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
}
