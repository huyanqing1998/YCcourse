package com.yc.favorite.biz;

import org.apache.ibatis.session.SqlSession;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.dao.FavoriteMapper;
import com.yc.favorite.dao.TagMapper;
import com.yc.favorite.util.MyBatisHelper;

public class FavoriteBiz {

	public void addFavorite(Favorite favorite) {

		SqlSession session = MyBatisHelper.openSession();
		try {
			TagMapper tm = session.getMapper(TagMapper.class);
			FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
			fm.insert(favorite);
			String[] tNames = favorite.getfTags().split("[；;，,\\s]");
			for(String tName : tNames) {
				int ret = tm.updateCount(tName);
				if(ret == 0) {
					tm.insert(tName);
				}
			}
			session.commit();
		} catch (RuntimeException e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
