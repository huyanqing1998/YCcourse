package com.yc.favorite.biz;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yc.favorite.bean.Favorite;
import com.yc.favorite.dao.FavoriteMapper;
import com.yc.favorite.dao.TagMapper;

@Service
@Transactional
public class FavoriteBiz {

	@Resource
	private TagMapper tm;
	@Resource
	private FavoriteMapper fm;

	public void addFavorite(Favorite favorite) {
		fm.insert(favorite);
		String[] tNames = favorite.getfTags().split("[；;，,\\s]");
		for (String tName : tNames) {
			int ret = tm.updateCount(tName);
			if (ret == 0) {
				tm.insert(tName);
			}
		}
	}
}
