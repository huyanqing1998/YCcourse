package com.yc.favorite.web;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.Tag;
import com.yc.favorite.biz.FavoriteBiz;
import com.yc.favorite.dao.FavoriteMapper;
import com.yc.favorite.dao.TagMapper;

@Controller
public class IndexServlet {
	
	@Resource
	private TagMapper tm;
	@Autowired
	private FavoriteMapper fm;
	
	@Resource
	FavoriteBiz fBiz;
	
	@GetMapping("index.s")
	public String index(String tId,String flag,Model m) {
			List<Tag> list = tm.selectAll();
			if(tId!=null) {
				for(Tag t:list) {
					if(tId.equals(""+t.gettId())) {
						m.addAttribute("showTag", t);
						break;
					}
				}
			} else if (flag!=null) {
				List<Favorite> fList = fm.selectByFlag(flag);
				m.addAttribute("fList",fList);
			}
			m.addAttribute("tList", list);
			return "index";		
	}
	
	@GetMapping("toedit")
	public String toedit() {
		return "edit";
	}
	
	
	@RequestMapping("saveFavorite.s")
	public String addFavorite(Favorite f,Model m) {
		try {
			fBiz.addFavorite(f);
			return "redirect:index.s";
		} catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("msg", "添加失败");
			return "edit";
		}
		
	}
	
}
