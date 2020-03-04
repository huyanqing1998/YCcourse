/*package com.yc.favorite.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.Tag;
import com.yc.favorite.dao.FavoriteMapper;
import com.yc.favorite.dao.TagMapper;
import com.yc.favorite.util.MyBatisHelper;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession session = MyBatisHelper.openSession();
		try {
			TagMapper tm = session.getMapper(TagMapper.class);
			FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
			List<Tag> list = tm.selectAll();
			
			String tId = request.getParameter("tId");
			String flag = request.getParameter("flag");
			if(tId!=null) {
				for(Tag t:list) {
					if(tId.equals(""+t.gettId())) {
						request.setAttribute("showTag", t);
						break;
					}
				}
			} else if (flag!=null) {
				List<Favorite> fList = fm.selectByFlag(flag);
				request.setAttribute("fList",fList);
			}
			System.out.println(list);
			request.setAttribute("tList", list);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} finally {
			session.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
*/