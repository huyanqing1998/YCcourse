package com.yc.favorite.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.Tag;
import com.yc.favorite.biz.FavoriteBiz;
import com.yc.favorite.dao.FavoriteMapper;
import com.yc.favorite.dao.TagMapper;
import com.yc.favorite.util.MyBatisHelper;

@WebServlet("/saveFavorite.s")
public class AddFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FavoriteBiz fBiz = new FavoriteBiz();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Favorite f = new Favorite();
		f.setfLabel(request.getParameter("fLabel"));
		f.setfTags(request.getParameter("fTags"));
		f.setfUrl(request.getParameter("fUrl"));
		f.setfDesc(request.getParameter("fDesc"));
		try {
			fBiz.addFavorite(f);
			response.sendRedirect("index.s");
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "添加失败");
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
