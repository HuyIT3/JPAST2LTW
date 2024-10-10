package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.IVideoDao;
import vn.iostar.dao.impl.VideoDao;
import vn.iostar.entity.Video;
import vn.iostar.services.IVideoService;

public class VideoServices implements IVideoService{
	IVideoDao vidDao =new VideoDao();
	@Override
	public int count() {
		return vidDao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return vidDao.findAll(page, pagesize);
	}

	@Override
	public List<Video> findByTitlename(String vdtitle) {
		return vidDao.findByTitlename(vdtitle);
	}

	@Override
	public List<Video> findAll() {
		return vidDao.findAll();
	}

	
	@Override
	public void delete(String vidid) throws Exception {
		vidDao.delete(vidid);
		
	}

	@Override
	public void update(Video video) {
		vidDao.update(video);
		
	}

	@Override
	public void insert(Video video) {
		vidDao.insert(video);
		
	}

	@Override
	public Video findById(String vidid) {
		return vidDao.findById(vidid);
	}

}
