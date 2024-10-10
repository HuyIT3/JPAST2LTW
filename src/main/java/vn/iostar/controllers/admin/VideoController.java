package vn.iostar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Video;
import vn.iostar.entity.Category;
import vn.iostar.services.IVideoService;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.VideoServices;
import vn.iostar.services.impl.CategoryServices;
import vn.iostar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/admin/videos","/admin/videoid/edit","/admin/videoid/update",
	"/admin/videoid/insert", "/admin/videoid/add", "/admin/videoid/delete"})
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IVideoService vidService = new VideoServices();
	ICategoryService cateService = new CategoryServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("videos")) {
			List<Video> list = vidService.findAll();
			req.setAttribute("listvid", list);
			req.getRequestDispatcher("/views/admin/videoid-list.jsp").forward(req, resp);
		}else if(url.contains("/admin/videoid/edit")) {
			String vidid = req.getParameter("videoId");
			
			Video category = vidService.findById(vidid);
			req.setAttribute("vid", category);
			List<Category> categories = cateService.findAll(); 
			req.setAttribute("categories", categories);
		    req.getRequestDispatcher("/views/admin/videoid-edit.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/videoid/add")) {
			 List<Category> categories = cateService.findAll(); 
		        req.setAttribute("categories", categories);
			req.getRequestDispatcher("/views/admin/videoid-add.jsp").forward(req, resp);
		}else if(url.contains("/admin/videoid/delete")) {
			String id = req.getParameter("videoId");
			try {
				vidService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("/admin/videoid/update")) {
			String videoid = req.getParameter("VideoId");
			String title = req.getParameter("title");
			String des = req.getParameter("Description");
			
			int viw = Integer.parseInt(req.getParameter("views"));
			String catename = req.getParameter("category");
			Category category = cateService.findById(Integer.parseInt(catename));
			int active = Integer.parseInt(req.getParameter("active")); 
			Video video = new Video();
			video.setVideoId(videoid);
			video.setTitle(title);
			
			video.setDescription(des);
			video.setCategory(category);
			video.setViews(viw);
		   
			video.setActive(active);
			//lưu hình cũ
			Video videoold = vidService.findById(videoid);
            String fileold = videoold.getPoster();
            // Upload Images
            String fname = "";
            String uploadPath = Constant.DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    // rename file
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index+1);
                    fname = System.currentTimeMillis() + "." + ext;
                    // upload file
                    part.write(uploadPath + "/" + fname);
                   
                    video.setPoster(fname);
                } else {
                    video.setPoster(fileold);
                }
            } catch (Exception e) {
                
                e.printStackTrace();
            }
				vidService.update(video);
				resp.sendRedirect(req.getContextPath() + "/admin/videos");
			}else if(url.contains("/admin/videoid/insert")) {
				Video video = new Video();
				String title = req.getParameter("title");
				String des = req.getParameter("Description");
				String vidid = req.getParameter("VideoId");
				int viw = Integer.parseInt(req.getParameter("Views"));
				String catename = req.getParameter("category");
				Category category = cateService.findById(Integer.parseInt(catename));
				int active = Integer.parseInt(req.getParameter("active"));
				video.setVideoId(vidid);
				video.setTitle(title);
				video.setDescription(des);
				video.setCategory(category);
				video.setViews(viw);
				
				video.setActive(active);
				String fname="";
				String uploadPath= Constant.UPLOAD_DIRECTORY;
				File uploadDir = new File(uploadPath);
				if(!uploadDir.exists()) {
				uploadDir.mkdir();
				}
				try {
					Part part = req.getPart("poster");
					if(part.getSize()>0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						//đổi tên file	
						int index = filename.lastIndexOf(".");
						String ext = filename.substring(index+1);
						fname = System.currentTimeMillis() + "." + ext;
						//up load file
						part.write(uploadPath + "/" + fname);
						//ghi tên file vào data
						video.setPoster(fname);
					}else {
						video.setPoster("avarta.png");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
					vidService.insert(video);
					resp.sendRedirect(req.getContextPath() + "/admin/videos");
				}
	}
}
