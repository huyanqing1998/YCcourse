package com.yc.favorite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.yc.favorite.bean.Favorite;

public interface FavoriteMapper {
	/*
	 * 根据flag的值做对应的Favoriate查询，如果flag==1查全部，如果flag==2查f_tags==null的记录
	 */
	public List<Favorite> selectByFlag(@Param("flag") String flag);
	
	@Insert("insert into favorite values (null,#{fLabel},#{fUrl},#{fTags},#{fDesc})")
	public void insert(Favorite f);

}
