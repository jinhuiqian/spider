package cn.itcast.crawler.test;

/**
 * @author 231
 * @date 2020-09-25 7:46
 */
public class FilmDaoImpl implements FilmDao {
    @Override
    public int add(Film film) {
        int code=0;
        String sql="insert into douban (title,score,posters,url) values(?,?,?,?)";

        code= DBUtils.update(sql,film.getTitle(),film.getScore(),film.getPosters(),film.getUrl());
        return code;    }
}
